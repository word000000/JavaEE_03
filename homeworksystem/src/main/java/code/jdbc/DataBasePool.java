package code.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @Author:GQM
 * @Date:created in 21:03 2020/3/22
 * @Description:
 * @Modifyed_By:
 */
public class DataBasePool {

    private static HikariDataSource hikariDataSource;


    public static HikariDataSource getHikariDataSource(){
        if(null != hikariDataSource){
            return hikariDataSource;
        }


        synchronized (DataBasePool.class){
            String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
            String drivername = "com.mysql.cj.jdbc.Driver";
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setUsername("root");
            hikariConfig.setPassword("0016");
            hikariConfig.setDriverClassName(drivername);
            hikariConfig.setJdbcUrl(url);

            hikariDataSource = new HikariDataSource(hikariConfig);

            return hikariDataSource;
        }
    }

    public static void main(String[] args){
        int i = 3;
        while (i-->=0){
            getHikariDataSource();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
