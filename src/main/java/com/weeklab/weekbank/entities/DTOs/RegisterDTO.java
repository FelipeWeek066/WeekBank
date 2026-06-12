package com.weeklab.weekbank.entities.DTOs;

import com.weeklab.weekbank.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO implements Serializable
{
    private String login;
    private String password;
    private UserRole role;
}
