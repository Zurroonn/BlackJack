/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import org.bson.Document;

public class Usuario {
    private String nombre;
    private String contraseña;
    private int dinero;

    public Usuario(String nombre, String contraseña, int dinero) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getDinero() {
        return dinero;
    }

    public Document toDocument() {
        return new Document("nombre", nombre)
                .append("contraseña", contraseña)
                .append("dinero", dinero);
    }

    public static Usuario fromDocument(Document doc) {
        return new Usuario(
                doc.getString("nombre"),
                doc.getString("contraseña"),
                doc.getInteger("dinero")
        );
    }
}
