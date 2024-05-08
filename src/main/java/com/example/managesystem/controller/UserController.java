package com.example.managesystem.controller;

import com.example.managesystem.query.AddUserDTO;
import com.example.managesystem.service.IUserService;
import com.example.managesystem.utils.BaseResponse;
import com.example.managesystem.utils.TLocalHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final IUserService userService;

    /**
     * Test URL
     *
     * @return
     */
    @GetMapping("/helloWord")
    public BaseResponse<String> helloWord() {
        return BaseResponse.ok("HelloWord");
    }

    /**
     * Decode Header Token
     *
     * @return
     */
    @GetMapping("/decodeHeader")
    public BaseResponse<String> decodeHeader() {
        return BaseResponse.ok(TLocalHelper.getUserInfo());
    }

    /**
     * Add User
     *
     * @return
     */
    @PostMapping("/admin/addUser")
    public BaseResponse addUser(@Valid @RequestBody AddUserDTO addUserDTO) {
        userService.addUser(addUserDTO);
        return BaseResponse.ok();
    }

    /**
     * Add Resource
     *
     * @return
     */
    @GetMapping("/user/{resource}")
    public BaseResponse addUserResource(@PathVariable String resource) {
        userService.addUserResource(resource);
        return BaseResponse.ok();
    }
}
