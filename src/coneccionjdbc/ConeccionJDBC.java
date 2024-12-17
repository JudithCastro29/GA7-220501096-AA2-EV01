package coneccionjdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConeccionJDBC {
    public static void main(String[] args) {
        String usuario = "root"; 
        String password = ""; 
        String url = "jdbc:mysql://localhost:3306/stockmaster"; 
        Connection conexion;
        Statement statement;
        ResultSet rs;

        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("¡Conexión establecida con éxito!");

            statement = conexion.createStatement();
                        
            // Insertar datos en la tabla Usuario (verifica que no haya duplicados en el idUsuario)
            String sqlInsertUsuario = "INSERT INTO Usuario (idUsuario, Nombre, Rol, Contraseña) VALUES "
                                    
                                    + "('u4', 'Luis Ropero', 'Jefe de planta', 'abcd');";
            statement.execute(sqlInsertUsuario);  
            System.out.println("Datos insertados en la tabla Usuario.");

            // Insertar datos en la tabla Cliente
            String sqlInsertCliente = "INSERT INTO Cliente (idCliente, Nombre, Direccion, Telefono, CorreoElectronico) VALUES "
                                    + "(1, 'Supermercado AlbaRosa', 'Calle 123', '123456789', 'albarosa@example.com'), "
                                    + "(2, 'Supermercado Surtimax', 'Calle 456', '987654321', 'surtimax@example.com');";
            statement.execute(sqlInsertCliente);
            System.out.println("Datos insertados en la tabla Cliente.");

            // Insertar datos en la tabla Producto
            String sqlInsertProducto = "INSERT INTO Producto (idProducto, Nombre, PrecioUnitario, StockMinimo, Ubicacion) VALUES "
                                      + "(1, 'Papa Ondulada', 1000, 50, 'A1'), "
                                      + "(2, 'Papa Fosforo', 1500, 30, 'B2');";
            statement.execute(sqlInsertProducto);
            System.out.println("Datos insertados en la tabla Producto.");

            // Insertar datos en la tabla Inventario
            String sqlInsertInventario = "INSERT INTO Inventario (idInventario, idProducto, CantidadEnStock, Usuario_idUsuario, Producto_idProducto) VALUES "
                                        + "(1, 1, 100, 'u1', 1), "
                                        + "(2, 2, 75, 'u2', 2);";
            statement.execute(sqlInsertInventario);
            System.out.println("Datos insertados en la tabla Inventario.");

            // Insertar datos en factura
            String sqlInsertFactura = "INSERT INTO Factura (idFactura, idCliente, FechaEmision) VALUES "
                                    + "(1, 1, '2024-12-17'), "
                                    + "(2, 2, '2024-12-18');";
            statement.execute(sqlInsertFactura);
            System.out.println("Datos insertados en la tabla Factura.");

            // Insertar datos en la tabla DetalleFactura
            String sqlInsertDetalleFactura = "INSERT INTO DetalleFactura (id, idFactura, idProducto, Cantidad, PrecioUnitario, Total) VALUES "
                                           + "(1, 1, 1, 5, 1000, 5000), "
                                           + "(2, 2, 2, 3, 1500, 4500);";
            statement.execute(sqlInsertDetalleFactura);
            System.out.println("Datos insertados en la tabla DetalleFactura.");
            
            // Leer y mostrar datos de las tablas
            System.out.println("\nLeyendo datos de la tabla Usuario...");
            rs = statement.executeQuery("SELECT * FROM Usuario");  
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("idUsuario") + ", Nombre: " + rs.getString("Nombre")
                                 + ", Rol: " + rs.getString("Rol"));
            }

            // Realizar operaciones adicionales (actualizar, eliminar)
            String sqlUpdateUsuario = "UPDATE Usuario SET Contraseña = 'nuevo1234' WHERE idUsuario = 'u2';";
            int rowsUpdated = statement.executeUpdate(sqlUpdateUsuario);
            if (rowsUpdated > 0) {
                System.out.println("¡Contraseña actualizada en la tabla Usuario!");
            }

            String sqlDeleteProducto = "DELETE FROM Producto WHERE idProducto = 2;";
            int rowsDeleted = statement.executeUpdate(sqlDeleteProducto);
            if (rowsDeleted > 0) {
                System.out.println("¡Producto eliminado de la tabla Producto!");
            }
            
            // Eliminar el usuario 'u4' de la tabla Usuario
            String sqlDeleteUsuario = "DELETE FROM Usuario WHERE idUsuario = 'u4';";
            int rowsDeletedUsuario = statement.executeUpdate(sqlDeleteUsuario);
            if (rowsDeletedUsuario > 0) {
                System.out.println("¡Usuario con id 'u4' eliminado de la tabla Usuario!");
            }

            // Cerrar conexión
            conexion.close();
            System.out.println("\nConexión cerrada.");

        } catch (SQLException ex) {
            Logger.getLogger(ConeccionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
