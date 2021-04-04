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
        LinkedList<TransaccionProducto> tp=new LinkedList<>();

        AccesoDatos datos = new AccesoDatos();

        rs = datos.ejecutaSQLRetornaRS(select);
        LinkedList<TransaccionProducto> productos = new LinkedList<>();
        while (rs.next()) {
            Producto pro = new Producto();
            pro.setEstado(rs.getInt("estado") == 1);
            pro.setId(rs.getInt("id"));
            pro.setNombre(rs.getString("nombre"));
            pro.setDescripcion(rs.getString("descripcion"));
            pro.setFoto(rs.getString("foto"));
            pro.setPrecio(rs.getFloat("precio"));
            pro.setCantidadMinimaVenta(rs.getInt("cantidadMinimaVenta"));
           TransaccionProducto transaccion=new TransaccionProducto();
           transaccion.setProducto(pro);
           transaccion.setCantidad(rs.getInt("cantidad"));
           tp.add(transaccion);
           

        }
        if(rs.first()){
        rs.absolute(1);
        pedido.setProductos(tp);
        pedido.setId(rs.getInt("idTransaccion"));
    }
return pedido;
    }

    public static void AgregarProducto(long idcliente,int productoid,int idpedido,int cantidad) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        String insert="exec AgregarProductoaCarrito "+idpedido+","+productoid+","+cantidad;
        
        Pedido pedido=seleccionarTransaccion(idcliente, 1);
        if(pedido.getProductos()==null){
            crearTransaccion(idcliente);
        }
        AccesoDatos datos=new AccesoDatos();
        datos.ejecutaSQL(insert);
    }
}
