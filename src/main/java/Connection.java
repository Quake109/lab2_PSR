import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


public class Connection {

    String user = "student01";
    String password = "student01";
    String host = "localhost";
    int port = 27017;
    String database = "database01";

    String clientURI = "mongodb://" + user + ":" + password + "@" + host + ":" + port + "/" + database;
    MongoClientURI uri = new MongoClientURI(clientURI);
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase db = mongoClient.getDatabase(database);

}
