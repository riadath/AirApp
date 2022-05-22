package main.airapp;

import datamodel.FlightInfo;
import datamodel.TicketInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BookingPageController extends Controller{

    @FXML
    private ListView<String> bookedSeatList;
    @FXML
    private ListView<Integer> flightList;

    public void getBookedFlights(){
        //TODO : add the flight names to flightList and all the tickets to the list
        ArrayList<FlightInfo> flightInfoList = new ArrayList<>(); //add the names here or sth idk
        ArrayList<TicketInfo> allTickets = new ArrayList<>(); //get all the tickets
        ObservableList<Integer>flightIDList = FXCollections.observableArrayList();
        ObservableList<String>bookedTicketList = FXCollections.observableArrayList();

        //TODO: delete later
        FlightInfo f1 = new FlightInfo(9,"Zerkon",null,7,null,
                null,null,null);
        FlightInfo f2 = new FlightInfo(89,"Yeonna",null,67,null,
                null,null,null);
        flightInfoList.add(f1);
        flightInfoList.add(f2);

        TicketInfo t1 = new TicketInfo("Druid","u",null,null,f1,1212);
        TicketInfo t2 = new TicketInfo("Ezekiel","v",null,null,f2,8892);
        TicketInfo t3 = new TicketInfo("Hll","w",null,null,f2,54);

        allTickets.add(t1);
        allTickets.add(t2);
        allTickets.add(t3);

        //delete later

        for(FlightInfo t_flight : flightInfoList){
            flightIDList.add(t_flight.getId());
        }
        flightList.setItems(flightIDList);
        
        flightList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bookedTicketList.clear();
                Integer flightId = flightList.getSelectionModel().getSelectedItem();
                for(TicketInfo ticket : allTickets){
                    if(ticket.getFlightInfo().getId() == flightId){
                        bookedTicketList.add(ticket.getName() + " " + ticket.getSeatNumber());
                    }
                }
                bookedSeatList.setItems(bookedTicketList);
            }
        });
    }

    public void switchToBookingPageForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("booking-page-form.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
