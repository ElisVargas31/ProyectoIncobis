package webIncobis;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import Dao.UsuarioDAO;
import clientes.Cliente;
import clientes.Solicitud;


@WebServlet("/incobisServlet")
public class IncobisServlet extends HttpServlet {
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Acción recibida: " + action); // Para depurar

        if (action != null) {
            switch (action) {
                case "registrar":
                    registrarUsuario(request, response);
                    break;
                case "iniciarSesion":
                    consultarUsuario(request, response);
                    break;
                case "solicitud":
                guardarSolicitud(request,response);
                break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha especificado ninguna acción");
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String nombre = request.getParameter("nombreORazonSocial");
        String apellido = request.getParameter("apellido");
        String correoElectronico = request.getParameter("correoElectronico");
        String telefono = request.getParameter("telefono");
        String nit = request.getParameter("nit");
        String password = request.getParameter("password");

        Cliente usuario = new Cliente();
        usuario.setNombreORazonSocial(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setTelefono(telefono);
        usuario.setNit(nit);
        usuario.setPassword(password);

        boolean registroExitoso = usuarioDAO.registrarUsuario(usuario);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (registroExitoso) {
            out.println("Registro exitoso");
        } else {
            out.println("Error en el registro");
        }
    }

    private void consultarUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        Cliente usuario = usuarioDAO.obtenerUsuarioPorCorreoYPassword(correo, password);
        
        if (usuario != null) {
            // Almacena información del usuario en la sesión si es necesario
            request.getSession().setAttribute("usuario", usuario);
            
            // Redirige a la página incobis.html
            response.sendRedirect("incobis.html");
        } else {
            // Maneja la respuesta para usuario no encontrado
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("{\"error\":\"Usuario no encontrado\"}");
        }
    }

    private void guardarSolicitud(HttpServletRequest request, HttpServletResponse response) 
            throws IOException 
    {
        String experiencia = request.getParameter("experiencia"); // 7
        String cargo = request.getParameter("cargo");
        String otros = request.getParameter("comentarioAreaTexto");
        String tipocontrato = request.getParameter("tipo-contrato");
        String nivelprofesional = request.getParameter("profesion");
        String comentario = request.getParameter("comentario");	
        
        Solicitud solicitud=new Solicitud();
        solicitud.setCargo(cargo);
        solicitud.setComentario(comentario);
        solicitud.setExperiencia(experiencia);
        solicitud.setOtros(otros);
        solicitud.setNivel_profesion(nivelprofesional);
        solicitud.setTipo_de_contrato(tipocontrato);
        
    	
  boolean guardadoExitoso = usuarioDAO.guardarSolicitud(solicitud);
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  if (guardadoExitoso) {
      out.println("Registro exitoso");
  } else {
      out.println("Error en el registro");
  }
    }

}
