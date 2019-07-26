package com.zxk175.doc.generator.service.impl;

import com.zxk175.doc.generator.entity.TableInfo;
import com.zxk175.doc.generator.utils.FreeMarkerUtils;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * 生成文档服务-html实现
 *
 * @author wwy
 */
public final class HtmlGeneratorServiceImpl extends AbstractGeneratorServiceImpl {

    @Override
    public void generateDbDoc() throws Exception {
        String dataBaseName = dbInfoDao.dataBaseName();
        List<TableInfo> tableInfoList = dbInfoDao.tableInfoList();
        // 获取模板
        String templateFileName = "htmlTemplate.html";
        Template template = FreeMarkerUtils.getTemplate(templateFileName);
        File dir = new File(targetFileDir);
        FileUtils.forceMkdir(dir);
        Random random = new Random();
        String filename = DateFormatUtils.format(new Date(), "yyyy-MM-dd_hh-mm-ss") + random.nextInt(10) + ".html";
        File file = new File(dir, filename);
        Map<String, Object> map = new HashMap<>(2);
        map.put("tableInfoList", tableInfoList);
        map.put("dataBaseName", dataBaseName);
        
        // 根据模板生成文件
        if (template != null) {
            template.process(map, new FileWriter(file));
        }
    }
}
