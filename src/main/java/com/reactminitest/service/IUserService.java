package com.reactminitest.service;

import com.reactminitest.model.User;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>{
    Optional<User> findByName(String name);
}
