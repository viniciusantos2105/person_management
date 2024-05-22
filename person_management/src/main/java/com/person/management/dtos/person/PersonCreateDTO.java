package com.person.management.dtos.person;

import com.person.management.dtos.address.AddressCreateDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateDTO {

    @NotBlank(message = "Nome não pode ser vazio'")
    private String fullName;
    @NotBlank(message = "Data de nascimento não pode ser vazia")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de nascimento inválida, use  dd/MM/yyyy")
    private String birthDate;
    private AddressCreateDTO address;
}
