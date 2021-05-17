package proyectostock.modelo;

import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {

    private IntegerProperty idProducto;
    private StringProperty codigoProducto;
    private StringProperty nombre;
    private StringProperty descripcion;
    private StringProperty capacidad;
    private IntegerProperty stock;
    private IntegerProperty minimoStock;
    private FloatProperty precioCompra;
    private FloatProperty precioVenta;
    private FloatProperty precioMayorista;
    private boolean esMayor;
    private boolean estado; //ACTIVO O NO
    private StringProperty proveedor;
//    private IntegerProperty idCategoria;
    private Categoria categoria;
    private Date fechaIn;

    public Producto(int idProducto, String codigoProducto, String nombre,
            String descripcion, String capacidad, int stock,
            int minimoStock, float precioCompra, float precioVenta,
            float precioMayorista, boolean esMayor, boolean estado,
            String proveedor, Categoria categoria, Date fechaIn) {

        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.codigoProducto = new SimpleStringProperty(codigoProducto);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.capacidad = new SimpleStringProperty(capacidad);
        this.stock = new SimpleIntegerProperty(stock);
        this.minimoStock = new SimpleIntegerProperty(minimoStock);
        this.precioCompra = new SimpleFloatProperty(precioCompra);
        this.precioVenta = new SimpleFloatProperty(precioVenta);
        this.precioMayorista = new SimpleFloatProperty(precioMayorista);
        this.esMayor = esMayor;
        this.estado = estado;
        this.proveedor = new SimpleStringProperty(proveedor);
        this.categoria = categoria;
        this.fechaIn = fechaIn;
    }

    public Producto(String codigoProducto, String nombre,
            String descripcion, String capacidad, int stock,
            int minimoStock, float precioCompra, float precioVenta,
            float precioMayorista, boolean esMayor, boolean estado,
            String proveedor, Categoria categoria, Date fechaIn) {
//        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.codigoProducto = new SimpleStringProperty(codigoProducto);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.capacidad = new SimpleStringProperty(capacidad);
        this.stock = new SimpleIntegerProperty(stock);
        this.minimoStock = new SimpleIntegerProperty(minimoStock);
        this.precioCompra = new SimpleFloatProperty(precioCompra);
        this.precioVenta = new SimpleFloatProperty(precioVenta);
        this.precioMayorista = new SimpleFloatProperty(precioMayorista);
        this.esMayor = esMayor;
        this.estado = estado;
        this.proveedor = new SimpleStringProperty(proveedor);
        this.categoria = categoria;
        this.fechaIn = fechaIn;
    }

    public Producto() {

    }

    public Producto(Integer idProducto, String codigo, String nombre, String desc, String cap, Integer stock, Integer minStock, Float pc, Float pv, Float pm, boolean esMay, boolean estado, String proveedor, int cat, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public Producto(String codigo, String nombre, String desc, Integer stock, float pc, float pv, float pm, boolean esMay, boolean estado, String proveedor, Categoria cat, Object object) {
//    
//        
//    }
    //Metodos atributo: idProducto
    public int getIdProducto() {
        return idProducto.get();
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = new SimpleIntegerProperty(idProducto);
    }

    public IntegerProperty IdProductoProperty() {
        return idProducto;
    }
    //Metodos atributo: codigoProducto

    public String getCodigoProducto() {
        return codigoProducto.get();
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = new SimpleStringProperty(codigoProducto);
    }

    public StringProperty CodigoProductoProperty() {
        return codigoProducto;
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
    //Metodos atributo: descripcion

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public StringProperty DescripcionProperty() {
        return descripcion;
    }
    //Metodos atributo: capacidad

    public String getCapacidad() {
        return capacidad.get();
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = new SimpleStringProperty(capacidad);
    }

    public StringProperty CapacidadProperty() {
        return capacidad;
    }
    //Metodos atributo: stock

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock = new SimpleIntegerProperty(stock);
    }

    public IntegerProperty StockProperty() {
        return stock;
    }
    //Metodos atributo: minimoStock

    public int getMinimoStock() {
        return minimoStock.get();
    }

    public void setMinimoStock(int minimoStock) {
        this.minimoStock = new SimpleIntegerProperty(minimoStock);
    }

    public IntegerProperty MinimoStockProperty() {
        return minimoStock;
    }
    //Metodos atributo: precioCompra

    public float getPrecioCompra() {
        return precioCompra.get();
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = new SimpleFloatProperty(precioCompra);
    }

    public FloatProperty PrecioCompraProperty() {
        return precioCompra;
    }
    //Metodos atributo: precioVenta

    public float getPrecioVenta() {
        return precioVenta.get();
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = new SimpleFloatProperty(precioVenta);
    }

    public FloatProperty PrecioVentaProperty() {
        return precioVenta;
    }
    //Metodos atributo: precioMayorista

    public float getPrecioMayorista() {
        return precioMayorista.get();
    }

    public void setPrecioMayorista(float precioMayorista) {
        this.precioMayorista = new SimpleFloatProperty(precioMayorista);
    }

    public FloatProperty PrecioMayoristaProperty() {
        return precioMayorista;
    }
    //Metodos atributo: esMayor

    public boolean getEsMayor() {
        return esMayor;
    }

    public void setEsMayor(boolean esMayor) {
        this.esMayor = esMayor;
    }
    //Metodos atributo: estado

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    //Metodos atributo: proveedor

    public String getProveedor() {
        return proveedor.get();
    }

    public void setProveedor(String proveedor) {
        this.proveedor = new SimpleStringProperty(proveedor);
    }

    public StringProperty ProveedorProperty() {
        return proveedor;
    }
    //Metodos atributo: categoria

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto
                + ", codigoProducto=" + codigoProducto
                + ", nombre=" + nombre + ", descripcion="
                + descripcion + ", capacidad=" + capacidad
                + ", stock=" + stock + ", minimoStock="
                + minimoStock + ", precioCompra=" + precioCompra
                + ", precioVenta=" + precioVenta + ", precioMayorista="
                + precioMayorista + ", esMayor=" + esMayor + ", estado="
                + estado + ", proveedor="
                + proveedor + ", categoria=" + categoria + fechaIn + '}';
    }

}
