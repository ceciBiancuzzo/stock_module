package proyectostock.vista;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import proyectostock.conexion.CategoriaDB;
import proyectostock.conexion.ProductoDB;
import proyectostock.modelo.Categoria;
import proyectostock.modelo.Producto;

public class GestionStockController implements Initializable {

    @FXML
    private TableView<Producto> tablaProducto;
    @FXML
    private TableColumn<Producto, String> colCodigo;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, String> colDescrip;

    @FXML
    private TableColumn<Producto, String> colCateg;
    @FXML
    private TableColumn<Producto, Float> colPrecioC;
    @FXML
    private TableColumn<Producto, Float> colPrecioV;
    @FXML
    private TableColumn<Producto, Float> colPrecioM;
    @FXML
    private TableColumn<Producto, Integer> colCant;
    @FXML
    private JFXTextField jtfBuscar;

    //listas
    private ObservableList<Producto> listaProductos;
    private ObservableList<Categoria> listaCategorias;
    private ObservableList<Producto> listaPedidos;
    //nuevo producto
    @FXML
    private JFXTextField tfCodigo;
    @FXML
    private JFXTextField tfNombre;
    @FXML
    private JFXTextField tfDescrip;
    @FXML
    private JFXTextField tfCapacidad;
    @FXML
    private JFXComboBox<Categoria> cbCateg;
    @FXML
    private JFXTextField tfProv;
    @FXML

    private JFXTextField tfStock;  //cantidad
    @FXML
    private JFXTextField tfStockMin;
    @FXML
    private JFXTextField tfPrecioC;

    @FXML
    private JFXTextField tfPrecioV;
    @FXML
    private JFXTextField tfPrecioM;

    // editar producto
    @FXML
    private JFXTextField tfCodigoE;
    @FXML
    private JFXTextField tfNombreE;
    @FXML
    private JFXTextField tfDescripE;
    @FXML
    private JFXTextField tfCapacidadE;
    @FXML
    private JFXComboBox<Categoria> cbCategE;
    @FXML

    private JFXTextField tfProvE;
    @FXML
    private JFXTextField tfStockE;

    @FXML
    private JFXTextField tfStockMinE;

    @FXML
    private JFXTextField tfPrecioCE;

    @FXML
    private JFXTextField tfPrecioVE;
    @FXML
    private JFXTextField tfPrecioME;

    @FXML
    private TableColumn<Producto, String> colCapacidad;

    @FXML
    private TableColumn<Producto, Date> colFechaIn;
    Producto p;
    ProductoDB daoProducto = new ProductoDB();
    CategoriaDB daoCategoria = new CategoriaDB();

