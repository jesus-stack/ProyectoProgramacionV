/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.Conexion.SNMPExceptions;
import DAO.UsuarioDLL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class Logica {
    public static void aceptar(Cliente c) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        UsuarioDLL.AceptarCliente(c.getId());
               Properties propiedad=new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
         propiedad.setProperty("mail.smtp.starttls.enable", "true");
          propiedad.setProperty("mail.smtp.port", "587");
           propiedad.setProperty("mail.smtp.auth", "true");
           Session session=Session.getDefaultInstance(propiedad);
           String correoe="jisusquiros2001@gmail.com";
           String contrasenna="jesus123587";
           String destinatrario= c.getCorreo();
           String Asunto="Aceptamos su peticion";
           String correo="Usted ha sido aprobado para comparar en linea en el Sitema Digital De Ventas"
                   + "\n esperamos verlo pronto";
           
           MimeMessage mail=new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress(correoe));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatrario));
            mail.setSubject(Asunto);
            mail.setText(correo);
            
            Transport transport=session.getTransport("smtp");
            transport.connect(correoe,contrasenna);
            transport.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transport.close();
        }catch(Exception e){
            
        }
        
           
        
    }
    
    public static void rechazar(String mensaje,Cliente c) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        UsuarioDLL.RechazarCliente(c.getId());
               Properties propiedad=new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
         propiedad.setProperty("mail.smtp.starttls.enable", "true");
          propiedad.setProperty("mail.smtp.port", "587");
           propiedad.setProperty("mail.smtp.auth", "true");
           Session session=Session.getDefaultInstance(propiedad);
           String correoe="jisusquiros2001@gmail.com";
           String contrasenna="jesus123587";
           String destinatrario= c.getCorreo();
           String Asunto="Rechazamos su peticion";
           String correo=mensaje;
           
           MimeMessage mail=new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress(correoe));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatrario));
            mail.setSubject(Asunto);
            mail.setText(correo);
            
            Transport transport=session.getTransport("smtp");
            transport.connect(correoe,contrasenna);
            transport.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transport.close();
        }catch(Exception e){
            
        }
        
           
        
    }
}
