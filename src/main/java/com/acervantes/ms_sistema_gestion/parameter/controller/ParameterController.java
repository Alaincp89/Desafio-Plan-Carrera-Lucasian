package com.acervantes.ms_sistema_gestion.parameter.controller;

import com.acervantes.ms_sistema_gestion.parameter.entity.Parameter;
import com.acervantes.ms_sistema_gestion.parameter.service.ParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parameters")
@Slf4j
public class ParameterController {
    private final ParameterService parameterService;

    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping
    public ResponseEntity<List<Parameter>> getAllParameters() {
        log.info("*********************************************************");
        log.info("********* INICIA MICROSERVICIO DE PARÁMETROS ************");
        log.info("*********************************************************");

        try {
            log.info("Iniciando operación para listar todos los parámetros");
            List<Parameter> parameters = parameterService.getAllParameters();
            log.info("Parámetros recuperados exitosamente");
            return ResponseEntity.ok(parameters);
        } catch (Exception e) {
            log.error("Error al recuperar la lista de parámetros", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Parameter> createParameter(@RequestBody Parameter parameter) {
        try {
            log.info("Iniciando operación para crear un nuevo parámetro");
            Parameter createdParameter = parameterService.createParameter(parameter);
            log.info("Parámetro creado exitosamente con ID: {}", createdParameter.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdParameter);
        } catch (Exception e) {
            log.error("Error al crear el parámetro", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parameter> getParameterById(@PathVariable Long id) {
        try {
            log.info("Buscando parámetro con ID: {}", id);
            Parameter parameter = parameterService.getParameterById(id);
            if (parameter != null) {
                log.info("Parámetro encontrado con ID: {}", id);
                return ResponseEntity.ok(parameter);
            } else {
                log.warn("Parámetro no encontrado con ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            log.error("Error al buscar el parámetro con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parameter> updateParameter(@PathVariable Long id, @RequestBody Parameter parameter) {
        try {
            log.info("Iniciando operación para actualizar el parámetro con ID: {}", id);
            Parameter updatedParameter = parameterService.updateParameter(id, parameter);
            log.info("Parámetro actualizado exitosamente con ID: {}", id);
            return ResponseEntity.ok(updatedParameter);
        } catch (Exception e) {
            log.error("Error al actualizar el parámetro con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParameter(@PathVariable Long id) {
        try {
            log.info("Iniciando operación para eliminar el parámetro con ID: {}", id);
            parameterService.deleteParameter(id);
            log.info("Parámetro eliminado exitosamente con ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar el parámetro con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
