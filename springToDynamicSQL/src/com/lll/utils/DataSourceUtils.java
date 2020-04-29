package com.lll.utils;
/**
 * 	切换数据源的工具类
 * @author HY
 *
 */
public class DataSourceUtils {

	private static final ThreadLocal<String> local = new ThreadLocal<String>();

	public static String getDbKey() {
		return local.get();
	}

	public static void setDbKey(String dbKey) {
        if(DynamicDatasource.checkDbKey(dbKey)){
            local.set(dbKey);
        } else {
            throw new NullPointerException("不存在"+dbKey+"的数据源！");
        }
    }

	public static void clear() {
		local.remove();
	}
}
