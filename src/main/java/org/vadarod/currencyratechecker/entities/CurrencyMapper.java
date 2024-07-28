package org.vadarod.currencyratechecker.entities;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {
    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    Currency fromCurrencyDtoToCurrency(CurrencyDto currencyDto);
    CurrencyDto fromCurrencyToCurrencyDto(Currency currency);
}
