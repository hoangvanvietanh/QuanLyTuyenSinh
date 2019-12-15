package com.gdu.reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
 
import javax.swing.JFrame;

import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
 
public class PrintReport extends JFrame {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
 
    public static void main(String[] args) throws ClassNotFoundException, JRException, SQLException {
    	PrintReport r = new PrintReport();
    	r.showReport("Hoàng Văn Việt Anh","10/7/1998","Hải Dương","025899331");
	}
    
    public void showReport(String name, String dateOfBirth, String placeOfBirth, String cmnd) throws JRException, ClassNotFoundException, SQLException {
 
        String reportSrcFile = "/home/hoangvanvietanh/Documents/QuanLyTuyenSinh/src/com/gdu/reports/formTrungTuyen.jrxml";
 
        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for report
        HashMap<String, Object> parameters = new HashMap<String, Object>();
 
        parameters.put("name", name);
        parameters.put("dateOfBirth", dateOfBirth);
        parameters.put("placeOfBirth", placeOfBirth);
        parameters.put("cmnd", cmnd);
 
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
 
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null, beanColDataSource);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        System.out.print("Done!");
 
    }
 
}
