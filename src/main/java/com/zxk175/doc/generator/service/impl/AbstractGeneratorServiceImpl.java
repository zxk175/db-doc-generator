package com.zxk175.doc.generator.service.impl;

import com.zxk175.doc.generator.dao.DbInfoDao;
import com.zxk175.doc.generator.service.GeneratorService;

/**
 * 文档生成服务-抽象基类
 *
 * @author wwy
 */
public abstract class AbstractGeneratorServiceImpl implements GeneratorService {

    DbInfoDao dbInfoDao;
    String targetFileDir;


    @Override
    public void generateDbDoc() throws Exception {
    }

    @Override
    public void setDbInfoDao(DbInfoDao dbInfoDao) {
        this.dbInfoDao = dbInfoDao;
    }

    @Override
    public void setTargetFileDir(String targetFileDir) {
        this.targetFileDir = targetFileDir;
    }
}
