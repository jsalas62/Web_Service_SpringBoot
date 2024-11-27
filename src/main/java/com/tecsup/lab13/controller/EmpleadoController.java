package com.tecsup.lab13.controller;

import com.tecsup.lab13.Exeption.ResourceNotFoundException;
import com.tecsup.lab13.model.Empleado;
import com.tecsup.lab13.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")

public class EmpleadoController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("empleados")
    public List<Empleado> listarTodosLosEmpleados(){
        return empleadoRepository.findAll();
    }

    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable long id){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No exitste el empleado con el ID: "+ id));
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable long id, @RequestBody Empleado empleadoRequest){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No existe el empleado con el ID"+ id));

        empleado.setNombre(empleadoRequest.getNombre());
        empleado.setApellidos(empleadoRequest.getApellidos());
        empleado.setEmail(empleadoRequest.getEmail());

        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable long id){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No existe el empleado con el ID"+ id));

        empleadoRepository.delete(empleado);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
