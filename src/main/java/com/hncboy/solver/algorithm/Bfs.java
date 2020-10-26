package com.hncboy.solver.algorithm;

import com.hncboy.solver.entity.BfsParam;
import com.hncboy.solver.entity.SolvePoint;
import org.opencv.core.Mat;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author hncboy
 * @date 2020/10/26 15:16
 * @description Bfs 解决方案
 */
public class Bfs {

    public static SolvePoint bfs(BfsParam bfsParam) {
        int step = bfsParam.getStep();
        Mat colorMaze = bfsParam.getColorMaze();
        int cols = bfsParam.getCols();
        int rows = bfsParam.getRows();
        int[][] endMat = bfsParam.getEndMat();

        // 定义起点
        SolvePoint startPoint = new SolvePoint(bfsParam.getStartPoint()[0], bfsParam.getStartPoint()[1]);
        colorMaze.put(startPoint.getY(), startPoint.getX(), 0, 255, 0);

        // 判断该点有没有访问过
        boolean[][] visit = new boolean[cols][rows];
        for (int i = 0; i < cols; i += step) {
            for (int j = 0; j < rows; j += step) {
                visit[i][j] = false;
            }
        }

        // 从起点开始
        visit[startPoint.getX()][startPoint.getY()] = true;

        SolvePoint successSolvePoint = getEndPoint(endMat);

        // 遍历所有点
        Queue<SolvePoint> queue = new LinkedList<>();
        queue.offer(startPoint);
        SolvePoint solvePoint = null;
        while (!queue.isEmpty()) {
            solvePoint = queue.poll();
            visit[solvePoint.getX()][solvePoint.getY()] = true;

            // 如果是终点
            if (isReachEnd(solvePoint, endMat)) {
                System.out.println("success");
                successSolvePoint = solvePoint;
                break;
            }

            // 往左
            if (solvePoint.getX() - step >= 0) {
                // 如果当前点未访问且是可以经过的路径
                if (!visit[solvePoint.getX() - step][solvePoint.getY()] && colorMaze.get(solvePoint.getY(), solvePoint.getX() - step)[0] == 255) {
                    queue.offer(new SolvePoint(solvePoint.getX() - step, solvePoint.getY(), solvePoint));
                    visit[solvePoint.getX() - step][solvePoint.getY()] = true;
                }
            }


            // 上
            if (solvePoint.getY() - step >= 0) {
                if (!visit[solvePoint.getX()][solvePoint.getY() - step] && colorMaze.get(solvePoint.getY() - step, solvePoint.getX())[0] == 255) {
                    queue.offer(new SolvePoint(solvePoint.getX(), solvePoint.getY() - step, solvePoint));
                    visit[solvePoint.getX()][solvePoint.getY() - step] = true;
                }
            }

            // 往右
            if (solvePoint.getX() + step < cols) {
                if (!visit[solvePoint.getX() + step][solvePoint.getY()] && colorMaze.get(solvePoint.getY(), solvePoint.getX() + step)[0] == 255) {
                    queue.offer(new SolvePoint(solvePoint.getX() + step, solvePoint.getY(), solvePoint));
                    visit[solvePoint.getX() + step][solvePoint.getY()] = true;
                }
            }

            // 往下
            if (solvePoint.getY() + step < rows) {
                if (!visit[solvePoint.getX()][solvePoint.getY() + step] && colorMaze.get(solvePoint.getY() + step, solvePoint.getX())[0] == 255) {
                    queue.offer(new SolvePoint(solvePoint.getX(), solvePoint.getY() + step, solvePoint));
                    visit[solvePoint.getX()][solvePoint.getY() + step] = true;
                }
            }
        }

        if (successSolvePoint.getPrev() == null) {
            System.out.println("fail");
            successSolvePoint = solvePoint;
        }

        return successSolvePoint;
    }

    /**
     * 判断是否到达迷宫终点区域
     *
     * @param solvePoint
     * @return
     */
    private static boolean isReachEnd(SolvePoint solvePoint, int[][] endMat) {
        return solvePoint.getX() >= endMat[0][0] && solvePoint.getX() <= endMat[0][1] && solvePoint.getY() >= endMat[1][0] && solvePoint.getY() <= endMat[1][1];
    }

    /**
     * 获取终点
     *
     * @return
     */
    private static SolvePoint getEndPoint(int[][] endMat) {
        // 取矩形的中点
        int endX = (endMat[0][0] + endMat[0][1]) / 2;
        int endY = (endMat[1][0] + endMat[1][1]) / 2;
        return new SolvePoint(endX, endY);
    }
}
