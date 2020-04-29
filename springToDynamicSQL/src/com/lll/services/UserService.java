package com.lll.services;

import org.kings.em.ccl.datastructure.Dto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

  public void addUser(Dto dto);

}
