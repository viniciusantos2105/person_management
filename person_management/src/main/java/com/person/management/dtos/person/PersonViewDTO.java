package com.person.management.dtos.person;

import com.person.management.dtos.address.AddressViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonViewDTO {
    private Long personId;
    private String fullName;
    private String birthDate;
    private List<AddressViewDTO> addresses = new ArrayList<>();
}
