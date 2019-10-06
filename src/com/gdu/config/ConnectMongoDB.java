package com.gdu.config;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.gdu.entity.Student;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectMongoDB {

	String database_name = "gia_dinh_university";
	String collection_name = "test";
	
	MongoClientURI uri = new MongoClientURI(
			"mongodb+srv://vietanh:113114115@cluster0-jfbgb.mongodb.net/test?retryWrites=true&w=majority");
	MongoClient mongoClient = new MongoClient(uri);
	MongoDatabase database = mongoClient.getDatabase(database_name);
	
	//FindIterable<Document> findIterable = collectionStudent().find(new Document());
	
	public MongoCollection<Document> collectionStudent ()
	{
		MongoCollection<Document> collection = database.getCollection(collection_name);
		return collection;
	}
//	public MongoCollection<Document> collection (String colectionName)
//	{
//		MongoCollection<Document> collection = database.getCollection(colectionName);
//		return collection;
//	}
	
	
	
//	public void test()
//	{
//		List<Student> studentList = new ArrayList<Student>();
//		Gson g = new Gson();
//		for(Document doc: findIterable)
//		{
//			Student s = g.fromJson(doc.toJson().toString(), Student.class);
//			studentList.add(s);
//		}
//		for(Student s: studentList)
//		{
//			System.out.println("Họ tên là: "+ s.getHoTen());
//		}		
//	}
}
