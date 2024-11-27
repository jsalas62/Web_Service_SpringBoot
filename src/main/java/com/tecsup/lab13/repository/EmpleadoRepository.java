package com.tecsup.lab13.repository;

import com.tecsup.lab13.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository <Empleado, Long> {

}
