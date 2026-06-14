package com.weeklab.weekbank.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDTO implements Serializable {
    private int amount;
    private String payer;
    private String payee;
    private String description;
    private LocalDateTime date;
}
