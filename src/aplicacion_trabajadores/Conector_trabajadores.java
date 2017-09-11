package aplicacion_trabajadores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author familia_p1
 */
public class Conector_trabajadores {
     //String url="E:/Documents and Settings/familia_p1/Escritorio/bases de datos/base_trabajadores.db";
    //String url="E:/Jesus/sistemas/bases de datos/base_trabajadores.db";
      String url="./base_datos_sqlite/base_trabajadores.db";
    Connection connect;
    public void connect(){
        
        try {
             connect = DriverManager.getConnection("jdbc:sqlite:"+url);
             if (connect!=null) {
                 System.out.println("Conectado");
             }else{
                 System.out.println("Conexión nula");
             }
        }catch (SQLException ex) {
             System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }
    }
    public void close(){
        try {
               connect.close();
        } catch (SQLException ex) {
               Logger.getLogger(Conector_trabajadores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveTrabajador(Trabajador trabajador){
        
        try {
            PreparedStatement st = connect.prepareStatement("insert into datos_trabajador(id, nombre,direccion,telefono,sueldo,region) values (?,?,?,?,?,?)");
            st.setInt(1, trabajador.getId());
            st.setString(2, trabajador.getNombre());
            st.setString(3, trabajador.getDireccion());
            st.setString(4, trabajador.getTelefono());
            st.setDouble(5, trabajador.getSueldo());
            st.setString(6, trabajador.getRegion());
            
            st.execute();
            System.out.println("ingresados");
        } catch (SQLException ex) {
            
            System.err.println(ex.getMessage());
        }
 
    }
    
    public void deleteTrabajador(int identificacion) {
        try{
        PreparedStatement st=connect.prepareStatement("delete from datos_trabajador where id=?");
        st.setInt(1, identificacion);
        st.execute();}
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    
    public ResultSet mostrarTrabajadores(int identificacion){
        ResultSet result = null;
        try {
            PreparedStatement st = connect.prepareStatement("select * from datos_trabajador WHERE id=?");
            st.setInt(1, identificacion);
            result = st.executeQuery();
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
        
    }
    public ResultSet verificarid(int identificacion){
        ResultSet result = null;
        try {
            PreparedStatement st = connect.prepareStatement("select id from datos_trabajador WHERE id='identificacion'");
            result = st.executeQuery();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }
    
    public ResultSet tomar_tabla(){
        ResultSet result=null;
        try{
            PreparedStatement st=connect.prepareStatement("select * from datos_trabajador"); 
            result = st.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return result;
    }
    
}

