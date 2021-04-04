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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Jesus
 */
public class TransaccionDB {
//    public static Pedido SelecionarProductos(long id) throws SNMPExceptions{
//         String select = "exec seleccionarProductosCarrito "+id;
//        Pedido Transaccion=new Pedido();
//        
//       
//
//        try {
//        
//            //Se instancia la clase de acceso a datos
//            AccesoDatos accesoDatos = new AccesoDatos();
//
//            //Se crea la sentencia de búsqueda
//         
//            //Se ejecuta la sentencia SQL
//            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
//            //Se llena el arryaList con los proyectos 
//            Producto pro;
//             while (rsPA.next()) {
//            pro=new Producto();
//              pro.setEstado(rsPA.getInt("estado")==1);
// 
//                pro.setId(rsPA.getInt("id"));
//                pro.setNombre(rsPA.getString("nombre"));
//                pro.setDescripcion(rsPA.getString("descripcion"));
//               pro.setFoto(rsPA.getString("foto"));
//                pro.setPrecio(rsPA.getFloat("precio"));
//                pro.setCantidadMinimaVenta(rsPA.getInt("cantidadMinimaVenta")); 
//                //productos.add(pro);
//                 
//             }
//            rsPA.close();
//
//        } catch (SQLException e) {
//            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
//                    e.getMessage(), e.getErrorCode());
//        } catch (Exception e) {
//            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
//                    e.getMessage());
//        } finally {
//
//        }
//      //  return pro;
//    }
// 
  
}
