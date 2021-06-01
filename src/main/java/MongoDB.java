import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import java.util.Scanner;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {


    public static void save() {

        Connection con = new Connection();
        MongoDatabase db = con.db;

        MongoCollection<Document> collectionAddClient = db.getCollection("Client");

        Document siczek = new Document("_id", 1)
                .append("firstName", "Krzysztof")
                .append("lastName", "Siczek")
                .append("age", 24);
        collectionAddClient.insertOne(siczek);

        Document kowalski = new Document("_id", 2)
                .append("firstName", "Jan")
                .append("lastName", "Kowalski")
                .append("age", 18);
        collectionAddClient.insertOne(kowalski);

        Document baczynski = new Document("_id", 3)
                .append("firstName", "Kasia")
                .append("lastName", "Bezmeza")
                .append("age", 60);
        collectionAddClient.insertOne(baczynski);

        MongoCollection<Document> collectionAddBook = db.getCollection("Book");

        Document gameOfThrones = new Document("_id", 1)
                .append("title", "Game of Thrones")
                .append("genre", "fantasy")
                .append("rating", 9);
        collectionAddBook.insertOne(gameOfThrones);

        Document houseOfCards = new Document("_id", 2)
                .append("title", "House of cards")
                .append("genre", "drama")
                .append("rating", 10);
        collectionAddBook.insertOne(houseOfCards);

        Document witcher1 = new Document("_id", 3)
                .append("title", "The Witcher: Season of Storms")
                .append("genre", "fantasy")
                .append("rating", 8);
        collectionAddBook.insertOne(witcher1);

        Document witcher2 = new Document("_id", 4)
                .append("title", "The Witcher: Baptism of Fire")
                .append("genre", "fantasy")
                .append("rating", 5);
        collectionAddBook.insertOne(witcher2);

        Document witcher3 = new Document("_id", 5)
                .append("title", "The Witcher: Blood of Elves")
                .append("genre", "fantasy")
                .append("rating", 8);
        collectionAddBook.insertOne(witcher3);

        MongoCollection<Document> collectionAddLibrary = db.getCollection("Library");

        Document library1 = new Document("_id", 1)
                .append("uniqueID", "PskMainLibrary")
                .append("address", "Kielcecka 23")
                .append("size", 1000);
        collectionAddLibrary.insertOne(library1);

        Document library2 = new Document("_id", 2)
                .append("uniqueID", "villageLibrary")
                .append("address", "Radomska 123")
                .append("size", 150);
        collectionAddLibrary.insertOne(library2);

    }

    public static void update() {

        Connection con = new Connection();
        MongoDatabase db = con.db;

        MongoCollection<Document> collectionUpdate = db.getCollection("Client");


        collectionUpdate.updateOne(eq("_id", 3), new Document("$set", new Document("Achievement", "Membership since 2008")));
        System.out.println("New Client List: \n");
        for (Document doc : collectionUpdate.find())
            System.out.println(doc.toJson());

    }

    public static void deleteOneBook() {

        Connection con = new Connection();
        MongoDatabase db = con.db;

        MongoCollection<Document> collectionDeleteBooks = db.getCollection("Book");

        collectionDeleteBooks.deleteOne(eq("_id", 5));
        for (Document doc : collectionDeleteBooks.find())
            System.out.println("Remaining books: " + doc.toJson());

    }

    public static void deleteAll() {

        Connection con = new Connection();
        MongoDatabase db = con.db;

        MongoCollection<Document> collectionDeleteBooks = db.getCollection("Book");
        MongoCollection<Document> collectionDeleteClients = db.getCollection("Client");
        MongoCollection<Document> collectionDeleteLibrary = db.getCollection("Library");


        for (Document doc : collectionDeleteBooks.find())
            collectionDeleteBooks.deleteOne(doc);

        for (Document doc : collectionDeleteClients.find())
            collectionDeleteClients.deleteOne(doc);

        for (Document doc : collectionDeleteLibrary.find())
            collectionDeleteLibrary.deleteOne(doc);

    }

    public static void download() {


        Connection con = new Connection();
        MongoDatabase db = con.db;

        MongoCollection<Document> collectionDownloadBooks = db.getCollection("Book");
        MongoCollection<Document> collectionDownloadClients = db.getCollection("Client");
        MongoCollection<Document> collectionDownloadLibraries = db.getCollection("Library");

        System.out.println("All Clients, Books, Libraries: \n");

        for (Document doc : collectionDownloadBooks.find())
            System.out.println("Books: " + doc.toJson());

        for (Document doc : collectionDownloadClients.find())
            System.out.println("Clients: " + doc.toJson());

        for (Document doc : collectionDownloadLibraries.find())
            System.out.println("Libraries: " + doc.toJson());

    }

    public static void downloadByRating() {

        Connection con = new Connection();
        MongoDatabase db = con.db;

        MongoCollection<Document> collectionDownloadBooks = db.getCollection("Book");

        int rating1;
        int rating2;

        System.out.println("Pick the book rating 0-10:");
        Scanner scan = new Scanner(System.in);
        System.out.println("Rating nr 1:");
        rating1 = scan.nextInt();
        System.out.println("Rating nr 2:");
        rating2 = scan.nextInt();

        for (Document d : collectionDownloadBooks.find(or(
                eq("rating", rating1),
                eq("rating", rating2))))
            System.out.println("Books with picked rating:) " + d.toJson());
    }


    public static void main(String[] args) {

        Connection con = new Connection();
        MongoClient mongoClient = con.mongoClient;

        Scanner scan = new Scanner(System.in);
        String option = "";
        while (!option.equals("0")) {
            System.out.println("Pick the option: ");
            System.out.println("1.Save \n2.Update \n3.Delete all \n4.Delete only one book \n5.Download \n6.Download books by rating \n0.Exit");
            option = scan.nextLine();
            switch (option) {
                case "0":
                    System.out.println("Exit");
                    break;
                case "1":
                    System.out.println("Save");
                    save();
                    break;
                case "2":
                    System.out.println("Update");
                    update();
                    break;
                case "3":
                    System.out.println("Delete all");
                    deleteAll();
                    break;
                case "4":
                    System.out.println("Delete only one book");
                    deleteOneBook();
                    break;
                case "5":
                    System.out.println("Download");
                    download();
                    break;
                case "6":
                    System.out.println("Download books by rating");
                    downloadByRating();
                    break;
            }
        }


        mongoClient.close();


    }
}
