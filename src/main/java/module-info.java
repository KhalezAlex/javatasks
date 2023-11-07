module ru.spb.reshenie.javatasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens ru.spb.reshenie.javatasks to javafx.fxml;
    opens ru.spb.reshenie.javatasks.entity to javafx.base;
    exports ru.spb.reshenie.javatasks;
    exports ru.spb.reshenie.javatasks.controllers;
    opens ru.spb.reshenie.javatasks.controllers to javafx.fxml;
    opens ru.spb.reshenie.javatasks.ui to javafx.fxml;
}