package com.lll.services.impl;

import javax.annotation.Resource;

import org.kings.em.ccl.datastructure.Dto;
import org.kings.em.ccl.datastructure.impl.BaseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lll.bean.User;
import com.lll.dao.UserDao;
import com.lll.services.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

  @Resource
  private UserDao userDao;

  public void addUser(Dto dto) {
    userDao.addUser(dto);
  }

}