    @FXML
    private JFXButton btAgregarCat;
    @FXML
    private JFXTextField tfAgregarCat;
    @FXML
    private JFXButton btOkCat;
    private JFXButton bteliminCat;
    @FXML
    private Label lNotificaciones;
    @FXML
    private JFXButton btAgregarCat1;
    @FXML
    private JFXTextField tfAgregarCat1;
    @FXML
    private JFXButton btOkCat1;
    @FXML
    private Button btActualizar;
    @FXML
    private JFXTextField tfNombreCat;
    @FXML
    private TableView<Categoria> tablaCategoria;
    @FXML
    private TableColumn<Categoria, String> colCategoriaCateg;
    @FXML
    private JFXTextField jtfBuscarCat;
    @FXML
    private Label labelTotalProd;
    @FXML
    private Label labelCantProdCateg;
    @FXML
    private JFXButton btAtras;
    @FXML
    private JFXTextField tfCantPed;
    @FXML
    private TableView<Producto> tablaPedido;
    @FXML
    private TableColumn<Producto, String> colCodigoPed;
    @FXML
    private TableColumn<Producto, String> colNombrePed;
    @FXML
    private TableColumn<Producto, String> colDescripcionPed;
    @FXML
    private TableColumn<Producto, Integer> colCantidadPed;
    @FXML
    private Tab tabEditar;
    @FXML
    private Tab tabPedido;
    @FXML
    private Label lbErrorCodigo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfCodigo.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // CREAR CON DO-WHILE
                for (int i = 0; i < listaProductos.size(); i++) {
                    //String codigo = tablaProducto.getItems().get(i).getCodigoProducto().toString();

                    if (tablaProducto.getItems().get(i).getCodigoProducto().toString().equalsIgnoreCase(tfCodigo.getText())) {
                        lNotificaciones.setText("El codigo ya existe");
                        break;

                    } else {
                        lNotificaciones.setText("");
                    }
                    //System.out.println(codigo);
                }

            }
        });
        //inicializar listas
        listaProductos = FXCollections.observableArrayList();
        listaCategorias = FXCollections.observableArrayList();
        listaPedidos = FXCollections.observableArrayList();

        cargarListas();

        configurarComponentes();
        //configuracion tabla pedido
        //cantidad

    }

    @FXML
    public void cargarListas() {

        lNotificaciones.setText("");
        listaProductos.clear();
        listaCategorias.clear();
        limpiarCampos();

//Hecho ceci
        try {
            ProductoDB daoProducto = new ProductoDB();
            CategoriaDB daoCategoria = new CategoriaDB();
            listaProductos.addAll(daoProducto.buscarTodos());
            listaCategorias.addAll(daoCategoria.buscarTodos());
            tablaProducto.setItems(listaProductos);
            tablaCategoria.setItems(listaCategorias);
            cbCategE.setItems(listaCategorias);
            cbCateg.setItems(listaCategorias);
            cbCategE.setValue(listaCategorias.get(0));  //con esto carga lo que hay en el combobox categ

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configurarComponentes() {

        colCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigoProducto"));

        colNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        colDescrip.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));

        colCapacidad.setCellValueFactory(new PropertyValueFactory<Producto, String>("capacidad"));

        colCateg.setCellValueFactory(new PropertyValueFactory<Producto, String>("categoria"));
        colPrecioC.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precioCompra"));
        colPrecioV.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precioVenta"));
        colPrecioM.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precioMayorista"));
        colCant.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock")); //cantidad
        colFechaIn.setCellValueFactory(new PropertyValueFactory<Producto, Date>("fechaIn")); //cantidad
        //configuracion tabla categoria de TAB CATEGORIAS
        colCategoriaCateg.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombre"));
//        if (tabEditar.isSelected()) {
        System.out.println("tab on");
//        llenarCamposEditar();
//        }

        //tabla pedido
        colCodigoPed.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigoProducto"));
        colNombrePed.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        colDescripcionPed.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        colCantidadPed.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));
    }

    @FXML
    private void llenarCamposEditar() {

        tablaProducto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Producto>() {

            @Override
            public void changed(ObservableValue<? extends Producto> observable, Producto valorAnterior, Producto valorSeleccionado) {
                try {
                    //System.out.println("se seleccionó un registro");
                    //System.out.println("Nombre alumno seleccionado"+ valorSeleccionado.getNombre());
                    tfCodigoE.setText(valorSeleccionado.getCodigoProducto());
                    tfNombreE.setText(valorSeleccionado.getNombre());
                    tfDescripE.setText(valorSeleccionado.getDescripcion());
                    tfCapacidadE.setText(valorSeleccionado.getCapacidad());
                    cbCategE.setValue(valorSeleccionado.getCategoria());
                    tfProvE.setText(valorSeleccionado.getProveedor());
                    tfStockE.setText(String.valueOf(valorSeleccionado.getStock()));
                    tfStockMinE.setText(String.valueOf(valorSeleccionado.getMinimoStock()));
                    tfPrecioCE.setText(String.valueOf(valorSeleccionado.getPrecioCompra()));
                    tfPrecioVE.setText(String.valueOf(valorSeleccionado.getPrecioVenta()));
                    tfPrecioME.setText(String.valueOf(valorSeleccionado.getPrecioMayorista()));
                } catch (Exception e) {
                    e.getMessage();

                }

            }
        }
        );

    }

    private void configurarTablaProductoPedido() {

        tablaProducto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Producto>() {
            @Override
            public void changed(ObservableValue<? extends Producto> observable, Producto valorAnterior, Producto valorSeleccionado) {
                //System.out.println("se seleccionó un registro");
                //System.out.println("Nombre alumno seleccionado"+ valorSeleccionado.getNombre());
//                tfCodigoE.setText(String.valueOf(valorSeleccionado.getCodigoProducto()));
//                tfNombreE.setText(valorSeleccionado.getNombre());
//                tfDescripE.setText(valorSeleccionado.getDescripcion());
//                tfCapacidadE.setText(valorSeleccionado.getCapacidad());
//                cbCategE.setValue(valorSeleccionado.getCategoria());
//                tfProvE.setText(valorSeleccionado.getProveedor());
//                tfStockE.setText(String.valueOf(valorSeleccionado.getStock()));
//                tfStockMinE.setText(String.valueOf(valorSeleccionado.getMinimoStock()));
//                tfPrecioCE.setText(String.valueOf(valorSeleccionado.getPrecioCompra()));
//                tfPrecioVE.setText(String.valueOf(valorSeleccionado.getPrecioVenta()));
//                tfPrecioME.setText(String.valueOf(valorSeleccionado.getPrecioMayorista()));

            }
        }
        );

    }

    @FXML
    private void tfBuscar(KeyEvent event) {

//        tablaProducto.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> verDetallesCliente(newValue));
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Producto> filteredData = new FilteredList<>(listaProductos, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        jtfBuscar.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Producto producto) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (producto.getCategoria().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Producto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablaProducto.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablaProducto.setItems(sortedData);

    }

//    public Producto setProducto(Producto p) {
//
//        Categoria c = new Categoria();
//        c = cbCateg.getSelectionModel().getSelectedItem();
//        System.out.println("Categoria select: " + c.toString());
//        p.setIdProducto(0);
//        p.setCodigoProducto(tfCodigo.getText());
//        p.setNombre(tfNombre.getText());
//        p.setDescripcion(tfDescrip.getText());
//        p.setCapacidad(tfCapacidad.getText());
//        p.setStock(Integer.parseInt(tfStock.getText()));
//        p.setMinimoStock(Integer.parseInt(tfStockMin.getText()));
//        p.setPrecioCompra(Float.parseFloat(tfPrecioC.getText()));
//        p.setPrecioVenta(Float.parseFloat(tfPrecioC.getText()));
//        p.setPrecioMayorista(Float.parseFloat(tfPrecioM.getText()));
//        p.setEsMayor(true);
//        p.setProveedor(tfProv.getText());
//        p.setCategoria(c);
//        System.out.println("Prod:" + p.toString());
//
//        return p;
//    }
    @FXML
    private void insertarProdOK(ActionEvent event) {    //agregar articulo

        Categoria cat;
        cat = cbCateg.getSelectionModel().getSelectedItem();
        System.out.println("Categoria select: " + cat.toString());
        String codigo = tfCodigo.getText();
        String nombre = tfNombre.getText();
        String desc = tfDescrip.getText();
        String cap = tfCapacidad.getText();
        Integer stock = Integer.parseInt(tfStock.getText());
        Integer minStock = Integer.parseInt(tfStockMin.getText());
        float pc = Float.parseFloat(tfPrecioC.getText());
        float pv = Float.parseFloat(tfPrecioC.getText());
        float pm = Float.parseFloat(tfPrecioM.getText());
        boolean estado = false;
        boolean esMay = true;
        String proveedor = tfProv.getText();

        Producto pr = new Producto(codigo, nombre, desc, cap, stock, minStock,
                pc, pv, pm, esMay, estado, proveedor, cat, null);
//
//        if (codigoProductoRepetido(pr)) {
//            if (daoProducto.insertarProducto(pr)) {
//                System.out.println("OK insert");
//                cargarListas();
//                limpiarCampos();
//                lNotificaciones.setText("El producto se ingreso de forma correcta");
//            }
        daoProducto.insertarProducto(pr);

//        else {
//            lNotificaciones.setText("Error Campos vacios o incorrectos");
        limpiarCampos();

    }

    @FXML
    private void editarProdOK(ActionEvent event) {
//        llenarCamposEditar();
        Producto pr = null;
        System.out.println("editarProd");
        if (tablaProducto.getSelectionModel().getSelectedItem() != null) {
            pr = tablaProducto.getSelectionModel().getSelectedItem();
//        ProductoDB cc = new ProductoDB();
//        int idProducto = tablaProducto.getSelectionModel().getSelectedItem().getIdProducto();
            pr.setCodigoProducto(tfCodigoE.getText());
            pr.setNombre(tfNombreE.getText());
            pr.setDescripcion(tfDescripE.getText());
            pr.setCapacidad(tfCapacidadE.getText());
            Categoria cat;
            pr.setCategoria(cbCategE.getSelectionModel().getSelectedItem());
            System.out.println("Categoria select: " + pr.getCategoria());
            pr.setStock(Integer.parseInt(tfStockE.getText()));
            pr.setMinimoStock(Integer.parseInt(tfStockMinE.getText()));
            pr.setPrecioCompra(Float.parseFloat(tfPrecioCE.getText()));
            pr.setPrecioVenta(Float.parseFloat(tfPrecioCE.getText()));
            pr.setPrecioMayorista(Float.parseFloat(tfPrecioME.getText()));

            pr.setEstado(false);
            pr.setEsMayor(true);
            pr.setProveedor(tfProvE.getText());
//        Date fechaIn = tablaProducto.getSelectionModel().getSelectedItem().getFechaIn();
        }
        if (daoProducto.actualizarProducto(pr)) {
            System.out.println("OK editado");
            cargarListas();
        }

        limpiarCampos();
        lNotificaciones.setText("El producto se ingreso de forma correcta");

//        try {
////            cc.actualizarProducto(p);
////            cargarListas();
//            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
//            a.setTitle(" Producto Editado");
//            a.setHeaderText("Producto editado con exito");
//            a.showAndWait();
//        } catch (Exception e) {
//            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
//            a.setTitle("error");
//            a.setHeaderText("No se pudo editar" + e.getMessage());
//            a.setContentText(e.getMessage());
//            a.showAndWait();
//        }
    }

    //Hecho Ceci
    @FXML
    private void eliminarProducto(ActionEvent event) {
        ProductoDB cp = new ProductoDB();
        int selectedIndex = tablaProducto.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            cp.eliminarProducto((tablaProducto.getSelectionModel().getSelectedItem().getIdProducto()));
            tablaProducto.getItems().remove(selectedIndex);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminando");
            alert.setHeaderText("Producto eliminado con exito");
            alert.showAndWait();

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No seleccionado");
            alert.setHeaderText("Producto no selecciona");
            alert.setContentText("Por favor selecciona un producto de la tabla");
            alert.showAndWait();
        }
        tablaProducto.refresh();

    }

    @FXML
    private void verAgregarCategoria(ActionEvent event) {

        cbCateg.setVisible(false);
        tfAgregarCat.setVisible(true);
        btOkCat.setVisible(true);
        btAtras.setVisible(true);
//        bteliminCat.setVisible(false);

    }

    @FXML
    private void ocultarAgregarCategoria() {

        tfAgregarCat.setVisible(false);
        btOkCat.setVisible(false);
        cbCateg.setVisible(true);
//        okAgregarCategoria();
        btAtras.setVisible(false);

//        cbCateg.setItems(listaCategorias);
//        cargarListas();
        //cbCateg.setItems(listaCategorias);
    }

    @FXML
    private void okAgregarCategoria() {
        Categoria c = new Categoria(0, tfAgregarCat.getText());
        //validacion para que no llene una categoria vacia
        if (tfAgregarCat.getText().length() == 0) {
            lNotificaciones.setText("No ha ingresado ninguna categoria");

            cbCateg.setVisible(false);
            tfAgregarCat.setVisible(true);
            btOkCat.setVisible(false);
            btAtras.setVisible(true);
            System.out.println("Correcto insert categoria");

        } else {

            daoCategoria.insertarCategoria(c);
            ocultarAgregarCategoria();
             cargarListas();
//            tablaCategoria.setItems(listaCategorias);
        }

//        else {
//            limpiarCampos();
//            lNotificaciones.setText("No ha ingresado ninguna categoria");
//            cbCateg.setVisible(false);
//            tfAgregarCat.setVisible(true);
//            btOkCat.setVisible(true);
        // System.out.println("Validacion incorrecta");
        // Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.setTitle("Usuario y/o contraseña inválidos");
        //alert.setHeaderText("Por favor digite su usuario y contraseña");
        //alert.setContentText("Acceso no autorizado");
    }

    @FXML
    private void limpiarCampos() {

        tfCodigo.setText("");
        tfNombre.setText("");
        tfDescrip.setText("");
        tfCapacidad.setText("");
        tfAgregarCat.setText("");
        tfPrecioC.setText("");
        tfPrecioV.setText("");
        tfPrecioM.setText("");
        tfStock.setText("");
        tfStockMin.setText("");
        tfProv.setText("");
        tfCodigoE.setText("");
        tfNombreE.setText("");
        tfDescripE.setText("");
        tfCapacidadE.setText("");
//        tfAgregarCat.setText("");
        tfPrecioCE.setText("");
        tfPrecioVE.setText("");
        tfPrecioME.setText("");
        tfStockE.setText("");
        tfStockMinE.setText("");
        tfProvE.setText("");
//        cbCateg.setValue(null);
//        cbCategE.setValue(null);      
//        lNotificaciones.setText("");
    }

    private void verEliminarCategoria(ActionEvent event) {

        Categoria c = cbCateg.getSelectionModel().getSelectedItem();
//        System.out.println("Categoria seleccionada: " + c.toString());
        cbCateg.setVisible(true);

        daoCategoria.eliminarCategoria(c);
        bteliminCat.setVisible(true);
        tfAgregarCat.setVisible(false);
        btOkCat.setVisible(false);

//        cbCateg.setVisible(false);
//        tfAgregarCat.setVisible(true);
//        btOkCat.setVisible(true);
    }

    private boolean codigoProductoRepetido(Producto pr) {
        //recorre lista vehiculos traida de la bd
        Iterator<Producto> i = listaProductos.iterator();
        while (i.hasNext()) {

            Producto p = i.next();
            //revisa y valida si el codigo existe
            if (p.getCodigoProducto().equals(pr.getCodigoProducto())) { //(p.getCodigoProducto().equals(tfCodigoE.getText()))

                System.err.println("Error Código repetido o vacío");

                //CARGA ITEM VEHICULO DE LA LISTA listaV a listaV2
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    private boolean validacionNuevoP() {
//        if (tfCodigo.getText().length() == 0 || tfCodigo.getText() == null) {
//            lNotificaciones.setText("Código Vacio");
//        }
//        if (tfNombre.getText().length() == 0 || tfNombre.getText() == null) {
//            lNotificaciones.setText("Nombre Vacio");
//        }
//        if (tfDescrip.getText().length() == 0 || tfDescrip.getText() == null) {
//            lNotificaciones.setText("Descripción Vacia");
//        }
//        if (tfCapacidad.getText().length() == 0 || tfCapacidad.getText() == null) {
//            lNotificaciones.setText("Capacidad Vacia");
//        }
//        if (cbCateg.getValue() == null) {
//            lNotificaciones.setText("Categoria Vacio");
//        }
//        if (tfProv.getText().length() == 0 || tfProv.getText() == null) {
//            lNotificaciones.setText("Proveedor Vacio");
//        }
//        if (tfStock.getText().length() == 0 || tfStock.getText() == null) {
//            lNotificaciones.setText("Stock Vacia");
//        }
//
//        if (tfStockMin.getText().length() == 0 || tfStockMin.getText() == null) {
//            lNotificaciones.setText("MInimo Stock Vacia");
//        }
//
//        if (tfPrecioC.getText().length() != 0) {
//            try {
//                Float.parseFloat(tfPrecioC.getText());
//            } catch (NumberFormatException e) {
//                lNotificaciones.setText("Valor de precio no permitido!\n");
//            }
//            if (tfPrecioV.getText().length() != 0) {
//                try {
//                    Float.parseFloat(tfPrecioV.getText());
//                } catch (NumberFormatException e) {
//                    lNotificaciones.setText("Valor de precio no permitido!\n");
//                }
//            }
//            if (tfPrecioM.getText().length() != 0) {
//                try {
//                    Float.parseFloat(tfPrecioM.getText());
//                } catch (NumberFormatException e) {
//                    lNotificaciones.setText("Valor de precio no permitido!\n");
//                }
//            }
//        if (codigoProductoRepetido(pr)) {
//            lNotificaciones.setText("Codigo nulo o repetido");
//        }

        return true;
    }

    @FXML
    private void tfBuscarCategorias(KeyEvent event) {

        FilteredList<Categoria> filteredData = new FilteredList<>(listaCategorias, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        jtfBuscarCat.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Categoria categoria) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (categoria.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Categoria> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablaCategoria.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablaCategoria.setItems(sortedData);

    }

    @FXML
    private void eliminarCategoria(ActionEvent event) {

//     Categoria c = tablaCategoria.getSelectionModel().getSelectedItem();
//
//        System.out.println("Categoria seleccionada: " + c.toString());
//        cbCateg.setVisible(true);
//
//        if (daoCategoria.eliminarCategoria(c)) {
//            System.out.println("Categoria " + c.getNombre() + " eliminada correctamente");
//            lNotificaciones.setText("Categoria " + c.getNombre() + " eliminada correctamente");
//        } else {
//            lNotificaciones.setText("Error al eliminar categoria");
//            limpiarCampos();
//    }
        Categoria c = tablaCategoria.getSelectionModel().getSelectedItem();

        System.out.println("Categoria seleccionada: " + c.toString());
        //cbCateg.setVisible(true);

        if (daoCategoria.eliminarCategoria(c)) {
            System.out.println("Categoria " + c.getNombre() + " eliminada correctamente");
            lNotificaciones.setText("Categoria " + c.getNombre() + " eliminada correctamente");
            limpiarCampos();
            cargarListas();

        } else {
            lNotificaciones.setText("Error al eliminar categoria");
            limpiarCampos();
        }
    }

    @FXML
    private void okAgregarCategoriaCateg() {
        Categoria c = new Categoria(0, tfNombreCat.getText());
        //validacion para que no llene una categoria vacia
        if (tfNombreCat.getText().length() == 0) {
            lNotificaciones.setText("No ha ingresado ninguna categoria");

        } else {

            daoCategoria.insertarCategoria(c);
            System.out.println("Correcto insert categoria");
            cargarListas();
            tfNombreCat.setText("");
        }
    }

    private boolean validarCant() {

        int cant = Integer.parseInt(tfCantPed.getText());
        Producto p = tablaProducto.getSelectionModel().getSelectedItem();

        if (tfCantPed.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Valor incorrecto");
            alert.setHeaderText("No hay artículos disponibles");
//            alert.setContentText("Por ");

            alert.showAndWait();
            System.out.println("Cantidad: " + cant);
            tfCantPed.setText("");
            return false;
        }

        if (p.getStock() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Valor incorrecto");
            alert.setHeaderText("No hay artículos disponibles");
//            alert.setContentText("Por ");

            alert.showAndWait();
            System.out.println("Cantidad: " + cant);
            tfCantPed.setText("");
            return false;
        }

        if (cant == 0 || tfCantPed.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Valor incorrecto");
            alert.setHeaderText("No se puede ingresar cantidad 0 (cero)");
            alert.setContentText("Por favor reintentar");

            alert.showAndWait();
            System.out.println("Cantidad: " + cant);
            tfCantPed.setText("");
            return false;
        }
        if (Integer.parseInt(tfCantPed.getText()) > p.getStock()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Valor incorrecto");
            alert.setHeaderText("Cantidad mayor a la disponible");
            alert.setContentText("Por favor reintentar");

            alert.showAndWait();
            tfCantPed.setText("");
            return false;

        }

        return true;
    }

    @FXML
    private boolean validarCantidad(ActionEvent event) {
        tablaPedido.setItems(listaPedidos);
        int cant;

//        && cant > p.getStock()
        try {
            if (tablaProducto.getSelectionModel().getSelectedIndex() == 0 && tfCantPed == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(main.getPrimaryStage());
                alert.setTitle("Error");
                alert.setHeaderText("Ningun elemento seleccionado");
                alert.setContentText("Por favor seleccionar un artículo");

                alert.showAndWait();

            } else {
                Producto p = tablaProducto.getSelectionModel().getSelectedItem();
                cant = Integer.parseInt(tfCantPed.getText());
                if (validarCant()) {
                    if (daoProducto.actualizarStock(p, cant)) {
                        System.out.println("Stock actualizado ok");
                        lNotificaciones.setText("Stock actualizado ok");
                        p.setStock(cant);
                        listaPedidos.add(p);

//                        tablaProducto.setItems(daoProducto.buscarTodos());
                        cargarListas();
//                        tablaProducto.refresh();
//                        
                        return true;
                    } else {
                        System.out.println("Error Stock NO actualizado");
                        lNotificaciones.setText("Error Stock NO actualizado");
                        tfCantPed.setText("");
                    }

//        cant = Integer.parseInt(tfCantPed.getText());
                }
            }
//
//        return true;

        } catch (Exception e) {
            System.out.println("Dentro del catch");
            return false;
        }

        return true;
    }

    private void onTabEditar(Event event) {
        llenarCamposEditar();
    }

    @FXML
    private void OnLimpiarTabla(ActionEvent event) {

//        colCodigoPed.setText("");
//        colNombrePed.setText("");
//        colDescripcionPed.setText("");
//        colCantidadPed.setText("");
        listaPedidos.removeAll(listaPedidos);
    }

    @FXML
    private boolean OnRestablecerStock(ActionEvent event) {

        Producto p = tablaPedido.getSelectionModel().getSelectedItem();
        int cant;

        cant = Integer.parseInt(tfCantPed.getText());
        if (p != null) {
            if (daoProducto.restablecerStock(p, cant)) {
                System.out.println("Stock reestablecido ok");
                lNotificaciones.setText("Stock actualizado ok");
//                          p.setStock(cant);
                listaPedidos.remove(p);
                tablaPedido.refresh();

                cargarListas();

                tablaProducto.refresh();

                return true;
            } else {
                System.out.println("Error Stock NO actualizado");
                lNotificaciones.setText("Error Stock NO actualizado");
                tfCantPed.setText("");
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Acción incorrecta");
            alert.setHeaderText("Seleccione un producto de la tabla pedido");
            alert.setContentText("Por favor reintentar");

            alert.showAndWait();
        }
        return false;

    }

}
