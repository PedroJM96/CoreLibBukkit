package com.pedrojm96.core.data;

import java.util.HashMap;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;


public class CoreMongoDB {
	private CoreMongoDBConnection connection;
	private String colletion;

	public CoreMongoDB(CoreMongoDBConnection connection,String colletion) {

		this.connection = connection;
		this.colletion = colletion;
	}
	
	public String get(CoreWHERE where, String data) {
		MongoCollection<Document> collection = this.connection.getDataStore().getCollection(colletion);
		Document searchQuery = new Document();
		searchQuery.put(where.get(0)[0], where.get(0)[1]);
		FindIterable<Document> cursor = collection.find(searchQuery);
		return (String) cursor.first().get(data);
	}
	
	public HashMap<String, String> get(CoreWHERE where, String... datas) {
		MongoCollection<Document> collection = this.connection.getDataStore().getCollection(colletion);
		Document searchQuery = new Document();
		searchQuery.put(where.get(0)[0], where.get(0)[1]);
		FindIterable<Document> cursor = collection.find(searchQuery);
		HashMap<String, String> value = new HashMap<String, String>();
		for(int i =0 ; i<datas.length ; i++) {
			value.put(datas[i], (String) cursor.first().get(datas[i]));
		}
		return value;
	}
	
	public void insert(Document data) {
		MongoCollection<Document> collection = this.connection.getDataStore().getCollection(colletion);
		collection.insertOne(data);
	}
	
	public void update(CoreWHERE where, Document data) {
		MongoCollection<Document> collection = this.connection.getDataStore().getCollection(colletion);
		Document query = new Document();
		query.put(where.get(0)[0], where.get(0)[1]);
		Document updateObject = new Document();
		updateObject.put("$set", data);
		collection.updateOne(query, updateObject);
	}
	

	public boolean checkData(CoreWHERE where) {
		MongoCollection<Document> collection = this.connection.getDataStore().getCollection(colletion);
		Document searchQuery = new Document();
		searchQuery.put(where.get(0)[0], where.get(0)[1]);
		FindIterable<Document> iterable = collection.find(searchQuery);
		return iterable.first() != null;
	}
	
	
}
