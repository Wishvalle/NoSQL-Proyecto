/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.UnknownHostException;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author Wishvalle
 */
public class Conexion {
    
    public MongoClient mongo;
    private final String db;
    public MongoDatabase database;
    private static Conexion instance;

    private Conexion() {
        db = "restaurante";
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongo = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongo.getDatabase(db);

    }

    public static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
            return instance;
        } else {
            return instance;
        }
    }
    
}
