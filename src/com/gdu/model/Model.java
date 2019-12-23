package com.gdu.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.gdu.config.ConnectMongoDB;
import com.gdu.entity.Admin;
import com.gdu.entity.History;
import com.gdu.entity.Scores;
import com.gdu.entity.Student;
import com.gdu.entity.StudentRegistration;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Model {

	ConnectMongoDB mongoDB = new ConnectMongoDB();	
	Gson g = new Gson();
	FindIterable<Document> findIterable;
	public static List<Student> studentList = new ArrayList<Student>();
	public static List<StudentRegistration> studentsRestrationList = new ArrayList<StudentRegistration>();
	public static List<History> historyList = new ArrayList<History>();
	public static List<Admin> adminList = new ArrayList<Admin>();
	
	public List<Student> getAllStudent()
	{
		findIterable = mongoDB.collectionStudent().find(new Document());
		
		for(Document doc: findIterable)
		{
			Student s = g.fromJson(doc.toJson().toString(), Student.class); 
			//Scores sc =  s.getScore().get(0);
			//System.out.println(sc.getLy_A2());
			studentList.add(s);
		}		
		return studentList;
	}
	
	public List<Admin> getAllAdmin()
	{
		findIterable = mongoDB.collectionAdmin().find(new Document());
		
		for(Document doc: findIterable)
		{
			Admin s = g.fromJson(doc.toJson().toString(), Admin.class); 
			//Scores sc =  s.getScore().get(0);
			//System.out.println(sc.getLy_A2());
			adminList.add(s);
		}		
		return adminList;
	}
	
	public List<History> getAllHistory()
	{
		findIterable = mongoDB.collectionHistory().find(new Document());
		
		for(Document doc: findIterable)
		{
			History s = g.fromJson(doc.toJson().toString(), History.class); 
			//Scores sc =  s.getScore().get(0);
			//System.out.println(sc.getLy_A2());
			//System.out.println(s.getContent());
			historyList.add(s);
		}		
		return historyList;
	}
	
	public List<StudentRegistration> getAllStudentRegistration()
	{
		findIterable = mongoDB.collectionStudentRegistration().find(new Document());
		
		for(Document doc: findIterable)
		{
			StudentRegistration s = g.fromJson(doc.toJson().toString(), StudentRegistration.class); 
			//Scores sc =  s.getScore().get(0);
			//System.out.println(sc.getLy_A2());
			studentsRestrationList.add(s);
		}		
		return studentsRestrationList;
	}
	
	public void insertStudents(Student student)
	{
		Document doc = new Document();
		doc =  Document.parse(g.toJson(student));
		mongoDB.collectionStudent().insertOne(doc);
	}
	
	public void insertHistory(History history)
	{
		Document doc = new Document();
		doc =  Document.parse(g.toJson(history));
		mongoDB.collectionHistory().insertOne(doc);
	}
	
	public void insertStudents(StudentRegistration student)
	{
		Document doc = new Document();
		doc =  Document.parse(g.toJson(student));
		mongoDB.collectionStudentRegistration().insertOne(doc);
	}
	
	public void updateStudents(StudentRegistration student)
	{
		Document doc = new Document();
		doc =  Document.parse(g.toJson(student));
		Bson filter = Filters.eq("studentCode", student.getStudentCode());
		mongoDB.collectionStudentRegistration().replaceOne(filter, doc);
	}
	
	public void updateStudents(Student student)
	{
		Document doc = new Document();
		doc =  Document.parse(g.toJson(student));
		Bson filter = Filters.eq("student_code", student.getStudent_code());
		mongoDB.collectionStudent().replaceOne(filter, doc);
	}
	
	public void deleteStudents(String studentCode)
	{
		Bson filter = Filters.eq("student_code", studentCode);
		mongoDB.collectionStudent().deleteOne(filter);
	}
}
