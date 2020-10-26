package com.hncboy.solver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hncboy
 * @date 2020/10/26 15:15
 * @description 迷宫解决坐标点
 */
@Data
@AllArgsConstructor
public class SolvePoint {

    private int x;

    private int y;

    private SolvePoint prev;

    public SolvePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
