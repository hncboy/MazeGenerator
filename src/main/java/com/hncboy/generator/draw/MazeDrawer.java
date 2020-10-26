package com.hncboy.generator.draw;

import com.hncboy.generator.enums.PointEnum;
import com.hncboy.generator.shape.Rectangle;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * @author hncboy
 * @date 2020/10/23 14:51
 * @description 迷宫绘制器
 */
public class MazeDrawer implements IDrawMaze {

    /**
     * 道路的尺寸（目前需要和墙壁尺寸一样）
     */
    private static final int ROAD_PIXEL = 16;

    /**
     * 墙壁的尺寸
     */
    private static final int WALL_PIXEL = 16;

    @Override
    public Mat drawMaze(Rectangle rectangle, int[][] maze) {
        init();

        // 矩形的长宽尺寸
        int matColsPixel = rectangle.getRoadHeight() * ROAD_PIXEL + WALL_PIXEL * (rectangle.getRoadHeight() + 1);
        int matRowsPixel = rectangle.getRoadWidth() * ROAD_PIXEL + WALL_PIXEL * (rectangle.getRoadWidth() + 1);
        Mat mat = Mat.zeros(matRowsPixel, matColsPixel, CvType.CV_8UC3);

        for (int i = 0; i < maze.length; i++) {
            // 计算左边竖轴上的偏移量
            int leftOffsetY = calcOffset(i);
            for (int j = 0; j < maze[i].length; j++) {
                // 计算左边横轴上的偏移量
                int leftOffsetX = calcOffset(j);

                // 绘制墙壁
                if (maze[i][j] == PointEnum.WALL.getType()) {
                    Imgproc.rectangle(mat,
                            // 矩形左上角
                            new Point(leftOffsetY, leftOffsetX),
                            // 矩形右下角
                            new Point(leftOffsetY + WALL_PIXEL, leftOffsetX + WALL_PIXEL),
                            new Scalar(0, 0, 0), -1,
                            8,
                            0);
                }

                // 绘制道路
                if (maze[i][j] == PointEnum.ROAD.getType()) {
                    Imgproc.rectangle(mat,
                            // 矩形左上角
                            new Point(leftOffsetY, leftOffsetX),
                            // 矩形右下角
                            new Point(leftOffsetY + ROAD_PIXEL, leftOffsetX + ROAD_PIXEL),
                            new Scalar(255, 255, 255), -1,
                            8,
                            0);
                }
            }
        }

        return mat;
    }

    /**
     * 根据单元格数量计算坐标上的偏移量
     *
     * @param count
     * @return
     */
    private int calcOffset(int count) {
        int offset = 0;
        // 如果填充了奇数块，墙壁比道路多一块
        if ((count % 2) == 1) {
            offset = ROAD_PIXEL + (count - 1) / 2 * (ROAD_PIXEL + WALL_PIXEL);
        }
        // 如果填充了偶数块，一半道路，一半墙壁
        if ((count % 2) == 0) {
            offset = count / 2 * (ROAD_PIXEL + WALL_PIXEL);
        }
        return offset;
    }
}
