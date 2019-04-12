package casasrurales;

import Conexion.Conexion;
import java.sql.*;
import java.util.Scanner;

/**
 * Solo están controladas las excepciones de SQL, el resto de excepciones no lo están.
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
            System.out.println("9. Salir");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1: {
                    System.out.println("Introduce un precio");
                    int auxiliar = teclado.nextInt();
                    uno(auxiliar);
                    break;
                }
                case 2: {
                    dos();
                    break;
                }
                case 3: {
                    tres();
                    break;
                }
                case 4: {
                    cuatro();
                    break;
                }
                case 5: {
                    cinco();
                    break;
                }
                case 6: {
                    seis();
                    break;
                }
                case 7: {
                    siete();
                    break;
                }
                case 8: {
                    ocho();
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
        try {
            Conexion cx = new Conexion("select idHabitacion,aseo,precio,tipoHabitacion,nombre"
                    + " from habitaciones inner join tipos on habitaciones.idTipo=tipos.idTipo "
                    + "inner join alojamientos on alojamientos.id=habitaciones.idAlojamiento where precio<" + auxiliar);
            while (cx.getResult().next()) {
                System.out.print("Nombre hotel: " + cx.getResult().getString("nombre"));
                System.out.print(" Número de habitación:" + cx.getResult().getString("idHabitacion"));
                if (cx.getResult().getBoolean("aseo")) {
                    System.out.print(" Tiene aseo ");
                } else {
                    System.out.print(" No tiene aseo ");
                }
                System.out.print("Precio: " + cx.getResult().getInt("Precio"));
                System.out.println(" Tipo de Habitación: " + cx.getResult().getString("tipoHabitacion"));

            }
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos" + ex.toString());
        }

    }

    public static void dos() {
        Scanner teclado = new Scanner(System.in);
        try {
            Conexion cx = new Conexion("Select * from alojamientos");
            while (cx.getResult().next()) {
                System.out.print(cx.getResult().getString("id"));
                System.out.println(".  " + cx.getResult().getString("nombre"));
            }
            System.out.println("Elije el número de alojamiento");
            int numero = teclado.nextInt();
            cx.setQuery("select telefono,nombre from alojamientos where id=" + numero);
            cx.getResult().next();
            System.out.println("El teléfono del alojamiento " + cx.getResult().getString("nombre") + " es: " + cx.getResult().getInt("telefono"));
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos " + ex.toString());
        }

    }

    public static void tres() {
        try {
            Conexion cx = new Conexion("Select alojamientos.nombre from alojamientos inner join habitaciones "
                    + "on alojamientos.id=habitaciones.idAlojamiento where habitaciones.aseo=1");
            while (cx.getResult().next()) {
                System.out.println(cx.getResult().getString("nombre"));
            }
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos " + ex.toString());
        }
    }

    public static void cuatro() {
        Scanner teclado = new Scanner(System.in);
        try {
            Conexion cx = new Conexion("Select * from alojamientos");
            while (cx.getResult().next()) {
                System.out.print(cx.getResult().getString("id"));
                System.out.println(".  " + cx.getResult().getString("nombre"));
            }
            System.out.println("Elije el número de alojamiento");
            int numero = teclado.nextInt();
            cx.setQuery("select count(idHabitacion) from habitaciones inner join alojamientos on habitaciones.idAlojamiento=alojamientos.id "
                    + "inner join tipos on habitaciones.idTipo=tipos.idTipo where tipos.tipoHabitacion='individual' and alojamientos.id="+numero);
            cx.getResult().next();
            System.out.println("El hotel tiene "+cx.getResult().getInt("count(idHabitacion)")+" habitacione individuales");
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos " + ex.toString());
        }
    }
    public static void cinco() {
        Scanner teclado = new Scanner(System.in);
        try {
            Conexion cx = new Conexion("Select * from alojamientos");
            while (cx.getResult().next()) {
                System.out.print(cx.getResult().getString("id"));
                System.out.println(".  " + cx.getResult().getString("nombre"));
            }
            System.out.println("Elije el número de alojamiento");
            int numero = teclado.nextInt();
            cx.setQuery("select count(idHabitacion) from habitaciones inner join alojamientos on habitaciones.idAlojamiento=alojamientos.id "
                    + "inner join tipos on habitaciones.idTipo=tipos.idTipo where tipos.tipoHabitacion='doble' and alojamientos.id="+numero);
            cx.getResult().next();
            System.out.println("El hotel tiene "+cx.getResult().getInt("count(idHabitacion)")+" habitacione dobles");
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos " + ex.toString());
        }
    }
    public static void seis() {
        Scanner teclado = new Scanner(System.in);
        try {
            Conexion cx = new Conexion("Select * from alojamientos");
            while (cx.getResult().next()) {
                System.out.print(cx.getResult().getString("id"));
                System.out.println(".  " + cx.getResult().getString("nombre"));
            }
            System.out.println("Elije el número de alojamiento");
            int numero = teclado.nextInt();
            cx.setQuery("select count(idHabitacion) from habitaciones inner join alojamientos on habitaciones.idAlojamiento=alojamientos.id "
                    + "inner join tipos on habitaciones.idTipo=tipos.idTipo where tipos.tipoHabitacion='triple' and alojamientos.id="+numero);
            cx.getResult().next();
            System.out.println("El hotel tiene "+cx.getResult().getInt("count(idHabitacion)")+" habitacione triples");
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos " + ex.toString());
        }
    }
     public static void siete() {
        Scanner teclado = new Scanner(System.in);
        try {
            Conexion cx = new Conexion("Select * from alojamientos");
            while (cx.getResult().next()) {
                System.out.print(cx.getResult().getString("id"));
                System.out.println(".  " + cx.getResult().getString("nombre"));
            }
            System.out.println("Elije el número de alojamiento");
            int numero = teclado.nextInt();
            cx.setQuery("select direccion,nombre from alojamientos where id=" + numero);
            cx.getResult().next();
            System.out.println("La direccion del alojamiento " + cx.getResult().getString("nombre") + " es: " + cx.getResult().getString("direccion"));
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos " + ex.toString());
        }

    }
     public static void ocho() {
        Scanner teclado = new Scanner(System.in);
        try {
            Conexion cx = new Conexion("Select * from alojamientos");
            while (cx.getResult().next()) {
                System.out.print(cx.getResult().getString("id"));
                System.out.print(".  " + cx.getResult().getString("nombre"));
                System.out.print(" "+cx.getResult().getString("direccion"));
                System.out.println(" "+cx.getResult().getString("telefono"));
            }
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos " + ex.toString());
        }

    }

}
