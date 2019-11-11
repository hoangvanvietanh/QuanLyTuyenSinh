package com.gdu.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.gdu.config.ConnectMongoDB;
import com.gdu.entity.Scores;
import com.gdu.entity.Student;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Model {

	ConnectMongoDB mongoDB = new ConnectMongoDB();	
	Gson g = new Gson();
	FindIterable<Document> findIterable;
	public static List<Student> studentList = new ArrayList<Student>();
	
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
	
	public void insertStudents(Student student)
	{
		Document doc = new Document();
		doc =  Document.parse(g.toJson(student));
		mongoDB.collectionStudent().insertOne(doc);
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
