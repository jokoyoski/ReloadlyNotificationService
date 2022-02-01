package com.repository.factories;


import com.main.model.AppSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class AppSettingQuery {

    @Autowired
    private DataSource dataSource;

    private SimpleJdbcCall jdbcCall;

    public AppSettingQuery(DataSource dataSource){
        this.dataSource = dataSource;
    }


    public List<AppSetting> getSettings(){

        try {

            this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("usp_Get_AppSettings").returningResultSet("app", BeanPropertyRowMapper.newInstance(AppSetting.class));

            return (List<AppSetting>)this.jdbcCall.execute().get("app");

        } catch (Exception e) {
            System.err.println(e);
            throw  (e);
        }
    }

    public AppSetting getSetting(String key){

        try {

            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("key", key);

            this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("usp_Get_AppSetting").returningResultSet("app", BeanPropertyRowMapper.newInstance(AppSetting.class));

            var res = (List<AppSetting>)this.jdbcCall.execute(param).get("app");

            return (res != null && res.size() > 0)?res.get(0):null;

        } catch (Exception e) {
            System.err.println(e);
            throw  (e);
        }
    }

    public void addSetting(AppSetting appSetting){

        try {

            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("key", appSetting.getKey())
                    .addValue("value", appSetting.getValue());

            this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("usp_Insert_AppSetting");

            this.jdbcCall.execute(param);

        } catch (Exception e) {
            System.err.println(e);
            throw  (e);
        }
    }

    public void updateSetting(AppSetting appSetting){

        try {

            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("id", appSetting.getId())
                    .addValue("key", appSetting.getKey())
                    .addValue("value", appSetting.getValue());

            this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("usp_Update_AppSetting");

            this.jdbcCall.execute(param);

        } catch (Exception e) {
            System.err.println(e);
            throw  (e);
        }
    }

    public void deleteSetting(int id){

        try {

            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("id", id);

            this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("usp_Delete_AppSetting");

            this.jdbcCall.execute(param);

        } catch (Exception e) {
            System.err.println(e);
            throw  (e);
        }
    }
}

