package casasrurales;

import Conexion.Conexion;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tetem
 */
public class CasasRurales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 9;
        Scanner teclado = new Scanner(System.in);
        while (opcion != 0) {
            System.out.println("Menú de rural");
            System.out.println("********************************************");
            System.out.println("1. Buscar habitaciones que tengan un precio inferior al indicado por el usuario");
            System.out.println("2. Consultar el número de teléfono de un alojamiento indicado por el usuario.");
            System.out.println("3. Buscar todos los alojamientos que tengan alguna habitación con baño.");
            System.out.println("4. Consultar el número de habitaciones individuales de un determinado alojamiento.");
            System.out.println("5. Consultar el número de habitaciones dobles de un determinado alojamiento.");
            System.out.println("6. Consultar el número de habitaciones triples de un determinado alojamiento.");
            System.out.println("7. Consultar la dirección de un determinado alojamiento.");
            System.out.println("8. Mostrar todos los alojamientos de la comunidad, con su dirección y su teléfono.");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1: {
                    System.out.println("Introduce un precio");
                    int auxiliar = teclado.nextInt();
                    uno(auxiliar);
                    break;
                }

                case 9:
                    System.exit(0);
            }
        }

        /*
        result = query.executeQuery("SELECT * FROM oficinas");
       

         */
    }

    public static void uno(int auxiliar) {
        String db = "rural";
        Connection con;
        Statement query;
        ResultSet result;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + db + "?serverTimezone=UTC", "root", "Chula378!");
            query = con.createStatement();
            result = query.executeQuery("select idHabitacion,aseo,precio,tipoHabitacion from habitaciones inner join tipos on habitaciones.idTipo=tipos.idTipo where precio<" + auxiliar);
            while (result.next()) {
                System.out.print("Número de habitación:" + result.getString("idHabitacion"));
                if (result.getBoolean("aseo")) {
                    System.out.print(" Tiene aseo ");
                } else {
                    System.out.print(" No tiene aseo ");
                }
                System.out.print("Precio: " + result.getInt("Precio"));
                System.out.println(" Tipo de Habitación: " + result.getString("tipoHabitacion"));

            }
            result.close();
            query.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con " + db + ": " + ex.toString());
        }

    }

}
