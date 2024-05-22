package com.person.management.adapters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Adapter {


    private final ModelMapper mapper;

    public Adapter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <Source, Target> Target map(Source source, Class<Target> targetClass) {
        return mapper.map(source, targetClass);
    }

    public <Type> void updateTargetFromSource(Type source, Type target) {
        mapper.map(source, target);
    }
}