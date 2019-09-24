package com.gdu.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
	// private String _id;
	private String full_name;
	private String student_code;
	private String sex;
	private String identity_card_number;
	private String date_of_birth;
	private String password;
	private String address;
	private StudentClass student_class;
	private ArrayList<Scores> score;
	private String place_of_birth;
	private String registration_date;
	private String status;
	
	

	
	
	public Student(String full_name, String student_code) {
		this.full_name = full_name;
		this.student_code = student_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public String getPlace_of_birth() {
		return place_of_birth;
	}

	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}

	public ArrayList<Scores> getScore() {
		return score;
	}

	public void setScore(ArrayList<Scores> score) {
		this.score = score;
	}

	public StudentClass getStudent_class() {
		return student_class;
	}

	public void setStudent_class(StudentClass student_class) {
		this.student_class = student_class;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getStudent_code() {
		return student_code;
	}

	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentity_card_number() {
		return identity_card_number;
	}

	public void setIdentity_card_number(String identity_card_number) {
		this.identity_card_number = identity_card_number;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
