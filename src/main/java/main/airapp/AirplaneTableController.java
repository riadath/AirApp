package main.airapp;

import database.repository.AirplaneRepository;
import datamodel.AirplaneInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AirplaneTableController extends Controller {

    public Button addAirplane;
    @FXML
    private TableView<AirplaneInfo> airplaneTable;

    @FXML
    private TableColumn<AirplaneInfo, String> airplaneCode;

    @FXML
    private TableColumn<AirplaneInfo, Integer> airplaneId;

    @FXML
    private TableColumn<AirplaneInfo, String> airplaneName;

    @FXML
    private TableColumn<AirplaneInfo, Integer> airplaneSeat;

    @FXML
    private ListView<String> navbarList;

    public void getAirplane () {
        String[] temp_str = new AirplaneRepository().getCompanyAsString();
        navbarList.getItems().clear();
        for (String x : temp_str) navbarList.getItems().add(x);

        navbarList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String manufacturer = navbarList.getSelectionModel().getSelectedItem();
                if (manufacturer != null) {
                    airplaneTable.setItems(new AirplaneRepository().airplaneAsObservable(manufacturer));
                }
            }
        });

        airplaneTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        airplaneId.setCellValueFactory(new PropertyValueFactory<>("id"));
        airplaneId.setMaxWidth((1f * Integer.MAX_VALUE  * 25));

        airplaneName.setCellValueFactory(new PropertyValueFactory<>("name"));
        airplaneName.setMaxWidth((1f * Integer.MAX_VALUE  * 25));

        airplaneCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        airplaneCode.setMaxWidth((1f * Integer.MAX_VALUE  * 25));

        airplaneSeat.setCellValueFactory(new PropertyValueFactory<>("number_of_seats"));
        airplaneSeat.setMaxWidth((1f * Integer.MAX_VALUE  * 25));

        airplaneTable.setItems(new AirplaneRepository().airplaneAsObservable());
    }

    public void switchToAirplaneForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("airplanes-page-form.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
