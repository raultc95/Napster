package com.Napster.CONTROLLER;

import com.Napster.MARIADB.MariaDBListRep;
import com.Napster.MARIADB.MariaDBSong;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.ListsRep;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class Principal {
    @FXML
    private ImageView imguser;
    @FXML
    private TableView<Artist> tabla;
    @FXML
    private TableView<ListsRep> listarep;
    @FXML
    private TableColumn<ListsRep,String> listado;


    @FXML
    private void initialize(){
        configuraTabla();
        listarep.setItems(FXCollections.observableList(MariaDBListRep.listarTodos()));

    }


    private void configuraTabla() {
        listado.setCellValueFactory(lista -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(lista.getValue().getNombre());
            return v;

        });

    }

}
