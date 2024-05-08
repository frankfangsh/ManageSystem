package com.example.managesystem.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class AddUserDTO {

    @NotBlank(message = "userId can not be null")
    private String userId;

    private Set<String> endpoint;

}
