package com.sdcuike.mybatis.pageable;

/**
 * Created by beaver on 2017/4/12.
 */
public abstract class Dialect {
    /**
     * 返回分页sql
     */
    public abstract String getLimitString(String sql, int offset, int limit);
    
    /**
     * 将sql转换为总记录数SQL
     *
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    public String getCountString(String sql) {
        return "select count(1) from (" + sql + ") tmp_count";
    }
}
