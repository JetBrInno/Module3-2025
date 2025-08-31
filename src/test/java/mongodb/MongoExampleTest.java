package mongodb;

import com.mongodb.client.*;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MongoExampleTest {
    private static final String MONGO_URL = "mongodb://localhost:27017/";

    private static MongoClient client;

    @BeforeAll
    public static void setUp(){
        client = MongoClients.create(MONGO_URL);
    }

    @Test
    public void connectTest() {
        MongoCursor<String> iterator = client.listDatabaseNames().iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

    @Test
    public void getCollectionTest() {
        MongoCollection<Document> cats = client.getDatabase("cats").getCollection("cats-info");
        System.out.println(cats);

        FindIterable<Document> documents = cats.find();
        Document cat = documents.iterator().next();
        System.out.println(cat.get("_id"));
    }

    @Test
    public void getCollectionsTest() {
        MongoCollection<Document> cats = client.getDatabase("cats").getCollection("cats-info");

        FindIterable<Document> documents = cats.find();
        documents.forEach(document -> {
            System.out.println(document);
            Integer age = null;
            age = document.getInteger("age");

            System.out.println(document.get("name"));
            System.out.println(age);
        });
    }


    @AfterAll
    public static void tearDown() {
        if (client != null) {
            client.close();
        }
    }
}
