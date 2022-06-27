package main.airapp;

import database.repository.FlightRepository;
import database.repository.TicketRepository;
import datamodel.FlightInfo;
import datamodel.TicketInfo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class BookingListPageController extends Controller{

    @FXML
    private ListView<TicketInfo> bookedSeatList;
    @FXML
    private ListView<FlightInfo> flightList;

    public void getBookedFlights(){

        ObservableList<FlightInfo> flightFetched = new FlightRepository().filterFlightAsObservableList(LocalDate.now(), LocalDate.MAX, null, null, null);

        TicketRepository ticketRepository = new TicketRepository();
        HashMap<Integer, ObservableList<TicketInfo>> ticketFetched = new HashMap<>();
        for (FlightInfo i : flightFetched) {
            ticketFetched.put(i.getId(), ticketRepository.ticketAsObservableList(i.getId()));
        }

        flightList.setItems(flightFetched);


        flightList.setOnMouseClicked(mouseEvent -> {
            bookedSeatList.setItems(ticketFetched.get(flightList.getSelectionModel().getSelectedItem().getId()));
        });

        flightList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<FlightInfo> call(ListView<FlightInfo> flightInfoListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(FlightInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getFlightName());
                        }
                    }
                };
            }
        });
        bookedSeatList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<TicketInfo> call(ListView<TicketInfo> flightInfoListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(TicketInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getPassengerDetails());
                        }
                    }
                };
            }
        });
    }
}
