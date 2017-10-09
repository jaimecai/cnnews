package com.cai.news.dao;

import com.cai.news.beans.Admin;

public interface AdminMapper {
    Admin selectByUsrNameAndPasswd(Admin admin);
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}