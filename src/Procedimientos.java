

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wishvalle
 */
public class Procedimientos {
    
    
    public static int id=6;
    private static MongoCollection<Cliente> collectionClient;
    static Conexion conn;
    public void getCollection() {
        conn = Conexion.getInstance();
        collectionClient = conn.database.getCollection("cliente", Cliente.class);
    }
    
    public static void AgregarCliente(Cliente document){
        
      if(conn != null){
          if (document != null) {
              document.setId(id);
              id++;
              collectionClient.insertOne(document);
              JOptionPane.showMessageDialog(null, "¡Cliendo agregado!" , "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
          }
      }
    
    }
    
    public static void ModificarCliente(Cliente document, int idx){
       if(conn != null){
          if (document != null) {
              collectionClient.replaceOne(eq("_id", idx), document);
              JOptionPane.showMessageDialog(null, "¡Cliendo modificado!" , "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
          }
      } //revisar
    
    }
    
    public static void EliminarCliente(int idx){
        if(conn != null){
          collectionClient.deleteOne(eq("_id", idx));
        }
    }
    
    public static Cliente BuscarCliente(int idx) {
        if(conn != null){
            return collectionClient.find(eq("_id", idx)).first();
        }
      return null;  
    }
    
    public static List<Cliente> ListarClientes() {
        FindIterable<Cliente> iterable = collectionClient.find();
        Iterator it = iterable.iterator();
        List<Cliente> allUsers = new ArrayList<>();
        while (it.hasNext()) {
            Cliente per = (Cliente) it.next();
            allUsers.add(per);
        }
        return allUsers;
    }
    
}
