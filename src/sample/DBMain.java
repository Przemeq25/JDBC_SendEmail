package sample;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

import java.sql.*;
import java.util.ArrayList;

public class DBMain {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://195.150.230.210:5434/2019_cichon_przemyslaw";
    static final String USER = "2019_cichon_przemyslaw";
    static final String PASS ="przemcio687";


    public ArrayList connectDatabase(){
        Connection connection = null;
        ArrayList<String> listTables = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pg_tables where schemaname='dziekanat';");
            while (rs.next()) {
                listTables.add(rs.getString("tablename"));
            }
            return listTables;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listTables;
    }

    public static ObservableList selectFromTables(javafx.scene.control.TableView tab, String tablename){
        Connection connection = null;
        ResultSet rs = null;
        ObservableList rowsData = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM dziekanat."+tablename);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int finalIdx = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                        rs.getMetaData().getColumnName(i+1)
                );
                column.setCellValueFactory(param ->
                        new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx))
                );
                tab.getColumns().add(column);
            }
            while (rs.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                rowsData.add(row);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }finally {
            try{
                connection.close();
                rs.close();
            }catch (Exception err){
                err.printStackTrace();
            }
        }
        return rowsData;
    }


    public ArrayList emailUsers(){
        Connection connection = null;
        ArrayList<String> listTables = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select email from dziekanat.email WHERE zgoda IS TRUE ");
            while (rs.next()) {
                listTables.add(rs.getString("email"));
            }
            return listTables;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listTables;
    }
}
