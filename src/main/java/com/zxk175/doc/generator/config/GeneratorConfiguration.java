package com.zxk175.doc.generator.config;

import com.zxk175.doc.generator.dao.DbInfoDao;
import com.zxk175.doc.generator.enums.DbType;
import com.zxk175.doc.generator.enums.TargetFileType;
import com.zxk175.doc.generator.service.GeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 生成器配置
 *
 * @author wwy
 */
@Configuration
@AllArgsConstructor
public class GeneratorConfiguration {

    private JdbcTemplate jdbcTemplate;


    @Bean
    public DbInfoDao dbInfoDao() throws InstantiationException, IllegalAccessException {
        DbInfoDao dbInfoDao = (DbInfoDao) DbType.MYSQL.getDbInfoDaoImpl().newInstance();
        dbInfoDao.setJdbcTemplate(jdbcTemplate);
        return dbInfoDao;
    }

    @Bean
    public GeneratorService generatorService(DbInfoDao dbInfoDao) throws InstantiationException, IllegalAccessException {
        GeneratorService generatorService = (GeneratorService) TargetFileType.HTML.getGeneratorServiceImpl().newInstance();
        generatorService.setDbInfoDao(dbInfoDao);
        generatorService.setTargetFileDir("doc");
        return generatorService;
    }
}
