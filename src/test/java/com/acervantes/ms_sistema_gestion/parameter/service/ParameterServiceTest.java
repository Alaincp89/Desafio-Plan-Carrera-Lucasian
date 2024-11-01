package com.acervantes.ms_sistema_gestion.parameter.service;

import com.acervantes.ms_sistema_gestion.parameter.entity.Parameter;
import com.acervantes.ms_sistema_gestion.parameter.repository.ParameterRepository;
import com.acervantes.ms_sistema_gestion.parameter.usecase.ParameterUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ParameterUseCaseTest {

    @Mock
    private ParameterRepository parameterRepository;

    @InjectMocks
    private ParameterUseCase parameterUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllParameters() {
        // Configuración de los datos de prueba
        Parameter parameter1 = new Parameter();
        parameter1.setId(1L);
        parameter1.setTipoParametro("limiteCreditoCliente");
        parameter1.setContexto("Comercial");
        parameter1.setDescripcion("Límite de crédito en pesos Colombianos asignado a clientes del segmento corporativo.");
        parameter1.setEstado("Activo");

        Parameter parameter2 = new Parameter();
        parameter2.setId(2L);
        parameter2.setTipoParametro("DescuentoCliente");
        parameter2.setContexto("Comercial");
        parameter2.setDescripcion("Descuento empleado a todos los clientes por temporada Navidad.");
        parameter2.setEstado("Activo");


        List<Parameter> parameters = Arrays.asList(parameter1, parameter2);
        when(parameterRepository.findAll()).thenReturn(parameters);


        List<Parameter> result = parameterUseCase.getAllParameters();

        assertEquals(2, result.size());
        verify(parameterRepository, times(1)).findAll();
    }

    @Test
    void testCreateParameter() {

        Parameter parameter = new Parameter();
        parameter.setId(1L);
        parameter.setTipoParametro("limiteCreditoCliente");
        parameter.setContexto("Comercial");
        parameter.setDescripcion("Límite de crédito en pesos Colombianos asignado a clientes del segmento corporativo.");
        parameter.setEstado("Activo");


        when(parameterRepository.save(parameter)).thenReturn(parameter);


        Parameter result = parameterUseCase.createParameter(parameter);

        assertEquals(1L, result.getId(), "El ID debería ser 1");
        assertEquals("limiteCreditoCliente", result.getTipoParametro());
        assertEquals("Comercial", result.getContexto());
        assertEquals("Límite de crédito en pesos Colombianos asignado a clientes del segmento corporativo.", result.getDescripcion());
        assertEquals("Activo", result.getEstado());

        verify(parameterRepository, times(1)).save(parameter);
    }
}
