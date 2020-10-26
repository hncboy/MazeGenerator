package com.hncboy.generator.algorithm;

import com.hncboy.generator.enums.PointEnum;
import com.hncboy.generator.shape.Rectangle;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hncboy
 * @date 2020/10/23 16:10
 * @description 随机 Prim 算法
 */
public class RandomizedPrim implements ICreatedAlgorithm {

    @Override
    public int[][] createMaze(Rectangle rectangle) {
        // 定义整个迷宫的宽度和高度
        int width = 2 * rectangle.getRoadWidth() + 1;
        int height = 2 * rectangle.getRoadHeight() + 1;

        // 构建迷宫基础矩形
        int[][] mazeRect = new int[height][width];

        // 初始化迷宫墙壁
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mazeRect[i][j] = PointEnum.WALL.getType();
            }
        }

        // 设置起点
        mazeRect[1][0] = PointEnum.ROAD.getType();

        // 设置终点
        mazeRect[height - 2][width - 1] = PointEnum.ROAD.getType();

        // 上下左右 4 个移动方向
        int[][] moves = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        // 存放需要搜索的节点
        List<MazePoint> searchList = new ArrayList<>();

        // 从起点周围的 (1,1) 节点，非边界节点开始搜索
        searchList.add(new MazePoint(1, 1, 1, 1));

        // 不断搜索直到列表里没有墙
        Random random = new Random();
        while (!searchList.isEmpty()) {
            // 随机获取一个列表节点进行搜索
            MazePoint mazePoint = searchList.remove(random.nextInt(searchList.size()));

            // 当前节点
            int currX = mazePoint.getX();
            int currY = mazePoint.getY();

            // 设置当前节点为道路节点
            mazeRect[currY][currX] = PointEnum.ROAD.getType();
            mazeRect[mazePoint.getDigY()][mazePoint.getDigX()] = PointEnum.ROAD.getType();

            // 向不同方向移动
            for (int[] move : moves) {
                // 下一个节点位置
                int nextX = currX + move[0] * 2;
                int nextY = currY + move[1] * 2;

                // 移出边界
                if (nextX < 0 || nextX > width - 1 || nextY < 0 || nextY > height - 1) {
                    continue;
                }

                // 连通迷宫
                if (mazeRect[nextY][nextX] == PointEnum.ROAD.getType()) {
                    continue;
                }

                if (!isInSearchList(searchList, nextX, nextY)) {
                    searchList.add(new MazePoint(nextX, nextY, currX + move[0], currY + move[1]));
                }
            }
        }

        return mazeRect;
    }

    /**
     * 判断点是否存在搜索列表中
     *
     * @param searchList
     * @param x
     * @param y
     * @return
     */
    private boolean isInSearchList(List<MazePoint> searchList, int x, int y) {
        for (MazePoint point : searchList) {
            if (point.getY() == y && point.getX() == x) {
                return true;
            }
        }
        return false;
    }

    @Data
    @AllArgsConstructor
    private static class MazePoint {

        private int x;

        private int y;

        private int digX;

        private int digY;
    }

}
