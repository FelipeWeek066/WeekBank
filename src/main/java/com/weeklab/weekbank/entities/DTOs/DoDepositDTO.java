package com.weeklab.weekbank.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoDepositDTO implements Serializable {
        private String payee;
        private int amount;
        private String description;

}
