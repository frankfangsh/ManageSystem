package com.example.managesystem.service;

import com.example.managesystem.query.AddUserDTO;


public interface IUserService {

    void addUser(AddUserDTO addUserDTO);

    void addUserResource(String resource);
}
