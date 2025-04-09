package com.ces3.proyectoces.servlet;

import com.ces3.proyectoces.dao.CursoService;
import com.ces3.proyectoces.model.Curso;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "curso", urlPatterns = "/curso")
public class CursoServlet extends HttpServlet {
    private CursoService servicio = new CursoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            sb.append(linea);
        }

        try {
            JSONObject json = new JSONObject(sb.toString());

            String nombre = json.getString("nombre");
            String codigo = json.getString("codigo");
            String profesor = json.getString("profesor");
            int cupoMaximo = json.getInt("cupoMaximo");
            int estudiantesInscritos = json.getInt("estudiantesInscritos");
            String facultad = json.getString("facultad");

            Curso curso = new Curso(0, nombre, codigo, profesor, cupoMaximo, estudiantesInscritos, facultad);
            boolean agregado = servicio.agregarCurso(curso);

            resp.setContentType("application/json");
            if (agregado) {
                JSONObject respuesta = new JSONObject();
                respuesta.put("mensaje", "Curso agregado exitosamente.");
                respuesta.put("id", curso.getId());
                resp.getWriter().write(respuesta.toString());
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Código duplicado o cupo inválido\"}");
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"JSON malformado\"}");
        }
    }


}
