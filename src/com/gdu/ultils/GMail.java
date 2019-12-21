package com.gdu.ultils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.gdu.entity.StudentRegistration;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GMail {
	
	public static void sendMail(StudentRegistration student) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "hoangvanvietanhh@gmail.com";
        //Your gmail password
        String password = "Vietanh123";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, student.getEmail(), student);

        //Send mail
        Transport.send(message);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thông báo");
		alert.setHeaderText("Thông báo trúng đã được gửi thành công");
		alert.setContentText("Thí sinh: " + student.getFullName() + " Mã số: "+student.getStudentCode());
		alert.showAndWait();
        System.out.println("Message sent successfully");
    }

	public static void sendMailFail(StudentRegistration student) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "hoangvanvietanhh@gmail.com";
        //Your gmail password
        String password = "Vietanh123";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessageFail(session, myAccountEmail, student.getEmail(), student);

        //Send mail
        Transport.send(message);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thông báo");
		alert.setHeaderText("Thông báo rớt đã được gửi thành công");
		alert.setContentText("Thí sinh: " + student.getFullName() + " Mã số: "+student.getStudentCode());
		alert.showAndWait();
        System.out.println("Message sent successfully");
    }
	
	private static Message prepareMessage(Session session, String myAccountEmail, String recepient, StudentRegistration student) {
        try {
        	MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First Email from Java App");
            String htmlCode = "<h3 style=\"text-align: center;\">Trường đại học Gia Định thông báo</h3>" + "<p>Thí sinh:"+student.getFullName()+"</p>"+"<p>Ngày sinh:"+student.getDateOfBirth()+"</p>"+"<p>Nơi sinh:"+student.getPlaceOfBirth()+"</p>"+"<p>Số CMND:"+student.getIdOfStudent()+"</p>" +"<p>Đã trúng tuyển vào trường đại học Gia Định với chuyên ngành</p>";
            //message.setContent(htmlCode, "text/html");
            
         // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setContent(htmlCode,"text/html; charset=utf-8");

	         // Create a multipar message
	         MimeMultipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = GetCurrentPath.path() + "src/com/gdu/documents/reportTrungTuyen.pdf";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart, "UTF-8");
            return message;
        } catch (Exception ex) {
            //Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
    
    private static Message prepareMessageFail(Session session, String myAccountEmail, String recepient, StudentRegistration student) {
        try {
        	MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First Email from Java App");
            String htmlCode = "<h3 style=\"text-align: center;\">Trường đại học Gia Định thông báo</h3>" + "<p>Thí sinh:"+student.getFullName()+"</p>"+"<p>Ngày sinh:"+student.getDateOfBirth()+"</p>"+"<p>Nơi sinh:"+student.getPlaceOfBirth()+"</p>"+"<p>Số CMND:"+student.getIdOfStudent()+"</p>" +"<p>Bạn đã không đủ điều kiện, cám ơn bạn đã lựa chọn Đại học Gia Định</p>";
            //message.setContent(htmlCode, "text/html");
            
         // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setContent(htmlCode,"text/html; charset=utf-8");

	         // Create a multipar message
	         MimeMultipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = GetCurrentPath.path() + "src/com/gdu/documents/reportTrungTuyen.pdf";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart, "UTF-8");
            return message;
        } catch (Exception ex) {
            //Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
