package main.airapp;

import database.repository.AirplaneRepository;
import datamodel.AirplaneInfo;
import datamodel.FlightInfo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FlightsPageFormController extends Controller {

    @FXML
    private ComboBox<AirplaneInfo> airplanesDropDown;

    @FXML
    private Button button;

    @FXML
    private DatePicker departureDate;

    @FXML
    private TextField departureTime;

    @FXML
    private TextField destination;

    @FXML
    private TextField source;

    @FXML
    private Label errorlabel;

    @FXML
    public void initialize(){
        fillCombo();
    }

    public void makeFlight() {


        String FlightSource = source.getText();

        String FlightDestination = destination.getText();

        LocalDate FlightDate = departureDate.getValue();

        String temp = departureTime.getText();
        if( !temp.isEmpty() ) {
            LocalTime FlightTime;
            String[] arr = temp.split(":");
            FlightTime = LocalTime.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        }

        if (airplanesDropDown.getSelectionModel().isEmpty()) {
            errorlabel.setText("Select Airplane");
        }
        else if (FlightSource.isEmpty()) {
            errorlabel.setText("Enter Flight Source");
        } else if (FlightDestination.isEmpty()) {
            errorlabel.setText("Enter Flight Destination");
        } else if (FlightDate == null ) {
            errorlabel.setText("Enter Flight Date");
        }  else if (temp.isEmpty()) {
            errorlabel.setText("Enter Flight Time");
        }else {
            errorlabel.setTextFill(Color.GREEN);
            errorlabel.setText("Flight Added Successfully!!");
            //     FlightInfo newFlight = new FlightInfo(FlightSource,FlightDestination,FlightDate,FlightTime);
            // TODO : assign id and make object
        }

    }

    private void fillCombo() {
        airplanesDropDown.setItems(new AirplaneRepository().airplaneAsObservable());

        // sets the thing to show on teh display box
        airplanesDropDown.setConverter(new StringConverter<>() {
            @Override
            public String toString(AirplaneInfo airplaneInfo) {
                if (airplaneInfo == null) return "<Airplane Filter>";
                return airplaneInfo.getName();
            }

            @Override
            public AirplaneInfo fromString(String s) {
                return null;
            }
        });

        // sets the things to show on the list
        airplanesDropDown.setCellFactory(new Callback<>() {
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



}
