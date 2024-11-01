package com.acervantes.ms_sistema_gestion.parameter.service;

import com.acervantes.ms_sistema_gestion.parameter.entity.Parameter;
import com.acervantes.ms_sistema_gestion.parameter.repository.ParameterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {
    private final ParameterRepository parameterRepository;

    public ParameterService(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    public List<Parameter> getAllParameters() {
        return parameterRepository.findAll();
    }

    public Parameter createParameter(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    public Parameter getParameterById(Long id) {
        return parameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parameter not found"));
    }

    public Parameter updateParameter(Long id, Parameter parameterDetails) {
        Parameter parameter = getParameterById(id);
        parameter.setTipoParametro(parameterDetails.getTipoParametro());
        parameter.setContexto(parameterDetails.getContexto());
        parameter.setDescripcion(parameterDetails.getDescripcion());
        parameter.setEstado(parameterDetails.getEstado());
        return parameterRepository.save(parameter);
    }

    public void deleteParameter(Long id) {
        Parameter parameter = getParameterById(id);
        parameterRepository.delete(parameter);
    }
}
