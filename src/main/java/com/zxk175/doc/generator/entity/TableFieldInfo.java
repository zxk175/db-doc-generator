package com.zxk175.doc.generator.entity;

import lombok.Data;

/**
 * 列信息
 *
 * @author wwy
 */
@Data
public class TableFieldInfo {

    /**
     * 列名
     */
    private String field;

    /***
     * 类型
     */
    private String type;

    /***
     * 是否能为空
     */
    private String nullAble;

    /***
     * 键
     */
    private String key;

    /***
     * 默认值
     */
    private String defaultValue;

    /***
     * 额外信息
     */
    private String extra;

    /***
     * 备注信息
     */
    private String remark;
}
