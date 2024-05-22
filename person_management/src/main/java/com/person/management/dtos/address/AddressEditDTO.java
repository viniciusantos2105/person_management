package com.person.management.dtos.address;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEditDTO {

    @NotBlank(message = "Rua é obrigatória")
    private String street;
    @NotBlank(message = "CEP é obrigatório")
    private String postalCode;
    @NotBlank(message = "Número é obrigatório")
    private String number;
    @NotBlank(message = "Cidade é obrigatória")
    private String city;
    @NotBlank(message = "Estado é obrigatório")
    private String state;
    private Boolean isPrimary;
}
