package com.sdcuike.mybatis.auditor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by beaver on 2017/4/25.
 */
public abstract class AbstractAuditingEntity implements Serializable {
    
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime lastmodifiedTime;
    private String lastmodifiedBy;
    
    public String getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    public LocalDateTime getLastmodifiedTime() {
        return lastmodifiedTime;
    }
    
    public void setLastmodifiedTime(LocalDateTime lastmodifiedTime) {
        this.lastmodifiedTime = lastmodifiedTime;
    }
    
    public String getLastmodifiedBy() {
        return lastmodifiedBy;
    }
    
    public void setLastmodifiedBy(String lastmodifiedBy) {
        this.lastmodifiedBy = lastmodifiedBy;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    
}
