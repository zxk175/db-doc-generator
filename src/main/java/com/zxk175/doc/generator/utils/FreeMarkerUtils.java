package com.zxk175.doc.generator.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * freeMaker 工具类
 *
 * @author wwy
 */
public class FreeMarkerUtils {

    private static final String COLON = ":";


    /**
     * 通过文件名加载模版
     *
     * @param ftlName
     * @return
     * @throws Exception
     */
    public static Template getTemplate(String ftlName) throws Exception {
        try {
            // 通过FreeMaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
            cfg.setEncoding(Locale.CHINA, "UTF-8");
            // 设定去哪里读取相应的ftl模板文件
            ClassPathResource classPathResource = new ClassPathResource("template");
            cfg.setDirectoryForTemplateLoading(classPathResource.getFile());
            // 在模板文件目录中找到名称为name的文件
            return cfg.getTemplate(ftlName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getClassResources() {
        String path = (String.valueOf(Thread.currentThread()
                .getContextClassLoader()
                .getResource("")))
                .replaceAll("file:/", "")
                .replaceAll("%20", " ")
                .trim();
        if (path.indexOf(COLON) != 1) {
            path = File.separator + path;
        }

        return path;
    }
}
