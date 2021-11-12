package com.Napster.CONTROLLER;

import com.Napster.DAO.ArtistDAO;
import com.Napster.MARIADB.Conection;
import com.Napster.MARIADB.MariaDBAlbum;
import com.Napster.MARIADB.MariaDBArtist;
import com.Napster.MARIADB.MariaDBSong;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Artist;
import com.Napster.Utils.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.Napster.Utils.Utils;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddAlbum {
    @FXML
    private TextField tittle;
    @FXML
    private TextField date;
    @FXML
    private TextField url;
    @FXML
    private ComboBox<Artist> listArtist = new ComboBox<>();
    @FXML
    private ComboBox<Album> idAlbum = new ComboBox<>();

    @FXML
    private Button add;
    @FXML
    private Button delete;

    private static AddAlbum.window actual_window = AddAlbum.window.ADD;

    MariaDBAlbum album = new MariaDBAlbum();
    //MariaDBArtist a = new MariaDBArtist();
    List<Artist> a = new ArrayList<>();


    public enum window {
        ADD("ADD"),
        UPDATE("UPDATE");
        private final String w;

        window(String w) {
            this.w = w;
        }

        public String getW() {
            return this.w;
        }

    }

    @FXML
    protected void initialize() {
        System.out.println(MariaDBArtist.listarTodos());

        listArtist.setConverter(Utils.artistConverter());
        listArtist.setItems(FXCollections.observableList(MariaDBArtist.listarTodos()));

        switch (actual_window.getW()) {
            case "ADD" -> {
                delete.setVisible(false);
                idAlbum.setVisible(false);
                listArtist.setConverter(Utils.artistConverter());
                listArtist.getItems().setAll(MariaDBArtist.listarTodos());
            }
            case "UPDATE" -> {
                add.setText("ACTUALIZAR");
                idAlbum.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        tittle.setText(newValue.getNombre());
                        date.setText(newValue.getFecha_publicacion()+"");
                        url.setText(newValue.getFoto());

                    }
                });
                album.setNombre(album.getNombre());
                idAlbum.setConverter(Utils.albumConverter());
                idAlbum.getItems().setAll(MariaDBAlbum.listarTodos());
                /*name.setText(actualArtist.getNombre());
                nationality.setText(actualArtist.getNacionalidad());
                url.setText(actualArtist.getFoto());*/
            }
        }


    }
    @FXML
    public void addAlbum() {
        if (add.getText().equals("AÑADIR")) {
            Connection conn;
            conn = Conection.getConexion();
            if (conn != null) {
                album.setNombre(tittle.getText());
                album.setFecha_publicacion(Integer.parseInt(date.getText()));
                album.setArtist(listArtist.getSelectionModel().getSelectedItem());
                try {
                    album.insertar(new MariaDBAlbum());

                    Dialog.showInformation("Album Añadido", "El album ha sido añadido sin problemas", "Puede continuar");

                } catch (SQLException e) {
                    Dialog.showError("ERROR", "ERROR", "ERROR");
                }
            } else {
                Dialog.showError("Error de Conexion", "No hay conexion a Internet", "Verifique la conexion");

            }
        } else if (add.getText().equals("ACTUALIZAR")) {
            updateAlbum();
        }


    }

    public void updateAlbum() {
        Connection conn;
        conn = Conection.getConexion();
        if(conn!=null){
            album.setId(idAlbum.getSelectionModel().getSelectedItem().getId());
            album.setNombre(tittle.getText());
            album.setFecha_publicacion(Integer.parseInt(date.getText()));
            album.setArtist(listArtist.getSelectionModel().getSelectedItem());
            MariaDBAlbum a = new MariaDBAlbum();
            a.actualizar(album);

        }

    }
    public void deleteAlbum(){
        Connection conn;
        conn = Conection.getConexion();
        if(conn!=null){
            album.setId(idAlbum.getSelectionModel().getSelectedItem().getId());
            try{
                album.eliminar(album);
                idAlbum.setConverter(Utils.albumConverter());
                idAlbum.setItems(FXCollections.observableList(MariaDBAlbum.listarTodos()));
                Dialog.showInformation("ALBUM ELIMINADO","El album ha sido eliminado sin problemas","Puede continuar");

            } catch (SQLException e){
                Dialog.showError("ERROR","ERROR","ERROR");
            }

        }
    }

    public static void changeWindow(AddAlbum.window w) {
        actual_window = w;
    }

    public void listar() {
        MariaDBArtist a;
        listArtist.getItems().add(a = new MariaDBArtist());
    }
}
