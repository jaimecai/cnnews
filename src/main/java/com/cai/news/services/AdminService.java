package com.cai.news.services;

import com.cai.news.beans.Admin;
import com.cai.news.dao.AdminMapper;
import com.cai.news.utils.MyBatisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;

public class AdminService {
    public Admin login(Admin admin){
        SqlSession sqlSession= MyBatisUtil.getSqlSessionFactory().openSession();
        AdminMapper adminMapper=sqlSession.getMapper(AdminMapper.class);
        admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        Admin result=adminMapper.selectByUsrNameAndPasswd(admin);
        return result;
    }
}
