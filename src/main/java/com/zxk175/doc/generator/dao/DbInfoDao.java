package com.zxk175.doc.generator.dao;


import com.zxk175.doc.generator.entity.TableInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 获取数据库信息的dao接口
 *
 * @author wwy
 */
public interface DbInfoDao {

    /**
     * 获取数据库名称
     *
     * @return ignore
     */
    String dataBaseName();

    /**
     * 获取表的信息
     *
     * @return ignore
     */
    List<TableInfo> tableInfoList();

    /**
     * 设置jdbcTemplate
     *
     * @param jdbcTemplate ignore
     */
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
