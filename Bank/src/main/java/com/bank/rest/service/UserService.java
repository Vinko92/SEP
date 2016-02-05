package com.bank.rest.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bank.rest.model.User;

@Service("userService")
@Transactional
public class UserService extends GenericServiceImpl<User> {

}
