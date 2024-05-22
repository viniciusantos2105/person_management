package com.person.management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String street;
    private String postalCode;
    private String number;
    private String city;
    private String state;
    private Boolean isPrimary;
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonModel person;
}
