module org.example.womenshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;
    requires java.desktop;


    opens org.example.womenshop to javafx.fxml;
    exports org.example.womenshop;
}