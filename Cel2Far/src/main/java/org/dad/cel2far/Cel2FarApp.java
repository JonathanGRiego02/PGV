package org.dad.cel2far;

import javafx.application.Application;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


public class Cel2FarApp extends Application {

    // View
    private TextField celsiusText, farenheitText;

    // Model
    private DoubleProperty celsius = new SimpleDoubleProperty();

    @Override
    public void start(Stage primaryStage) throws Exception {
        celsiusText = new TextField();
        celsiusText.setPrefColumnCount(5);

        farenheitText = new TextField();
        farenheitText.setPrefColumnCount(5);
        farenheitText.setEditable(false);
        farenheitText.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: transparent;");


        // Cajas horizontales para guardar el texto y su Field
        HBox celsiusBox = new HBox(5, new Label("Celcius"), celsiusText);
        HBox farenheitBox = new HBox(5, new Label("Farenheit"), farenheitText);

        celsiusBox.setAlignment(Pos.BASELINE_CENTER);
        farenheitBox.setAlignment(Pos.BASELINE_CENTER);

        VBox root = new VBox(5, celsiusBox, farenheitBox);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);

        Scene scene = new Scene(root, 320, 200);

        primaryStage.setTitle("Celcius to Farenheit");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Bindings
        celsiusText.textProperty().bindBidirectional(celsius, new NumberStringConverter());
// ( °C × 9 / 5) + 32 =  °F
// (32 °F − 32) × 5 / 9 = 0 °C
        DoubleExpression cel2far = celsius.multiply(9.0 / 5.0).add(32);

        farenheitText.textProperty().bind(cel2far.asString("%.2f"));

    }

}
