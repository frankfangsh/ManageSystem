package com.example.managesystem.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.example.managesystem.query.AddUserDTO;
import com.example.managesystem.service.IUserService;
import com.example.managesystem.utils.BusinessException;
import com.example.managesystem.utils.TLocalHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserServiceImpl implements IUserService {

    private static final String ADMIN = "admin";

    private static final String PATH = "User.txt";

    @Override
    public void addUser(AddUserDTO addUserDTO) {
        if (!ADMIN.equals(TLocalHelper.getUserInfo().getRole())) {
            throw new BusinessException("No access to this endpoint.");
        }

        String json = JSONUtil.toJsonStr(addUserDTO);
        FileUtil.writeUtf8String(json, getFilePath());
    }

    @Override
    public void addUserResource(String resource) {
        if (!ADMIN.equals(TLocalHelper.getUserInfo().getRole())) {
            throw new BusinessException("No access to this endpoint.");
        }

        String content = FileUtil.readUtf8String(getFilePath());
        if (!StringUtils.isEmpty(content)) {
            AddUserDTO addUserDTO = JSONUtil.toBean(content, AddUserDTO.class);
            if (CollUtil.isEmpty(addUserDTO.getEndpoint())) {
                addUserDTO.setEndpoint(Stream.of(resource).collect(Collectors.toSet()));
            } else {
                addUserDTO.getEndpoint().add(resource);
            }
            String json = JSONUtil.toJsonStr(addUserDTO);
            FileUtil.writeUtf8String(json, getFilePath());
        }
    }

    private String getFilePath() {
        File file = new File(PATH);
        return file.getAbsolutePath();
    }
}
