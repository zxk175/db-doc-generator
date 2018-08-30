package com.zxk175.doc.generator.dao.impl;

import com.zxk175.doc.generator.dao.DbInfoDao;
import com.zxk175.doc.generator.entity.TableInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 获取数据库信息的dao实现-抽象基类
 *
 * @author wwy
 */
public abstract class AbstractDbInfoDao implements DbInfoDao {

    protected JdbcTemplate jdbcTemplate;


    @Override
    public String dataBaseName() {
        return null;
    }

    @Override
    public List<TableInfo> tableInfoList() {
        return null;
    }

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
