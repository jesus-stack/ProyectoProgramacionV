
/*
 * To change this license header, choose License Headers in Project Propert

* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DAO.Conexion.AccesoDatos;
import DAO.Conexion.SNMPExceptions;
import Model.Cliente;
import Model.Funcionario;
import Model.Tipo.TipoUsuario;
import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author Usuario
 */
public class UsuarioDLL {
    
    
    
 public static int insertarUsuario(Usuario usuario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
 String procedure1="insert into usuario (id) values ("+usuario.getId()+")";
     AccesoDatos datos=new AccesoDatos();
     return datos.ejecutaSQL(procedure1);
 
 
 }
 
 
 
 
 public static void insertarCliente(Cliente cliente) throws NamingException, SNMPExceptions, SQLException, ClassNotFoundException{
     
 cliente.setTipo(TipoUsuarioDLL.traerTipoUsuarioPorDescripcion("Cliente"));

 String procedure="exec InsertarCliente "+((Cliente)cliente).getIdentificacion()+", '"+((Cliente)cliente).getNombre()+"', '"+((Cliente)cliente).getSegundoNombre()+"', '"
        +((Cliente)cliente).getApellido()+"', '"+((Cliente)cliente).getSegundoApellido()+"', '"+((Cliente)cliente).getCorreo()+"', "+((Cliente)cliente).getTelefono()+
         ", '"+((Cliente)cliente).getContrasenna()+"', "+((Cliente)cliente).getTipo().getId();
 
 
 AccesoDatos datos=new AccesoDatos();

   datos.ejecutaSQL(procedure);
 
 
 

 
 }
    
 
 
 //tipos de usuarios
 public static LinkedList<TipoUsuario> listaTipoUsuario() throws SNMPExceptions{
    String select = "";
        LinkedList<TipoUsuario> listaEstado = new LinkedList<TipoUsuario>();

        try {
        
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "exec SeleccionarTiposUsuario";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int id = rsPA.getInt("id");
                String descr = rsPA.getString(""
                        + "descripcion");

                listaEstado.add(new TipoUsuario(id, descr));
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
        return listaEstado;
    }
    
 public static LinkedList<Usuario> listaUsuarios() throws SNMPExceptions{
      String select = "";
        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        Usuario user=new Usuario();

        try {
        
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "exec [SelecionarTodosUsuarios]";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
             user.setId(rsPA.getLong("id"));
             user.setContrasenna(rsPA.getString("contrasenna"));
             user.setEstado(rsPA.getInt("estado")==1);
             user.setTipo(new TipoUsuario(rsPA.getInt("tipoUsuario"),rsPA.getString("descripcion")));
             lista.add(user);
               

             
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
        return lista;
 }
 
     public static Usuario UsuarioXidentificacion(long id) throws SNMPExceptions{
    String select = "exec SeleccionaUsuarioXIdentificacion "+id;
        Usuario usuario =null;

        try {
        
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
         
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos 
             while (rsPA.next()) {
           
            if(TraerTipoUsuarioPorId(rsPA.getInt("tipoUsuario")).getId()==2){
                usuario=new Cliente();
                
            ((Cliente)usuario).setNombre(rsPA.getString("nombre"));
            ((Cliente)usuario).setSegundoNombre(rsPA.getString("sNombre"));
            ((Cliente)usuario).setApellido(rsPA.getString("apellido"));
            ((Cliente)usuario).setSegundoApellido(rsPA.getString("sApellido"));
            ((Cliente)usuario).setCorreo(rsPA.getString("correo"));
            ((Cliente)usuario).setTelefono(rsPA.getInt("telefono"));
            }
            else{
                usuario=new Funcionario();
            ((Funcionario)usuario).setNombre(rsPA.getString("nombre"));
            ((Funcionario)usuario).setsNombre(rsPA.getString("sNombre"));
            ((Funcionario)usuario).setApellido(rsPA.getString("apellido"));
            ((Funcionario)usuario).setsApellido(rsPA.getString("sApellido"));
            ((Funcionario)usuario).setCorreo(rsPA.getString("correo"));
            ((Funcionario)usuario).setTelefono(rsPA.getInt("telefono"));
            
            }
             usuario.setId(rsPA.getLong("id"));
            usuario.setContrasenna(rsPA.getString("contrasenna"));
            usuario.setTipo(TraerTipoUsuarioPorId(rsPA.getInt("tipoUsuario")));
            usuario.setEstado((rsPA.getInt("estado")==1));
                 
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
        return usuario;
    } 
       public static String NombreUsuarioXidentificacion(long id) throws SNMPExceptions{
    String select = "exec SeleccionaUsuarioXIdentificacion "+id;
        String nombre="";

        try {
        
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
         
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos 
             while (rsPA.next()) {
            nombre+=rsPA.getString("nombre")+" "+rsPA.getString("apellido");
                 
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
        return nombre;
    } 
     
     
     
 
       
       
       
       public static TipoUsuario TraerTipoUsuarioPorId(int id) throws SNMPExceptions{
       
       String select = "exec TraerTipoUsuarioPorId "+id;
       TipoUsuario tipo=null;

        try {
        
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

          
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            
             while (rsPA.next()) {
           tipo=new TipoUsuario();
           tipo.setId(rsPA.getInt("id"));
           tipo.setDescripcion(rsPA.getString("descripcion"));
                 
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
        return tipo;
    } 

   
       public static LinkedList<Cliente> peticionesClientes() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
           LinkedList<Cliente> clientes=new LinkedList<>();
           String select="select * from usuario where tipoUsuario=2 and estado=0";
           ResultSet rs;
           Cliente cliente;
           
           AccesoDatos datos=new AccesoDatos();
          rs= datos.ejecutaSQLRetornaRS(select);
           while(rs.next()){
               cliente=(Cliente) UsuarioXidentificacion(rs.getLong("id"));
               clientes.add(cliente);
           }
       return clientes;
       }
       
       public static void AceptarCliente(long id) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
           String update="update usuario set estado=1 where id="+id;
           AccesoDatos datos=new AccesoDatos();
           datos.ejecutaSQL(update);
       }
        public static void RechazarCliente(long id) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
           String delete="delete cliente where id="+id+"  delete usuario where id="+id;
           AccesoDatos datos=new AccesoDatos();
           datos.ejecutaSQL(delete);
       }
       
       }
       
       
       
       
       
       
 
  
 
 
    
    
