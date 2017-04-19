package com.sdcuike.practice.domain.dto;

import com.sdcuike.practice.domain.City;
import lombok.Data;

/**
 * Created by beaver on 2017/4/14.
 */
@Data
public class CityDto {
    private long id;
    
    private String name;
    
    private String state;
    
    
    public static void main(String[] args) {
        City city = new City();
        city.setId(12L);city.setName("na");city.setState("shandong");
        CityDtoMapper cityDtoMapper = new CityDtoMapperImpl();
        CityDto cityDto = cityDtoMapper.cityToDto(city);
        System.out.println(cityDto);
    }
    
}
