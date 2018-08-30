package com.zxk175.doc.generator;

import com.zxk175.doc.generator.service.GeneratorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * spring上下文初始化完成后-执行生成文档操作
 *
 * @author wwy
 */
@Log4j2
@Component
public class GeneratorDbDocBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private GeneratorService generatorService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            generatorService.generateDbDoc();
            log.info("生成数据库文档成功");
        } catch (Exception e) {
            log.error("生成数据库文档错误", e);
            return;
        }
    }
}