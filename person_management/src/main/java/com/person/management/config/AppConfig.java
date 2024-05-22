package com.person.management.config;

import com.person.management.dtos.person.PersonCreateDTO;
import com.person.management.dtos.person.PersonEditDTO;
import com.person.management.dtos.person.PersonViewDTO;
import com.person.management.entities.Person;
import com.person.management.exceptions.commons.DateInvalidException;
import com.person.management.models.PersonModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);


        mapper.createTypeMap(PersonCreateDTO.class, Person.class)
                .addMappings(mapping -> mapping.using(toDate).map(PersonCreateDTO::getBirthDate, Person::setBirthDate));
        mapper.createTypeMap(PersonEditDTO.class, Person.class)
                .addMappings(mapping -> mapping.using(toDate).map(PersonEditDTO::getBirthDate, Person::setBirthDate));
        mapper.createTypeMap(PersonModel.class, PersonViewDTO.class)
                .addMappings(mapping -> mapping.map(PersonModel::getAddressList, PersonViewDTO::setAddresses))
                .addMappings(mapping -> mapping.using(fromDate).map(PersonModel::getBirthDate, PersonViewDTO::setBirthDate));
        mapper.createTypeMap(PersonModel.class, Person.class)
                .addMappings(mapping -> mapping.map(PersonModel::getAddressList, Person::setAddresses));
        mapper.createTypeMap(Person.class, PersonViewDTO.class)
                .addMappings(mapping -> mapping.using(fromDate).map(Person::getBirthDate, PersonViewDTO::setBirthDate));

        return mapper;
    }

    private final Converter<String, Date> toDate = context -> {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(context.getSource());
        } catch (ParseException e) {
            throw DateInvalidException.createDateInvalidException("Data inválida");
        }
    };

    private final Converter<Date, String> fromDate = context -> new SimpleDateFormat("dd/MM/yyyy").format(context.getSource());
}
