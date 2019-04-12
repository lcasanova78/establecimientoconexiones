/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tetem
 */
public class Conexion {

    private String db = "rural";
    private Connection con;
    private Statement query;
    private ResultSet result;
    private String consulta;

    public Conexion() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + db + "?serverTimezone=UTC", "root", "Chula378!");
 
        } catch (Exception ex) {

        }
    }


    public void setQuery(String aux) {
        try {
            this.consulta=aux;
            this.query = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public ResultSet getResult() {
        try {
            result = query.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
         return result;
    }
    public void close(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
