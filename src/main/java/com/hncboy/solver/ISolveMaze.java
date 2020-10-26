package com.hncboy.solver;

import com.hncboy.solver.entity.RectangleMazeImageInfo;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * @author hncboy
 * @date 2020/10/26 15:05
 * @description 迷宫解决接口
 */
public interface ISolveMaze {

    default void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * 解决本系统生成的迷宫
     * @param maze
     * @return
     */
    Mat solveSystemMaze(int[][] maze);

    /**
     * 矩形迷宫图片识别路线
     * @param rectangleMazeImageInfo
     */
    void solveRectangleMazeImage(RectangleMazeImageInfo rectangleMazeImageInfo);
}
