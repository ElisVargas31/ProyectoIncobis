package Dao;

import javax.naming.NamingException;

import clientes.Cliente;
import clientes.Solicitud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UsuarioDAO {
    private ConexionBD conexionBD;

    public UsuarioDAO() {
        try {
            this.conexionBD = new ConexionBD(); // Inicializa la conexión aquí
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(Cliente usuario) {
        Connection connection = null;
        boolean registroExitoso = false;

        try {
            connection = ConexionBD.conectar();
            String sql = "INSERT INTO cliente (nombre_razon_social, apellido, correo, telefono, nit, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNombreORazonSocial());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setString(3, usuario.getCorreoElectronico());
            preparedStatement.setString(4, usuario.getTelefono());
            preparedStatement.setString(5, usuario.getNit());
            preparedStatement.setString(6, usuario.getPassword());

            int filasAfectadas = preparedStatement.executeUpdate();
            registroExitoso = filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                conexionBD.cerrarConexion(connection);
            }
        }
        return registroExitoso;
    }

    // Método para obtener el usuario por correo y password
    public Cliente obtenerUsuarioPorCorreoYPassword(String correo, String password) {
    	Cliente usuario = null;
        Connection connection = null;

        try {
            connection = ConexionBD.conectar();
            String sql = "SELECT * FROM cliente WHERE correo = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usuario = new Cliente();
                usuario.setNombreORazonSocial(resultSet.getString("nombre_razon_social"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setCorreoElectronico(resultSet.getString("correo"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setNit(resultSet.getString("nit"));
                usuario.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                conexionBD.cerrarConexion(connection);
            }
        }
        return usuario;
    }
    
 // Método para registrar un nuevo usuario
    public boolean guardarSolicitud(Solicitud solicitud) {
        Connection connection = null;
        boolean guardadoExitoso = false;

        try {
            connection = ConexionBD.conectar();
            String sql = "INSERT INTO solicitud (fecha, estado, cargo, experiencia, cliente_id,"
            		+ " tipo_contrato, nivel_profesional, otros, comentario)"
            		+ " VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf( new Date()));
            preparedStatement.setString(2, "ACTIVO");
            preparedStatement.setString(3, solicitud.getCargo());
            preparedStatement.setString(4, solicitud.getExperiencia());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, solicitud.getTipo_de_contrato());
            preparedStatement.setString(7, solicitud.getNivel_profesion());
            preparedStatement.setString(8, "Otros");
            preparedStatement.setString(9, solicitud.getComentario());

            int filasAfectadas = preparedStatement.executeUpdate();
            guardadoExitoso = filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                conexionBD.cerrarConexion(connection);
            }
        }
        return guardadoExitoso;
    }
}

