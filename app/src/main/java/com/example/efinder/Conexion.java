package com.example.efinder;
import android.Manifest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException
        ;

public class Conexion {

    private final String URL = "jdbc:mysql://tu_servidor:puerto/tu_base_de_datos";
    private final String USUARIO = "tu_usuario";
    private final String CONTRASEÑA = "tu_contraseña";

    public Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador JDBC", e);
        }

    }

}
