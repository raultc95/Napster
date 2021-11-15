package com.Napster.CONTROLLER;

import com.Napster.MARIADB.MariaDBSong;
import com.Napster.MODEL.Song;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddListRep {
    @FXML
    private TableView<MariaDBSong> listSong;
    @FXML
    private TableColumn<Song,String> songCol;





    @FXML
    private void initialize(){
        tablalista();
        listSong.setItems(FXCollections.observableList(MariaDBSong.listarTodos()));

    }

    @FXML
    private void tablalista(){
        songCol.setCellValueFactory(lista -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(lista.getValue().getNombre());
            return v;

        });

    }
}
