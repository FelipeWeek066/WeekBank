package com.weeklab.weekbank.entities.DTOs.mappers;

import com.weeklab.weekbank.entities.DTOs.DepositDTO;
import com.weeklab.weekbank.entities.Deposit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface DepositMapper {
    DepositMapper INSTANCE = Mappers.getMapper(DepositMapper.class);


    @Mapping(target = "payer", source = "payer.name")
    @Mapping(target = "payee", source = "payee.name")
    DepositDTO depositToDTO(Deposit deposit);

    List<DepositDTO> depositsToDTOs(List<Deposit> deposits);
}
