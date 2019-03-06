/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homologaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author JOHN EDWAR
 */
public class conexionBD {
      private Connection conex=null;
    private ResultSet rs =null;
    private Statement s=null;
    private PreparedStatement pst = null; //sss
    
    public static Connection newconexion() throws ClassNotFoundException{
          
        try{
            
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shuv","homologacionesBD", "facil");
            //JOptionPane.showMessageDialog(null, "Conexión establecida correctamente");
            
            if(con != null){
                System.out.println("Conectando a la base de datos....");
            }
            return con;
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
            System.out.println("Error de conexion");
            return null;
        }
    }
    public conexionBD() {
        try{
             Class.forName("org.postgresql.Driver");
             conex= DriverManager.getConnection("jdbc:postgresql://localhost:5432/shuv","homologacionesBD", "facil");
             System.out.println(" se hizo la conexion por aqui");
        }
        catch(Exception ex){
            System.out.println("Sorry error pendejjo");
        }
    }
//    public void sentencia(String sql){
//        
//        try {
//            s = conex.createStatement();
//            String codigo = "s";
//            int z = s.executeUpdate(sql);
//            
//            if (z==1){
//                   System.out.println("Se agrego exitosamente el registro");
//            }
//            else{
//                System.out.println(" error al ingresar registro ");
//            }
//        } catch (Exception e) {
//            System.out.println("Error de conexion gg");
//        }
//    }
    
     public void sentencia(String sql){
         
        System.out.println("Sentencia : " + sql + "\n"); // Declaracion en null del resultSet
        
        try { 
            pst = conex.prepareStatement(sql); //Preparo la sentencia sql a ejecutar
            int registros = pst.executeUpdate(); //Obtengo datos de consulta - Cuento los regs
            System.out.println("\nCantidad de registros afectados : " + registros);
            System.out.println("------------------------------------------------- \n");
            pst.close(); //Libero datos del PreparedStatement, tambien se libera ResultSet
        } catch (SQLException e) {
            e.printStackTrace(); // Capturo la excepcion en caso de error
        }
        
    }
       public void desconectar() {
        try {
//Cierro todas las conexiones con la base de datos
//Libero memoria y cierro las conexiones abiertas en objeto Connection
            conex.close();
        } catch (Exception e) {
// Controlo cualquier excepcion generada durante el cierre de la conexion
            e.printStackTrace();
        }
    }
}
