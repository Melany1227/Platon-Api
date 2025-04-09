package com.ces3.proyectoces.dao;

import com.ces3.proyectoces.model.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private List<Curso> cursos = new ArrayList<>();
    private int currentId = 1;

    public List<Curso> listarCursos() {
        return cursos;
    }
}
