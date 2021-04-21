/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Conexion.AccesoDatos;
import DAO.Conexion.SNMPExceptions;
import Model.Barrio;
import Model.Canton;
import Model.Cliente;
import Model.Direccion;
import Model.Distrito;
import Model.Producto;
import Model.Provincia;
import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author Usuario
 */
@Named(value = "direccionDLL")
@Dependent
public class DireccionDLL {

  
    public DireccionDLL() {
    }
    
    
    
      public static LinkedList<Direccion> listaTodasDireccionCliente(long idCliente) throws SNMPExceptions{
        String select = "exec SeleccionarDirecCliente "+idCliente;
        LinkedList<Direccion> listaDirecciones = new LinkedList<Direccion>();

        try {
        
            
            AccesoDatos accesoDatos = new AccesoDatos();
            

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
                Direccion d=new Direccion();
                d.setId(rsPA.getInt("id"));
            
        
                d.setB(TraerBarrioPorId(rsPA.getInt("codProvincia"),rsPA.getInt("codCanton"),rsPA.getInt("codDistrito"),rsPA.getInt("codBarrio")));
                d.setSennas(rsPA.getString("Sennas"));
                d.setCliente((Cliente)UsuarioDLL.UsuarioXidentificacion(rsPA.getLong("idCliente")));
               
                 
               
                listaDirecciones.add(d);
                 

            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return listaDirecciones;
    }
      
      //traer provincia por id
      public static Provincia TraerProvinciaPorId(int id) throws SNMPExceptions{
        String select = "exec SeleccionarProvinciaPorid "+id;
       Provincia p=null;

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
                int idP=rsPA.getInt("Cod_provincia");
               String provincia=rsPA.getString("DSC_Provincia");
                p=new Provincia(idP,provincia );
            }
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return p;
      }
      
