package com.example.managesystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseUser {

    private Long userId;

    private String accountName;

    private String role;

}
