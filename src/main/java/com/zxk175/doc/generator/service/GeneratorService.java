package com.zxk175.doc.generator.service;

import com.zxk175.doc.generator.dao.DbInfoDao;

/**
 * 生成文件-服务
 *
 * @author wwy
 */
public interface GeneratorService {

    /**
     * 生成数据库文档
     *
     * @throws Exception ignore
     */
    void generateDbDoc() throws Exception;

    /**
     * 设置数据库信息Dao
     *
     * @param dbInfoDao ignore
     */
    void setDbInfoDao(DbInfoDao dbInfoDao);

    /**
     * 设置目标目录
     *
     * @param targetFileDir ignore
     */
    void setTargetFileDir(String targetFileDir);
}