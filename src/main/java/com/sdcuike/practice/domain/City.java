package com.sdcuike.practice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    private long              id;

    private String            name;

    private String            state;
}
