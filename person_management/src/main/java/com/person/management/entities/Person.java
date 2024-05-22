package com.person.management.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long personId;
    private String fullName;
    private Date birthDate;
    private List<Address> addresses = new ArrayList<>();
}
