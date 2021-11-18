package com.sxh.ts.entity;

import java.util.Date;

/**
 * @author sxh
 * @date 2021/11/18
 */
public class TransactionDemo {
    private String tUid;

    private Date updateDate;

    private String updateUser;

    private Date createDate;

    private String createUser;

    public TransactionDemo(String tUid, Date updateDate, String updateUser, Date createDate, String createUser) {
        this.tUid = tUid;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
        this.createDate = createDate;
        this.createUser = createUser;
    }

    public TransactionDemo() {
    }

    public String gettUid() {
        return tUid;
    }

    public void settUid(String tUid) {
        this.tUid = tUid;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
