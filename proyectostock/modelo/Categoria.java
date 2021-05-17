/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectostock.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cecilia
 */
public class Categoria {

    public IntegerProperty idCategoria;
    private StringProperty nombre;

    public Categoria(int idCategoria, String nombre) {
        this.idCategoria = new SimpleIntegerProperty(idCategoria);
        this.nombre = new SimpleStringProperty(nombre);
    }

    public Categoria(StringProperty nombre) {
        this.nombre = nombre;
    }

    public Categoria() {

    }

    //Metodos atributo: idCategoria
    public int getIdCategoria() {
        return idCategoria.get();
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = new SimpleIntegerProperty(idCategoria);
    }

    public IntegerProperty IdCategoriaProperty() {
        return idCategoria;
    }
    //Metodos atributo: nombre

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public StringProperty NombreProperty() {
        return nombre;
    }

    @Override
    public String toString() {
        return   nombre.get();
    }

}
