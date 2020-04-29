package com.lll.utils;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源集合管理类
 * 使用数据源之前必须先添加，然后指定使用哪个数据源
 * @author yangxiangfeng
 *
 */
public class DynamicDatasource extends AbstractRoutingDataSource {

    private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
    
    private final static Map<DataSourceEntity, String> dseMap = new HashMap<>();
    
	//spring注入需要set方法，不是必要的，可以注入也可以不注入
    public static void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        DynamicDatasource.dataSourceMap = dataSourceMap;
    }
    
	//获取数据源信息集合
    public static Map<DataSourceEntity, String> getDsemap() {
        return dseMap;
    }

	//检查是否包含指定id的数据源
    public static boolean checkDbKey(String dbKey){
        if(dataSourceMap.get(dbKey) != null)return true;
        return false;
    }
    //抽象方法，必须重写，用来判断使用哪个数据源
    @Override
    protected String determineCurrentLookupKey() {
        return DataSourceUtils.getDbKey();
    }
    
    /**
     * 对数据源的初始化方法，由于这里已经将数据源集合放在本类中，如果不重写将会由于父类参数为null而抛出异常。
     */
    @Override
    public void afterPropertiesSet() {}
    
    /**
     * 确定使用哪一个数据源
     * 这里不做null判断，因为是经过null判断后再进入的。
     */
    @Override
    protected DataSource determineTargetDataSource() {
        System.out.println("——使用数据源——");
        String dsKey = determineCurrentLookupKey();
        DataSource dds = dataSourceMap.get(dsKey);
        return dds;
    }

    /**
     * 添加数据源
     * 为了防止多线程添加同一个数据源，这里采用同步,同时会判断是否已存在
     * @param dbkey
     * @param ip
     * @param port
     * @param service 实例名
     * @param username
     * @param password
     * @return String 新建数据源对应的key，如果已经存在，则返回之前的key。
     */
    public synchronized String addDataSource(String dbkey, String ip,   int port, String service, String username, String password){
        DataSourceEntity d1 = new DataSourceEntity(ip, port, service, username);
        String value = dseMap.get(d1);
        if(dseMap.get(d1) != null){
            return value;//已存在则返回该数据源的id
        }
        
        DataSource ds = createDataSource2(ip, port, service, username, password);
        dataSourceMap.put(dbkey, ds);//存储数据源集合
        dseMap.put(d1, dbkey);//保存已经存储了哪些数据源
        
        return dbkey;
    }
    
    /**
     * 创建一个数据源
     * 
     * @param ip
     * @param port
     * @param service
     * @param username
     * @param password
     * @return
     */
    private DataSource createDataSource2(String ip, int port, String service, String username, String password) {
        BasicDataSource dds = new BasicDataSource();
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        dds.setUrl("jdbc:mysql://" + ip + ":" + port + "/" + service +"?useUnicode=true&characterEncoding=utf-8");
        dds.setUsername(username);
        dds.setPassword(password);
        return dds;
    }
    
}