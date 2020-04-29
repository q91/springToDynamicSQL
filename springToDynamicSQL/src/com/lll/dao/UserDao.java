package com.lll.dao;


import org.kings.em.ccl.datastructure.Dto;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public interface UserDao {
  public void addUser(Dto dto);
}
