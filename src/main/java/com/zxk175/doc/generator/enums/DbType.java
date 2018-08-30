package com.zxk175.doc.generator.enums;

import com.zxk175.doc.generator.dao.MysqlDbInfoDao;

/**
 * 数据库类型
 *
 * @author wwy
 */
public enum DbType {

    /**
     * mysql
     */
    MYSQL(MysqlDbInfoDao.class);

    private Class dbInfoDaoImpl;

    DbType(Class dbInfoDaoImpl) {
        this.dbInfoDaoImpl = dbInfoDaoImpl;
    }

    public Class getDbInfoDaoImpl() {
        return dbInfoDaoImpl;
    }
}
