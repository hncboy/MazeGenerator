package com.hncboy.solver.entity;

import lombok.Data;
import org.opencv.core.Mat;

/**
 * @author hncboy
 * @date 2020/10/26 15:26
 * @description TODO
 */
@Data
public class BfsParam {

    private int rows;
    private int cols;
    private Mat colorMaze;
    private int step;

    /**
     * 起点位置
     */
    private int[] startPoint = new int[2];

    /**
     * 终点矩形区域
     */
    private int[][] endMat = new int[2][2];
}
