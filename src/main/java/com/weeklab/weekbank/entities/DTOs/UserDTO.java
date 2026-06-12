package com.weeklab.weekbank.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    String name;
    int amount;

}
