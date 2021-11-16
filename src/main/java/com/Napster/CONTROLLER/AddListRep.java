package com.Napster.CONTROLLER;

import com.Napster.MARIADB.MariaDBAlbum;
import com.Napster.MARIADB.MariaDBSong;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Song;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddListRep {
    @FXML
    private TableView<Song> listSong;
    @FXML
    private TableColumn<Song,String> songCol;
    @FXML
    private TableColumn<Song,String> songList;
    @FXML
    private TableColumn<Song,String> albumCol;
    @FXML
    private TableView<Song> actualList;
    @FXML
    private TableColumn<Song, String> actualSong;

    @FXML
    private Button add;





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
        actualSong.setCellValueFactory(lista -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(lista.getValue().getNombre());
            return v;

        });

    }

    /**
     * Al seleccionar la cancion se añadiria a la tabla siguente, que seria la lista de canciones que vamso a añadir
     * a nuestra lista de reproduccion, no esta terminada esta parte
     */
    @FXML
    private void songChange(){
        Song s = songCol.getTableView().getItems().get(listSong.getSelectionModel().getSelectedIndex());
        actualList.getItems().addAll(s);
        System.out.println(s);

    }
}
