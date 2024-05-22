package com.person.management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    private String fullName;
    private Date birthDate;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<AddressModel> addressList;


}
