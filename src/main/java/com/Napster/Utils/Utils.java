package com.Napster.Utils;

import com.Napster.DAO.ArtistDAO;
import com.Napster.MARIADB.MariaDBAlbum;
import com.Napster.MARIADB.MariaDBArtist;
import com.Napster.MARIADB.MariaDBGenre;
import com.Napster.MARIADB.MariaDBSong;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.Genre;
import com.Napster.MODEL.Song;
import javafx.util.StringConverter;

public class Utils {
    public static StringConverter<Artist> artistConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(Artist object) {
                return object == null ? null : object.toCombobox();
            }

            @Override
            public Artist fromString(String s) {
                MariaDBArtist a = null;
                if (s != null) {
                    int id = Integer.parseInt(s.substring(s.lastIndexOf(".")));
                    a = new MariaDBArtist(id);

                }
                return a;
            }
        };
    }

    public static StringConverter<Album> albumConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(Album object) {
                return object == null ? null : object.toCombobox();
            }

            @Override
            public Album fromString(String s) {
                MariaDBAlbum a = null;
                if (s != null) {
                    int id = Integer.parseInt(s.substring(s.lastIndexOf(".")));
                    a = new MariaDBAlbum(id);

                }
                return a;
            }
        };
    }

    public static StringConverter<Genre> genreConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(Genre object) {
                return object == null ? null : object.toCombobox();
            }

            @Override
            public Genre fromString(String s) {
                MariaDBGenre a = null;
                if (s != null) {
                    int id = Integer.parseInt(s.substring(s.lastIndexOf(".")));
                    a = new MariaDBGenre(id);

                }
                return a;
            }
        };
    }

    public static StringConverter<Song> songConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(Song object) {
                return object == null ? null : object.toCombobox();
            }

            @Override
            public Song fromString(String s) {
                MariaDBSong a = null;
                if (s != null) {
                    int id = Integer.parseInt(s.substring(s.lastIndexOf(".")));
                    a = new MariaDBSong(id);

                }
                return a;
            }
        };
    }
}
