package com.weeklab.weekbank.entities.DTOs;

import com.weeklab.weekbank.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    String name;
    int amount;
    LocalDateTime entryDate;
    UserRole role;
    List<DepositDTO> deposits;

}
