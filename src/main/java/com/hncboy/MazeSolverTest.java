package com.hncboy;

import com.hncboy.solver.MazeSolver;
import com.hncboy.solver.entity.RectangleMazeImageInfo;

/**
 * @author hncboy
 * @date 2020/10/26 15:16
 * @description 迷宫解决测试
 */
public class MazeSolverTest {

    public static void main(String[] args) {
        testSpotImage1();
        testSpotImage2();
        testSpotImage3();
    }

    private static void testSpotImage3() {
        RectangleMazeImageInfo rectangleMazeImageInfo = new RectangleMazeImageInfo();

        // 开始结束坐标可以通过 ps 测量
        String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\image\\";
        rectangleMazeImageInfo.setReadFilePath(dir+"1603699378854.png");
        rectangleMazeImageInfo.setWriteFileName(dir+"1603699378854_result.png");
        rectangleMazeImageInfo.setRoadPixel(16);
        int[] startPoint = new int[]{24, 8};
        rectangleMazeImageInfo.setStartPoint(startPoint);
        int[][] endMat = new int[][]{{960, 944}, {976, 960}};
        rectangleMazeImageInfo.setEndMat(endMat);

        new MazeSolver().solveRectangleMazeImage(rectangleMazeImageInfo);
    }

    private static void testSpotImage2() {
        RectangleMazeImageInfo rectangleMazeImageInfo = new RectangleMazeImageInfo();

        // 开始结束坐标可以通过 ps 测量
        String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\image\\spot\\";
        rectangleMazeImageInfo.setReadFilePath(dir+"spot_60_60.png");
        rectangleMazeImageInfo.setWriteFileName(dir+"spot_60_60_result.png");
        rectangleMazeImageInfo.setRoadPixel(16);
        int[] startPoint = new int[]{472, 8};
        rectangleMazeImageInfo.setStartPoint(startPoint);
        int[][] endMat = new int[][]{{480, 496}, {952, 962}};
        rectangleMazeImageInfo.setEndMat(endMat);

        new MazeSolver().solveRectangleMazeImage(rectangleMazeImageInfo);
    }

    private static void testSpotImage1() {
        RectangleMazeImageInfo rectangleMazeImageInfo = new RectangleMazeImageInfo();

        // 开始结束坐标可以通过 ps 测量
        String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\image\\spot\\";
        rectangleMazeImageInfo.setReadFilePath(dir+"spot_8_8.png");
        rectangleMazeImageInfo.setWriteFileName(dir+"spot_8_8_result.png");
        rectangleMazeImageInfo.setRoadPixel(16);
        int[] startPoint = new int[]{56, 8};
        rectangleMazeImageInfo.setStartPoint(startPoint);
        int[][] endMat = new int[][]{{65, 81}, {121, 130}};
        rectangleMazeImageInfo.setEndMat(endMat);

        new MazeSolver().solveRectangleMazeImage(rectangleMazeImageInfo);
    }
}
