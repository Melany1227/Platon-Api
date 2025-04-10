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

@WebServlet(name = "curso", urlPatterns = "/curso/*")
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Curso> lista = servicio.listarCursos();
            JSONArray jsonArray = new JSONArray();
            for (Curso curso : lista) {
                JSONObject obj = new JSONObject();
                obj.put("id", curso.getId());
                obj.put("nombre", curso.getNombre());
                obj.put("codigo", curso.getCodigo());
                obj.put("profesor", curso.getProfesor());
                obj.put("cupoMaximo", curso.getCupoMaximo());
                obj.put("estudiantesInscritos", curso.getEstudiantesInscritos());
                obj.put("facultad", curso.getFacultad());
                jsonArray.put(obj);
            }
            resp.getWriter().write(jsonArray.toString());
        } else if (pathInfo.startsWith("/facultad")) {
            String facultad = req.getParameter("nombre");
            if (facultad != null) {
                List<Curso> lista = servicio.buscarPorFacultad(facultad);
                if (lista.isEmpty()) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    JSONObject respuesta = new JSONObject();
                    respuesta.put("mensaje", "No se encontraron cursos para la facultad: " + facultad);
                    resp.getWriter().write(respuesta.toString());
                    return;
                }

                JSONArray jsonArray = new JSONArray();
                for (Curso curso : lista) {
                    JSONObject obj = new JSONObject();
                    obj.put("id", curso.getId());
                    obj.put("nombre", curso.getNombre());
                    obj.put("codigo", curso.getCodigo());
                    obj.put("profesor", curso.getProfesor());
                    obj.put("cupoMaximo", curso.getCupoMaximo());
                    obj.put("estudiantesInscritos", curso.getEstudiantesInscritos());
                    obj.put("facultad", curso.getFacultad());
                    jsonArray.put(obj);
                }
                resp.getWriter().write(jsonArray.toString());
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Debe proporcionar el parámetro 'nombre'\"}");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"error\": \"Ruta no válida\"}");
        }
    }

}
