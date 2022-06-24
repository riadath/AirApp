package main.airapp;

import database.repository.AirplaneRepository;
import database.repository.FlightRepository;
import datamodel.AirplaneInfo;
import datamodel.FlightInfo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightTableController {

    @FXML
    private ComboBox<AirplaneInfo> airplaneBox;

    @FXML
    private TableColumn<FlightInfo, String> airplaneName;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private Label warningLabel;

    @FXML
    private TableColumn<FlightInfo, Integer> flightId;

    @FXML
    private TableColumn<FlightInfo, LocalDate> flightDate;

    @FXML
    private TableColumn<FlightInfo, String> flightDest;

    @FXML
    private TableColumn<FlightInfo, String> flightSrc;

    @FXML
    private TableView<FlightInfo> flightTable;

    @FXML
    private TableColumn<FlightInfo, LocalTime> flightTime;

    @FXML
    private TableColumn<FlightInfo, Integer> remainingSeat;

    @FXML
    void filterFlight(ActionEvent event) {
        final String airplaneCode = (airplaneBox.getValue()==null ? null : airplaneBox.getValue().getName());
        LocalDate from = dateFrom.getValue();
        StringBuilder warningText = new StringBuilder("");
        if (from == null) {
            warningText.append("Starting Date not found\n");
            from = LocalDate.parse("1970-01-01");
        }
        LocalDate to = dateTo.getValue();
        if (to == null) {
            warningText.append("End Date not Found");
            to = LocalDate.now();
        }
        warningLabel.setText(String.valueOf(warningText));
        flightTable.setItems(new FlightRepository().filterFlightAsObservableList(from, to, null, null, airplaneCode));
    }

    // fills the combobox that holds the list of airplane names scheduled flights
    private void fillCombo () {
        airplaneBox.setItems(new AirplaneRepository().airplaneAsObservable());

        // sets the thing to show on teh display box
        airplaneBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(AirplaneInfo airplaneInfo) {
                if(airplaneInfo == null) return "<Airplane Filter>";
                return airplaneInfo.getName();
            }

            @Override
            public AirplaneInfo fromString(String s) {
                return null;
            }
        });

        // sets the things to show on the list
        airplaneBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<AirplaneInfo> call(ListView<AirplaneInfo> airplaneInfoListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(AirplaneInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });
    }

    public void getFlight () {

        fillCombo();

        flightId.setCellValueFactory(new PropertyValueFactory<>("id"));
        airplaneName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAirplane().getName()));
        remainingSeat.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAirplane().getNumber_of_seats()));
        flightSrc.setCellValueFactory(new PropertyValueFactory<>("source"));
        flightDest.setCellValueFactory(new PropertyValueFactory<>("destination"));
        flightDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        flightTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));

        flightTable.setItems(new FlightRepository().flightAsObservableList());
    }

}
