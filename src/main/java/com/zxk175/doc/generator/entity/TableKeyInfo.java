package com.zxk175.doc.generator.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 表的索引信息
 *
 * @author wwy
 */
@Data
public class TableKeyInfo {

    /**
     * 索引名称
     */
    private String name;

    /**
     * 包含那些字段
     */
    private List<String> columns;

    /**
     * 是否唯一
     */
    private Boolean unique;

    /**
     * 索引类型
     */
    private String indexType;

    /**
     * 索引注释
     */
    private String indexComment;


    public TableKeyInfo() {

    }

    public String getColumnCombine() {
        return StringUtils.join(columns, ",");
    }
}
