package com.gdu.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import com.gdu.entity.Student;
import com.gdu.model.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController implements Initializable{

    public List<Student> listStudent = Model.studentList;
    public Model model = new Model();
	
	
	@FXML
    private TableView<Student> tbData;
    @FXML
    public TableColumn<Student, String> full_name;
    @FXML
    public TableColumn<Student, String> student_code;
    @FXML
    public TableColumn<Student, String> identity_card_number;
    @FXML
    public TableColumn<Student, String> date_of_birth;
    @FXML
    public TableColumn<Student, String> sex;
    
    @FXML
    public TextField txtMSSV;
    
    @FXML
    public TextField txtHoTen;
    
    @FXML
    public RadioButton radNam;
    
    @FXML
    public RadioButton radNu;
    
    @FXML
    public TextField txtCMND;
    
    @FXML
    public DatePicker datePickerDateOfBirth;
    
    @FXML
    public ToggleGroup grGioiTinh;
    

    
    @FXML
    private void btnThem()
    {
    	int flag = 0;
    	for(Student sv: listStudent)
    	{
    		if(sv.getStudent_code().equals(txtMSSV.getText()))
    		{
    			JOptionPane.showMessageDialog(null, "Mã sinh viên đã tồn tại");
    			flag++;
    		}
    	}
    	if(flag==0)
    	{
        	String gioiTinh ="";
        	if(radNam.isSelected())
        	{
        		gioiTinh = "Nam";
        	}
        	else
        	{
        		gioiTinh = "Nữ";
        	}
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	LocalDate dateOfBirth = LocalDate.parse(datePickerDateOfBirth.getValue().toString());
        	Student sv = new Student(txtHoTen.getText(), txtMSSV.getText(),gioiTinh,txtCMND.getText(),formatter.format(dateOfBirth).toString(), BCrypt.hashpw(txtMSSV.getText(), BCrypt.gensalt()));
        	listStudent.add(sv);
        	model.insertStudents(sv);
        	loadData();
        	JOptionPane.showMessageDialog(null, "Thêm thành công");
    	}
    	flag = 0;
    	
    	
    }
    
    @FXML
    private void btnXoa()
    {
    	int index = -1;
    	for(Student student : listStudent)
    	{
    		if(student.getStudent_code().equals(txtMSSV.getText()))
    		{
    			index = listStudent.indexOf(student);
    			
    		}
    	}
    	if(index != -1)
    	{
    		listStudent.remove(index);
    	}
    	loadData();
    	model.deleteStudents(txtMSSV.getText());
    	btnTaoMoi();
    	JOptionPane.showMessageDialog(null, "Xóa thành công");
    }
    
    @FXML
    private void btnSua()
    {
        	String gioiTinh ="";
        	if(radNam.isSelected())
        	{
        		gioiTinh = "Nam";
        	}
        	else
        	{
        		gioiTinh = "Nữ";
        	}
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	LocalDate dateOfBirth = LocalDate.parse(datePickerDateOfBirth.getValue().toString());
        	Student sv = new Student(txtHoTen.getText(), txtMSSV.getText(),gioiTinh,txtCMND.getText(),formatter.format(dateOfBirth).toString(), BCrypt.hashpw(txtMSSV.getText(), BCrypt.gensalt()));
        	int index = -1;
        	for(Student student : listStudent)
        	{
        		if(student.getStudent_code().equals(txtMSSV.getText()))
        		{
        			index = listStudent.indexOf(student);
        			
        		}
        	}
        	if(index != -1)
        	{
        		listStudent.remove(index);
            	listStudent.add(sv);
        	}
        	loadData();
        	model.updateStudents(sv);;
        	JOptionPane.showMessageDialog(null, "Sửa thành công");
    	
    }
    
    @FXML
    private void btnSelectedRow()
    {
    	txtMSSV.setDisable(true);
    	Student sv = tbData.getSelectionModel().getSelectedItem();
    	txtMSSV.setText(sv.getStudent_code());
    	txtHoTen.setText(sv.getFull_name());
    	txtCMND.setText(sv.getIdentity_card_number());
    	if(sv.getSex().equals("Nam"))
    	{
    		radNam.setSelected(true);
    	}
    	else
    	{
    		radNu.setSelected(true);
    	}
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateOfBirth = LocalDate.parse(sv.getDate_of_birth(),formatter);
    	datePickerDateOfBirth.setValue(dateOfBirth);
    }
    
    @FXML
    private void btnTaoMoi()
    {
    	txtMSSV.setDisable(false);
    	txtCMND.setText("");
    	txtHoTen.setText("");
    	txtMSSV.setText("");
    	LocalDate now = LocalDate.now();
    	datePickerDateOfBirth.setValue(now);
    	radNam.setSelected(true);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}
	
	public void loadData()
	{
		full_name.setCellValueFactory(new PropertyValueFactory<Student,String>("full_name"));
		student_code.setCellValueFactory(new PropertyValueFactory<Student,String>("student_code"));
		sex.setCellValueFactory(new PropertyValueFactory<Student,String>("sex"));
		identity_card_number.setCellValueFactory(new PropertyValueFactory<Student,String>("identity_card_number"));
		date_of_birth.setCellValueFactory(new PropertyValueFactory<Student,String>("date_of_birth"));
		
		ObservableList<Student> student = FXCollections.observableArrayList(listStudent);
		tbData.setItems(student);
	}
}
