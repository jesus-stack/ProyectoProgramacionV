/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Conexion.AccesoDatos;
import DAO.Conexion.SNMPExceptions;
import Model.Pedido;
import Model.Producto;
import Model.TransaccionProducto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author Jesus
 */
public class TransaccionDB {

    public static void crearTransaccion(long id) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String insert = "exec CrearTransaccion " + id;

        AccesoDatos datos = new AccesoDatos();
        datos.ejecutaSQL(insert);
    }

    public static Pedido seleccionarTransaccion(long id, int estado) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "exec seleccionarTransaccion " + id + "," + estado;
        ResultSet rs = null;
        Pedido pedido = new Pedido();
       

        AccesoDatos datos = new AccesoDatos();

        rs = datos.ejecutaSQLRetornaRS(select);
      while(rs.next()){
          pedido.setId(rs.getInt("id"));
      
       
        
         pedido.setProductos(transaccionP(rs.getInt("id")));
      }
     
      
        
        
    
return pedido;
    }
    
    public static LinkedList<TransaccionProducto> transaccionP(int id) throws SNMPExceptions, SQLException, ClassNotFoundException, NamingException{
      ResultSet rs = null;
      AccesoDatos datos = new AccesoDatos();
      LinkedList <TransaccionProducto> tp=new LinkedList<>();  
      rs=datos.ejecutaSQLRetornaRS("exec seleccionarProductosCarrito "+id);
        
         while (rs.next()) {
            Producto pro = ProductoDB.traerProductoPorId(rs.getInt("id"));
           TransaccionProducto transaccion=new TransaccionProducto();
           transaccion.setProducto(pro);
           transaccion.setCantidad(rs.getInt("cantidad"));
           tp.add(transaccion);
        }
    return tp;
    } 

    public static void AgregarProducto(long idcliente,int productoid,int cantidad) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
       
        
        Pedido pedido=seleccionarTransaccion(idcliente, 1);
        if(pedido.getProductos()==null){
            crearTransaccion(idcliente);
            pedido=seleccionarTransaccion(idcliente, 1);
        }
        String store="AgregarProductoaCarrito";
        
        AccesoDatos datos=new AccesoDatos();
        if(existeProductoTransaccion(pedido.getId(), productoid)){
            store="ActualizarProductoCarrito";
            cantidad=1;
        }
       
         String insert="exec "+store+" "+pedido.getId()+","+productoid+","+cantidad;
        datos.ejecutaSQL(insert);
    }
    private static boolean existeProductoTransaccion(int trans,int pro) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        String select="exec SeleccionarProductoCarritoXid "+trans+","+pro;
        ResultSet rs=null;
        AccesoDatos datos=new AccesoDatos();
        rs=datos.ejecutaSQLRetornaRS(select);
        return rs.next();
        
    }
  
     public static void EliminarProducto(int trans,long pro) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        String ejecucion="delete from transaccionProducto where idTransaccion="+trans+" and idProducto="+pro;
       
        AccesoDatos datos=new AccesoDatos();
        datos.ejecutaSQL(ejecucion);
     
        
    }
    
    
}
