package com.simple.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class User implements Serializable {

    public static final long serialVersionUID = 45568L;

    @Id
    private int userId;
    private String name;
    private String address;
}
