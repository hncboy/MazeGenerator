package com.hncboy;

import com.hncboy.generator.algorithm.RandomizedPrim;
import com.hncboy.generator.draw.MazeDrawer;
import com.hncboy.generator.shape.Rectangle;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @author hncboy
 * @date 2020/10/23 14:00
 * @description 迷宫生成
 */
public class MazeGenerator {

    public static void main(String[] args) {
        // 定义迷宫矩形
        Rectangle rectangle = new Rectangle();
        rectangle.setRoadHeight(10);
        rectangle.setRoadWidth(10);
        // 创建迷宫
        int[][] maze = new RandomizedPrim().createMaze(rectangle);
        // 绘制迷宫
        Mat mat = new MazeDrawer().drawMaze(rectangle, maze);
        // 生成迷宫图片
        long time = System.currentTimeMillis();
        Imgcodecs.imwrite(System.getProperty("user.dir") + "\\src\\main\\resources\\image\\" + time + ".png", mat);
    }
}
