package com.sdcuike.practice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long              id;

    @Column(nullable = false)
    private String            name;

    @Column(nullable = false)
    private String            state;

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    protected City() {
    }
}
