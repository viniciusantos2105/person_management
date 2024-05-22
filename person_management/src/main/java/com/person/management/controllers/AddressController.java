package com.person.management.controllers;

import com.person.management.dtos.address.AddressCreateDTO;
import com.person.management.dtos.address.AddressEditDTO;
import com.person.management.dtos.address.AddressViewDTO;
import com.person.management.services.AddressService;
import com.person.management.services.PersonAddressService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class AddressController {


    private final AddressService addressService;
    private final PersonAddressService personAddressService;

    public AddressController(AddressService addressService, PersonAddressService personAddressService) {
        this.addressService = addressService;
        this.personAddressService = personAddressService;
    }

    @Operation(summary = "Listagem de endereços por pessoa",
            description = "Este endpoint get, consiste na listagem de todos os endereços de uma pessoa",
            tags = {"Address"})
    @GetMapping("/{personId}/address")
    public ResponseEntity<List<AddressViewDTO>> findAddressByPersonId(@PathVariable Long personId) {
        List<AddressViewDTO> addressViewDTO = addressService.findAddressByPersonId(personId);
        return ResponseEntity.status(HttpStatus.OK).body(addressViewDTO);
    }

    @Operation(summary = "Busca de endereço por id",
            description = "Este endpoint get, consiste na busca de um endereço",
            tags = {"Address"})
    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressViewDTO> findByAddressId(@PathVariable Long addressId) {
        AddressViewDTO addressViewDTO = addressService.findByAddressId(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(addressViewDTO);
    }

    @Operation(summary = "Criação de endereço para pessoa",
            description = "Este endpoint post, consiste na criação de um novo endereço para uma pessoa",
            tags = {"Address"})
    @PostMapping("/{personId}/address")
    public ResponseEntity<AddressViewDTO> createAddress(@PathVariable Long personId, @RequestBody AddressCreateDTO addressCreateDTO) {
        AddressViewDTO address = personAddressService.createAddress(addressCreateDTO, personId);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @Operation(summary = "Edição de endereço",
            description = "Este endpoint put, consiste na atualização de um endereço",
            tags = {"Address"})
    @PutMapping("/address/{addressId}")
    public ResponseEntity<AddressViewDTO> editAddress(@PathVariable Long addressId, @RequestBody AddressEditDTO addressEditDTO) {
        AddressViewDTO address = addressService.editAddress(addressId, addressEditDTO);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @Operation(summary = "Delete de um endereço",
            description = "Este endpoint delete, consiste no delete de um endereço",
            tags = {"Address"})
    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
