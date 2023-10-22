module ru.spb.reshenie.javatasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ru.spb.reshenie.javatasks to javafx.fxml;
    exports ru.spb.reshenie.javatasks;
}