package com.Napster.CONTROLLER;

import com.Napster.MARIADB.Conection;
import com.Napster.MARIADB.MariaDBAlbum;
import com.Napster.MARIADB.MariaDBArtist;
import com.Napster.MODEL.Genre;
import com.Napster.MODEL.Song;
import com.Napster.Utils.Dialog;
import com.Napster.MARIADB.MariaDBGenre;
import com.Napster.Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;


public class AddGenre {
    @FXML
    private TextField genre;
    @FXML
    private Button add;
    @FXML
    private ComboBox<Genre> idGenre = new ComboBox<>();
    private static AddGenre.window actual_window = AddGenre.window.ADD;
    MariaDBGenre gen=new MariaDBGenre();


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
    protected void initialize(){
        switch (actual_window.getW()){
            case "ADD"->{
                idGenre.setVisible(false);

            }
            case "UPDATE"->{
                add.setText("BORRAR");
                idGenre.setConverter(Utils.genreConverter());
                idGenre.getItems().setAll(MariaDBGenre.listarTodos());
                /*name.setText(actualArtist.getNombre());
                nationality.setText(actualArtist.getNacionalidad());
                url.setText(actualArtist.getFoto());*/
            }
        }

    }
    @FXML
    public void addGenre(){
        if (add.getText().equals("AÑADIR")){
        Connection conn;
        conn = Conection.getConexion();
        if(conn!=null){
            gen.setName(genre.getText());
            try{
                gen.insertar(new MariaDBGenre());
                Dialog.showInformation("Genero Añadido","El Genero ha sido añadido sin problemas","Puede continuar");

            } catch (SQLException e){
                Dialog.showError("ERROR","ERROR","ERROR");
            }
        }else {
            Dialog.showError("Error de Conexion","No hay conexion a Internet","Verifique la conexion");

        }

        }else if (add.getText().equals("BORRAR")) {
            updateGenre();
        }



    }
    @FXML
    public void updateGenre(){
        Connection conn;
        conn = Conection.getConexion();
        if(conn!=null){
            gen.setId(idGenre.getSelectionModel().getSelectedItem().getId());
            gen.setName(genre.getText());
            MariaDBGenre a = new MariaDBGenre();
            a.actualizar(gen);
        }
    }
    public static void changeWindow(AddGenre.window w){
        actual_window=w;
    }
}
