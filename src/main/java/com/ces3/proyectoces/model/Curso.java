package com.ces3.proyectoces.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    Integer id;
    String nombre;
    String codigo;
    String profesor;
    Integer cupoMaximo;
    Integer estudiantesInscritos;
    String facultad;
}
