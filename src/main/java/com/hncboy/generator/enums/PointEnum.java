package com.hncboy.generator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hncboy
 * @date 2020/10/23 16:34
 * @description 迷宫节点的枚举
 */
@Getter
@AllArgsConstructor
public enum PointEnum {

    /**
     * 道路
     */
    ROAD(0),

    /**
     * 墙壁
     */
    WALL(1);

    private final Integer type;
}
