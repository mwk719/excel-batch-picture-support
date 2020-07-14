package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.Parameter;

/**
 * @auther 喻场
 * @date 2020/7/217:57
 */
public class RelationShip {
    @Parameter("Id")
    private String id;
    @Parameter("Type")
    private String type;
    @Parameter("Target")
    private String target;
    public RelationShip(String id, String type, String target) {
        this.id = id;
        this.type = type;
        this.target = target;
    }
}
