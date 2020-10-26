package com.hncboy.solver;

import com.hncboy.solver.algorithm.Bfs;
import com.hncboy.solver.entity.BfsParam;
import com.hncboy.solver.entity.RectangleMazeImageInfo;
import com.hncboy.solver.entity.SolvePoint;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @author hncboy
 * @date 2020/10/26 15:13
 * @description 迷宫解决者
 */
public class MazeSolver implements ISolveMaze {

    @Override
    public Mat solveSystemMaze(int[][] maze) {
        init();
        return null;
    }

    @Override
    public void solveRectangleMazeImage(RectangleMazeImageInfo rectangleMazeImageInfo) {
        init();

        // 读取图片到矩阵中
        Mat srcMaze = Imgcodecs.imread(rectangleMazeImageInfo.getReadFilePath());
        // 图片的行
        int rows = srcMaze.rows();
        // 图片的列
        int cols = srcMaze.cols();

        // 将图片转为黑白
        Mat colorMaze = srcMaze.clone();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (srcMaze.get(i, j)[0] < 100) {
                    colorMaze.put(i, j, 0, 0, 0);
                } else {
                    colorMaze.put(i, j, 255, 255, 255);
                }
            }
        }

        // bfs 进行路径查询
        BfsParam bfsParam = new BfsParam();
        bfsParam.setRows(rows);
        bfsParam.setCols(cols);
        bfsParam.setColorMaze(colorMaze);
        bfsParam.setStep(rectangleMazeImageInfo.getRoadPixel()/2);
        bfsParam.setStartPoint(rectangleMazeImageInfo.getStartPoint());
        bfsParam.setEndMat(rectangleMazeImageInfo.getEndMat());
        SolvePoint successMapPoint = Bfs.bfs(bfsParam);

        // 渲染路线
        Imgcodecs.imwrite(rectangleMazeImageInfo.getWriteFileName(), renderRoute(successMapPoint, colorMaze));
//        HighGui.imshow("color", colorMaze);
//        HighGui.waitKey(0);
    }

    /**
     * 渲染路线
     *
     * @param successMapPoint
     * @param colorMaze
     */
    private Mat renderRoute(SolvePoint successMapPoint, Mat colorMaze) {
        // 从终点开始往前遍历，渲染图片路径
        SolvePoint prev = successMapPoint.getPrev();
        colorMaze.put(prev.getY(), prev.getX(), 0, 0, 255);
        while (prev.getPrev() != null) {
            SolvePoint curr = prev;
            prev = prev.getPrev();

            // 画线
            Imgproc.line(colorMaze, new Point(curr.getX(), curr.getY()), new Point(prev.getX(), prev.getY()), new Scalar(0, 0, 255), 2);

            // 画点
            // colorMaze.put(prev.getY(), prev.getX(), 0, 0, 255);
        }
        return colorMaze;
    }
}
