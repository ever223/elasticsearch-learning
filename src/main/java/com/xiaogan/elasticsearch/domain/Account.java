package com.xiaogan.elasticsearch.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaoo_gan
 * @date 2017-03-19
 */
@Setter
@Getter
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Account {
    private String _id;
    private int age;
    private int balance;
    private String accountNumber;
    private String firstname;
    private String lastname;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}
