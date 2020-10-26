package com.hncboy.solver.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hncboy
 * @date 2020/10/26 15:06
 * @description 矩形迷宫图片信息
 */
@Data
public class RectangleMazeImageInfo {

    /**
     * 读取图片路径
     */
    private String readFilePath;

    /**
     * 保存图片途径
     */
    private String writeFileName;

    /**
     * 每个道路长度（像素）
     */
    private int roadPixel;

    /**
     * 起点位置
     */
    private int[] startPoint = new int[2];

    /**
     * 终点矩形区域
     */
    private int[][] endMat = new int[2][2];
}
