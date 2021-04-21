/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Conexion.AccesoDatos;
import DAO.Conexion.SNMPExceptions;
import Model.Reporte;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Jesus
 */
public class ReportesBD {
    
    
    public static Reporte reportes() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        Reporte reporte=new Reporte();
        String select="exec reportes";
        ResultSet rs=null;
        AccesoDatos datos=new AccesoDatos();
        rs=datos.ejecutaSQLRetornaRS(select);
        while(rs.next()){
            reporte.setPedidoPendiente(rs.getInt("pendiente"));
             reporte.setPedidoFacturado(rs.getInt("facturado"));
              reporte.setPedidoDespachado(rs.getInt("despachado"));
              reporte.setTotal(rs.getInt("total"));
              reporte.setVentaEfectivo(rs.getFloat("contado"));
                    reporte.setVentaCredito(rs.getFloat("credito"));
        }
        return reporte;
    }
}
