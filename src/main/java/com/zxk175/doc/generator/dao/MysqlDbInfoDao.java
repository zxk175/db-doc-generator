package com.zxk175.doc.generator.dao;

import com.zxk175.doc.generator.dao.impl.AbstractDbInfoDao;
import com.zxk175.doc.generator.entity.TableFieldInfo;
import com.zxk175.doc.generator.entity.TableInfo;
import com.zxk175.doc.generator.entity.TableKeyInfo;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 获取mysql数据库信息
 *
 * @author wwy
 */
public final class MysqlDbInfoDao extends AbstractDbInfoDao {

    @Override
    public String dataBaseName() {
        return jdbcTemplate.queryForObject("select database()", String.class);
    }

    @Override
    public List<TableInfo> tableInfoList() {
        String tablesSql = "select table_name,table_comment from information_schema.tables where table_schema = database()";
        List<TableInfo> tableInfoList = jdbcTemplate.query(tablesSql, new TableInfoRowMapper());

        String columnsSql = "select COLUMN_NAME, COLUMN_COMMENT,COLUMN_DEFAULT,IS_NULLABLE,COLUMN_TYPE,COLUMN_KEY,EXTRA from information_schema.columns where table_schema = database() and table_name = ?";
        String keysSql = "show keys from ";
        tableInfoList.forEach((tableInfo) -> {
            Object[] params = new Object[]{tableInfo.getTableName()};
            List<TableFieldInfo> fields = jdbcTemplate.query(columnsSql, params, new TableFieldInfoRowMapper());

            tableInfo.setFields(fields);
            List<Map<String, Object>> rawKeyInfoList = jdbcTemplate.query(keysSql + tableInfo.getTableName(), new ColumnMapRowMapper());

            Map<String, TableKeyInfo> keyMap = new HashMap<>(5);
            for (Map<String, Object> rawKeyInfo : rawKeyInfoList) {
                TableKeyInfo tableKeyInfo = keyMap.get(rawKeyInfo.get("Key_name").toString());
                String columnName = rawKeyInfo.get("Column_name").toString();
                if (tableKeyInfo == null) {
                    tableKeyInfo = new TableKeyInfo();
                    ArrayList<String> columns = new ArrayList<>();
                    columns.add(columnName);
                    tableKeyInfo.setColumns(columns);
                    tableKeyInfo.setIndexComment(rawKeyInfo.get("Index_comment").toString());
                    tableKeyInfo.setIndexType(rawKeyInfo.get("Index_type").toString());
                    tableKeyInfo.setName(rawKeyInfo.get("Key_name").toString());
                    tableKeyInfo.setUnique("0".equals(rawKeyInfo.get("Non_unique").toString()));
                } else {
                    tableKeyInfo.getColumns().add(columnName);
                }
                keyMap.put(rawKeyInfo.get("Key_name").toString(), tableKeyInfo);
            }
            tableInfo.setKeys(new ArrayList<>(keyMap.values()));
        });

        return tableInfoList;
    }

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static class TableInfoRowMapper implements RowMapper<TableInfo> {
        @Override
        public TableInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            String tableName = rs.getString(1);
            String remark = rs.getString(2);
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableRemark(remark);
            tableInfo.setTableName(tableName);
            return tableInfo;
        }
    }

    public static class TableFieldInfoRowMapper implements RowMapper<TableFieldInfo> {
        @Override
        public TableFieldInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            TableFieldInfo tableFieldInfo = new TableFieldInfo();
            tableFieldInfo.setDefaultValue(rs.getString("COLUMN_DEFAULT"));
            tableFieldInfo.setExtra(rs.getString("EXTRA"));
            tableFieldInfo.setField(rs.getString("COLUMN_NAME"));
            tableFieldInfo.setKey(rs.getString("COLUMN_KEY"));
            tableFieldInfo.setNullAble(rs.getString("IS_NULLABLE"));
            tableFieldInfo.setType(rs.getString("COLUMN_TYPE"));
            tableFieldInfo.setRemark(rs.getString("COLUMN_COMMENT"));
            if (tableFieldInfo.getDefaultValue() == null) {
                tableFieldInfo.setDefaultValue("无");
            }

            return tableFieldInfo;
        }
    }
}
