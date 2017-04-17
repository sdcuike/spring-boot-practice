package com.sdcuike.practice.domain.dto;

import com.sdcuike.practice.domain.City;
import org.mapstruct.Mapper;

/**
 * Created by beaver on 2017/4/14.
 */
@Mapper
public interface CityDtoMapper {
    CityDto cityToDto(City city);
}
