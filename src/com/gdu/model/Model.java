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
//		student.setStudent_code("456");
		
		Document doc = new Document();
		doc =  Document.parse(g.toJson(student));
//		System.out.println(doc);
		mongoDB.collectionStudent().insertOne(doc);
//		String test = "{'identity_card_number':'025899331'}";
//		Document query = new Document();
//		query = Document.parse(test);
//		System.out.println(query);
		
		
	}
	
	public void updateStudents(Student student)
	{
		Document doc = new Document();
		doc =  Document.parse(g.toJson(student));
		Bson filter = Filters.eq("identity_card_number", "025899331");
		mongoDB.collectionStudent().replaceOne(filter, doc);
	}
}
