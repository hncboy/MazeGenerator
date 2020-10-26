package com.hncboy.generator.draw;

import com.hncboy.generator.shape.Rectangle;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * @author hncboy
 * @date 2020/10/26 11:27
 * @description 绘制迷宫接口
 */
public interface IDrawMaze {

    default void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * 绘制迷宫
     * @param rectangle
     * @param maze
     * @return
     */
    Mat drawMaze(Rectangle rectangle, int[][] maze);
}