        //traer Canton por id
      public static Canton TraerCantonPorId(int idProvincia,int idCanton) throws SNMPExceptions{
        String select = "exec SeleccionarCantonPorid "+idProvincia+","+idCanton;
       Canton c=null;

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
               c=new Canton();
               c.setP(TraerProvinciaPorId(rsPA.getInt("Cod_Provincia")));
                c.setCodigo(rsPA.getInt("Cod_Canton"));
                c.setNombre(rsPA.getString("DSC_Canton"));
            }
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return c;
      }
      //traer distrito por ID
          public static Distrito TraerDistritoPorId(int idProvincia,int idCanton,int idDistrito) throws SNMPExceptions{
        String select = "exec SeleccionarDistritoPorid "+idProvincia+","+idCanton+","+idDistrito;
       Distrito d=null;

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
               d=new Distrito();
               d.setP(TraerProvinciaPorId(rsPA.getInt("Cod_Provincia")));
                d.setC(TraerCantonPorId(rsPA.getInt("Cod_Provincia"), rsPA.getInt("Cod_Canton")));
                d.setCodigo(rsPA.getInt("Cod_Distrito"));
                d.setNombre(rsPA.getString("DSC_Distrito"));
            }
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return d;
      }
      
          //traer Barrio por ID
             public static Barrio TraerBarrioPorId(int idProvincia,int idCanton,int idDistrito,int IdBarrio) throws SNMPExceptions{
        String select = "exec SeleccionarBarrioPorid "+idProvincia+","+idCanton+","+idDistrito+","+IdBarrio;
       Barrio b=null;

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
               b=new Barrio();
               b.setP(TraerProvinciaPorId(rsPA.getInt("Cod_Provincia")));
                b.setC(TraerCantonPorId(rsPA.getInt("Cod_Provincia"), rsPA.getInt("Cod_Canton")));
                b.setD(TraerDistritoPorId(rsPA.getInt("Cod_Provincia"), rsPA.getInt("Cod_Canton"), rsPA.getInt("Cod_Distrito")));
                b.setCodigo(rsPA.getInt("Cod_Barrio"));
                b.setNombre(rsPA.getString("DSC_Barrio"));
            }
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return b;
      }
      
    public static LinkedList<Provincia> Provincias() throws SNMPExceptions{
        String select = "select * from Provincia";
       LinkedList<Provincia> provincias=new LinkedList<>();

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
                Provincia p;
                int idP=rsPA.getInt("Cod_provincia");
               String provincia=rsPA.getString("DSC_Provincia");
                p=new Provincia(idP,provincia );
                provincias.add(p);
            }
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return provincias;
      }   
      
    public static LinkedList<Canton> cantones(int idProvincia) throws SNMPExceptions{
        String select = "select * from canton where cod_provincia="+idProvincia;
       LinkedList<Canton> cantones=new LinkedList<>();
       Canton c;
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
                
               c=new Canton();
               c.setP(TraerProvinciaPorId(rsPA.getInt("Cod_Provincia")));
                c.setCodigo(rsPA.getInt("Cod_Canton"));
                c.setNombre(rsPA.getString("DSC_Canton"));
                cantones.add(c);
            }
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return cantones;
      }
      
         public static LinkedList<Distrito> Distritos(int idProvincia,int idCanton) throws SNMPExceptions{
        String select = "select * from distrito where cod_provincia= "+idProvincia+" and cod_canton="+idCanton;
       LinkedList<Distrito> distritos=new LinkedList<>();
       Distrito d;

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
               d=new Distrito();
               d.setP(TraerProvinciaPorId(rsPA.getInt("Cod_Provincia")));
                d.setC(TraerCantonPorId(rsPA.getInt("Cod_Provincia"), rsPA.getInt("Cod_Canton")));
                d.setCodigo(rsPA.getInt("Cod_Distrito"));
                d.setNombre(rsPA.getString("DSC_Distrito"));
                distritos.add(d);
            }
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return distritos;
      }
       
           public static LinkedList<Barrio> Barrios(int idProvincia,int idCanton,int idDistrito) throws SNMPExceptions{
        String select = "select * from barrio where cod_provincia="+idProvincia+" and cod_canton="+idCanton+
                " and cod_distrito="+idDistrito;
        LinkedList<Barrio> barrios=new LinkedList<>();
       Barrio b;

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
        
            while (rsPA.next()) {
               b=new Barrio();
               b.setP(TraerProvinciaPorId(rsPA.getInt("Cod_Provincia")));
                b.setC(TraerCantonPorId(rsPA.getInt("Cod_Provincia"), rsPA.getInt("Cod_Canton")));
                b.setD(TraerDistritoPorId(rsPA.getInt("Cod_Provincia"), rsPA.getInt("Cod_Canton"), rsPA.getInt("Cod_Distrito")));
                b.setCodigo(rsPA.getInt("Cod_Barrio"));
                b.setNombre(rsPA.getString("DSC_Barrio"));
                barrios.add(b);
            }
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return barrios;
      }
           
           
           
           public static void InsertarDireccion(int Provincia,int canton,int distrito,int barrio,String sennas) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
               Usuario usuario=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
               
               String Insert="insert into Direccion (idCliente,codProvincia,codCanton,codDistrito,codbarrio,Sennas) values("+
                       usuario.getId()+","+ Provincia+","+canton+","+distrito+","+barrio+",'"+sennas+"')";
               AccesoDatos datos=new AccesoDatos();
               datos.ejecutaSQL(Insert);
             
           }
           public static void Editar(int direccion,int Provincia,int canton,int distrito,int barrio,String sennas) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
               String insert="update Direccion set codProvincia="+Provincia+
                       ",codCanton="+canton+",codDistrito="+distrito+",codbarrio="+barrio+
                       ",Sennas='"+sennas+"' where id="+direccion;
               AccesoDatos datos=new AccesoDatos();
               datos.ejecutaSQL(insert);
           }
           public static void Eliminar(int direccion) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
               String delete=" update Direccion set estado=0 where id="+direccion;
                 AccesoDatos datos=new AccesoDatos();
               datos.ejecutaSQL(delete);
           }
      
      
      
      
      

    
}
