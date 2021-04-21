/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ReportesBD;
import Model.Reporte;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Jesus
 */
@Named(value = "beanReportes")
@SessionScoped
public class beanReportes implements Serializable {

  
    private PieChartModel model,model1;
    private Reporte reporte;
 

 
    @PostConstruct 
        void init(){
            try{
  reporte=ReportesBD.reportes();
            }catch(Exception e){
                
            }
      model = new PieChartModel();
      model.set("Pedidos Pendientes", reporte.getPedidoPendiente());//jobs in thousands
      model.set("Pedidos en proceso", reporte.getPedidoFacturado());
      model.set("Pedidos Finalizados", reporte.getPedidoDespachado());
    

      //followings are some optional customizations:
      //set title
     
      //set legend position to 'e' (east), other values are 'w', 's' and 'n'
      model.setLegendPosition("e");
      //enable tooltips
      model.setShowDatatip(true);
      //show labels inside pie chart
      model.setShowDataLabels(true);
      //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
      model.setDataFormat("value");
      //format: %d for 'value', %s for 'label', %d%% for 'percent'
      model.setDataLabelFormatString("%d");
      //pie sector colors
      model.setSeriesColors("aaf,afa,faa,ffa,aff,faf,ddd");
      
      
       model1 = new PieChartModel();
      model1.set(" Ventas al Contado", reporte.getVentaEfectivo());//jobs in thousands
      model1.set("Ventas a Credito", reporte.getVentaCredito());
      
    

      //followings are some optional customizations:
      //set title
     
      //set legend position to 'e' (east), other values are 'w', 's' and 'n'
      model1.setLegendPosition("e");
      //enable tooltips
      model1.setShowDatatip(true);
      //show labels inside pie chart
      model1.setShowDataLabels(true);
      //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
      model1.setDataFormat("value");
      //format: %d for 'value', %s for 'label', %d%% for 'percent'
      model1.setDataLabelFormatString("%d");
      //pie sector colors
      model1.setSeriesColors("aaf,afa,faa,ffa,aff,faf,ddd");
  }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

  public PieChartModel getModel() {
      return model;
  }
   public PieChartModel getModel1() {
      return model1;
  } /**
     * Creates a new instance of beanReportes
     */
   
    
}
