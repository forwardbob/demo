package com.example.demo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ServerAddress serverAddress=new ServerAddress("47.92.36.72",27017);
        List<ServerAddress> addrs=new ArrayList<>();
        addrs.add(serverAddress);
        MongoCredential credential= MongoCredential.createMongoCRCredential("kitchen","kitchen","kitchen_1302_90911".toCharArray());
        ArrayList<MongoCredential> mongoCredentials = new ArrayList<>();
        mongoCredentials.add(credential);
        //通过连接认证获取数据库
        MongoClient mongoClient = new MongoClient(addrs, mongoCredentials);
        //连接到数据库
        MongoDatabase database = mongoClient.getDatabase("kitchen");
        System.out.println("连接成功");

        Test test = new Test();
        test.update(database);
    }


    public void createCollection(MongoDatabase database){
        database.createCollection("test_db");
    }
    public void getCollection(MongoDatabase database){
        MongoCollection<Document> db = database.getCollection("test_db");
        String fullName = db.getNamespace().getFullName();
        System.out.println("获取库" +fullName+ "成功");
    }

    public void insertCollection(MongoDatabase database){
        MongoCollection<Document> db = database.getCollection("test_db");
        String fullName = db.getNamespace().getFullName();
        System.out.println("获取库" +fullName+ "成功");
        Document document=new Document("title","MongoDB");
       // document.append("description","datebase");
        document.append("content","123123");
        List<Document> documents=new ArrayList<>();
        documents.add(document);
        db.insertMany(documents);
        System.out.println("插入成功");
    }
    public void find(MongoDatabase database){
        //检索所有文档
        /**
         * 1. 获取迭代器FindIterable<Document>
         * 2. 获取游标MongoCursor<Document>
         * 3. 通过游标遍历检索出的文档集合
         * */
        MongoCollection<Document> db = database.getCollection("test_db");
        Bson bson = Filters.eq("content", "1231234");
        System.out.println("查询条件 content="+"1231234");
        FindIterable<Document> documents = db.find(bson);
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    public void update(MongoDatabase database){
        MongoCollection<Document> db = database.getCollection("test_db");
        Bson bson = Filters.eq("content", "1231234");
        db.updateMany(Filters.eq("content","123123"),new Document("$set",new Document("content","1234")));


    }
}



//  try {
//          //连接到mongodb服务
//          MongoClient client = new MongoClient("47.92.36.72", 27017);
//          //连接到对应数据库
//          MongoDatabase kitchen = client.getDatabase("kitchen");
//          System.out.println("连接成功");
//          } catch (Exception e) {
//          e.printStackTrace();
//          } finally {
//          }