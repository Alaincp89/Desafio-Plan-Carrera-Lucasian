package com.acervantes.ms_sistema_gestion.parameter.usecase;

import com.acervantes.ms_sistema_gestion.parameter.entity.Parameter;
import com.acervantes.ms_sistema_gestion.parameter.repository.ParameterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterUseCase {
    private final ParameterRepository repository;

    public ParameterUseCase(ParameterRepository repository) {
        this.repository = repository;
    }

    public List<Parameter> getAllParameters() {
        return repository.findAll();
    }

    public Parameter createParameter(Parameter parameter) {
        return repository.save(parameter);
    }


}