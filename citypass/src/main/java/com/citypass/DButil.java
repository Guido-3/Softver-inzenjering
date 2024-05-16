package com.citypass;

import java.sql.Connection;
import java.sql.DriverManager;

public class DButil {
    public static Connection open(){
        try {
            DBConfig config = new DBConfig();
            String url = config.getUrl();
            String user = config.getUser();
            String password = config.getPassword();

            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch(Exception ex){
            return null;
        }
    }
}
