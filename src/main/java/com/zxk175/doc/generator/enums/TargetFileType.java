package com.zxk175.doc.generator.enums;

import com.zxk175.doc.generator.service.impl.ExcelGeneratorServiceImpl;
import com.zxk175.doc.generator.service.impl.HtmlGeneratorServiceImpl;

/**
 * 生成文件类型
 *
 * @author wwy
 */
public enum TargetFileType {

    /**
     * excel文档
     */
    EXCEL(ExcelGeneratorServiceImpl.class),
    /**
     * html文档
     */
    HTML(HtmlGeneratorServiceImpl.class);

    
    private Class generatorServiceImpl;


    TargetFileType(Class generatorServiceImpl) {
        this.generatorServiceImpl = generatorServiceImpl;
    }

    public Class getGeneratorServiceImpl() {
        return generatorServiceImpl;
    }
}
