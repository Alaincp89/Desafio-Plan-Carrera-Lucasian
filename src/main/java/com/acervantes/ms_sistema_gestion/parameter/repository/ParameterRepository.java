package com.acervantes.ms_sistema_gestion.parameter.repository;

import com.acervantes.ms_sistema_gestion.parameter.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
}
