package com.ces3.proyectoces.dao;

import com.ces3.proyectoces.model.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoService {
    private List<Curso> cursos = new ArrayList<>();
    private int currentId = 1;

    public List<Curso> listarCursos() {
        return cursos;
    }

    public boolean agregarCurso(Curso nuevoCurso) {
        for (Curso c : cursos) {
            if (c.getCodigo().equals(nuevoCurso.getCodigo())) {
                return false;
            }
        }
        if (nuevoCurso.getCupoMaximo() <= 0) {
            return false;
        }
        nuevoCurso.setId(currentId++);
        cursos.add(nuevoCurso);
        return true;
    }

    public List<Curso> buscarPorFacultad(String facultad) {
        List<Curso> resultado = new ArrayList<>();
        for (Curso c : cursos) {
            if (c.getFacultad().equalsIgnoreCase(facultad)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

}
