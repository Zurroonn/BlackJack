/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class UsuarioDAO {

    private final MongoCollection<Document> collection;

    public UsuarioDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("banco");
    }

    public boolean crearUsuario(Usuario usuario) {
        if (buscarUsuario(usuario.getNombre()) == null) { // Si el usuario no existe
            collection.insertOne(usuario.toDocument());  // Lo inserta en la base de datos
            System.out.println("Usuario creado exitosamente.");
            return true; // Indica que la creación fue exitosa
        } else {
            System.out.println("El usuario ya existe.");
            return false; // Indica que el usuario ya existía
        }
    }

    public Usuario buscarUsuario(String nombre) {
        Document doc = collection.find(Filters.eq("nombre", nombre)).first();
        return (doc != null) ? Usuario.fromDocument(doc) : null;
    }

    public Usuario iniciarSesion(String nombre, String contraseña) {
        Document doc = collection.find(Filters.and(
                Filters.eq("nombre", nombre),
                Filters.eq("contraseña", contraseña) // Verifica usuario y contraseña
        )).first();

        return (doc != null) ? Usuario.fromDocument(doc) : null;
    }

    public void actualizarDinero(String nombre, int nuevoDinero) {
        collection.updateOne(Filters.eq("nombre", nombre),
                new Document("$set", new Document("dinero", nuevoDinero)));
        System.out.println("Dinero actualizado.");
    }

    public void eliminarUsuario(String nombre) {
        collection.deleteOne(Filters.eq("nombre", nombre));
        System.out.println("Usuario eliminado.");
    }
}
