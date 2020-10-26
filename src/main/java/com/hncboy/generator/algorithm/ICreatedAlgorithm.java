package com.hncboy.generator.algorithm;

import com.hncboy.generator.shape.Rectangle;

/**
 * @author hncboy
 * @date 2020/10/23 16:04
 * @description 迷宫创建算法策略
 */
public interface ICreatedAlgorithm {

    /**
     * 创建迷宫
     *
     * @param rectangle
     * @return
     */
    int[][] createMaze(Rectangle rectangle);
}
