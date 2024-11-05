package Dao;

import javax.naming.NamingException;

import clientes.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

