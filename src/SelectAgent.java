import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;

public class SelectAgent {
    protected JPanel mainPanel;
    private JList agentList;
    private JButton selectAgentButton;
    private JPanel SelectAgentPanel;
    private JPanel ListTripsPanel;
    private JList tripsList;
    private JButton editTripButton;
    private JButton createTripButton;
    private JPanel AddTravelersPanel;
    private JPanel AddPackagesPanel;
    private JPanel ChoosePaymentPanel;
    private JPanel PayCreditPanel;
    private JPanel AddThankYouNotePanel;
    private JPanel ShowIteneraryPanel;
    private JList availableTravelerList;
    private JList availablePackageList;
    private JList assignedPackageList;
    private JButton addButton;
    private JButton addPackagesContinueButton;
    private JList paymentMethodList;
    private JButton payButton;
    private JTextField cardNumberField;
    private JTextField expDateField;
    private JTextField zipField;
    private JButton payButton1;
    private JTextArea thankYouTextArea;
    private JButton addNoteButton;
    private JTextArea iteneraryTextArea;
    private JButton doneButton;
    private JList assignedTravelerList;
    private JButton addTravelerButton;
    private JButton addTravelersContinueButton;
    private JButton saveButtonInTravelerState;
    private JButton saveButtonInPackageState;
    private JButton saveButtonInPaymentState;
    private JButton saveButtonInCardDetailsState;
    private JButton saveButtonInThankYouState;
    private String agentName;
    private ArrayList<String> travelersTemp;
    private Person currentAgent;
    private DefaultListModel assignedTravelersListModel = new DefaultListModel();


    public SelectAgent() {
        travelersTemp = new ArrayList<String>();
        Trip trip = new Trip();
        JSONArray jtravelers = new JSONArray();
        // List name of Agents in trip
        DefaultListModel listModel1 = new DefaultListModel();
        List<Person> agents = AgentOptions.GetsOptions();
        for (Person agent : agents) {
            listModel1.addElement(agent.getName());
        }
        agentList.setModel(listModel1);

        // List Travelers
        DefaultListModel listModel2 = new DefaultListModel();
        List<Traveler> travelers = TravelerOptions.GetsOptions();
        for(int i = 0; i < agents.size(); i++){
            listModel2.addElement(travelers.get(i));
        }
        availableTravelerList.setModel(listModel2);

        // List Packages
        DefaultListModel listModel4 = new DefaultListModel();
        List<Package> packages = PackageOptions.GetsOptions();
        for(int i = 0; i < packages.size(); i++){
            listModel4.addElement(packages.get(i).getTravelsTo());
        }
        availablePackageList.setModel(listModel4);

        // Go to page that shows the list of trips to edit or creating a trip
        selectAgentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Person agent : agents) {
                    if (agent.getName().equalsIgnoreCase((String) agentList.getSelectedValue())) {
                        agentName = agent.getName();
                        //trip.addAgent(agentName);
                        currentAgent = agent;

                    }
                }


                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        createTripButton.addActionListener(new ActionListener() { //create a trip and go to "add travelers" screen
            @Override
            public void actionPerformed(ActionEvent e) {
                //add selected agent from previous screen
                trip.addAgent(currentAgent);
                trip.setState(1); //set state to 1: add traveler screen

                //switch to travelers card
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "AddTravelersCard");
            }
        });
        // Adding Travelers to a trip List

        addTravelerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //move selected traveler from availableTravelers list to assignedTravlers list
                assignedTravelersListModel.addElement(availableTravelerList.getSelectedValue());


                assignedTravelerList.setModel(assignedTravelersListModel);
            }
        });
        addTravelersContinueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add assigned travelers to trip
                for(int i = 0; i < assignedTravelerList.getModel().getSize();i++){
                    trip.addTravelers((Person) assignedTravelerList.getModel().getElementAt(i));
                }

                trip.commitTravelers(); //saves added travelers
                trip.setState(2);
                trip.writeTrip();
                //go to package screen
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "AddPackageCard");


            }
        });
        DefaultListModel listModel5 = new DefaultListModel();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel5.addElement(availablePackageList.getSelectedValue());
                String temp;
                for (Package aPackage : packages) {
                    if (aPackage.getTravelsFrom().equalsIgnoreCase((String) availablePackageList.getSelectedValue())) {
                        temp = aPackage.getTravelsFrom();
                        trip.addPackages(temp);
                    }
                }

                assignedPackageList.setModel(listModel5);
            }
        });
        addPackagesContinueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.commitPackages();
                //Save setState
                trip.setState(3);
                trip.writeTrip();

                //go to payment type screen
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ChoosePaymentCard");
            }
        });
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(paymentMethodList.getSelectedIndex() == 1) {
                    //go to payment type screen
                    CardLayout cl = (CardLayout) mainPanel.getLayout();
                    cl.show(mainPanel, "PayCreditCard");
                } else {
                    trip.setState(4);
                    CardLayout cl = (CardLayout) mainPanel.getLayout();
                    cl.show(mainPanel, "ThankYouCard");
                }
            }
        });
        payButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //commit payment details
                //for(int i = 0; i < )


                //go to payment type screen
                trip.addPaymentDetails((String)cardNumberField.getText(),(String)expDateField.getText(),(String)zipField.getText());//////////////////////////////////////
                trip.setState(4);
                trip.writeTrip();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ThankYouCard");
            }
        });
        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add thank you note
                trip.addNote(thankYouTextArea.getText());


                // then go to itenerary
                trip.setState(5);
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "IteneraryCard");
            }
        });
        editTripButton.addActionListener(new ActionListener() {
            @Override //Edit trip Listener
            public void actionPerformed(ActionEvent actionEvent) {
                JSONReader r = new JSONReader("trips.json");
                try
                {
                    r.readJSON();
                    // Go back to add travelers
                    if(trip.getState() == 2){
                        CardLayout cl = (CardLayout) mainPanel.getLayout();
                        cl.show(mainPanel, "AddTravelersCard");
                    }
                    if(trip.getState() == 3){
                        CardLayout cl = (CardLayout) mainPanel.getLayout();
                        cl.show(mainPanel, "AddPackageCard");
                    }
                    if(trip.getState() == 4){
                        CardLayout cl = (CardLayout) mainPanel.getLayout();
                        cl.show(mainPanel, "ChoosePaymentCard");
                    }
                    if(trip.getState() == 5){
                        CardLayout cl = (CardLayout) mainPanel.getLayout();
                        cl.show(mainPanel, "PayCreditCard");
                    }
                    if(trip.getState() == 6){
                        CardLayout cl = (CardLayout) mainPanel.getLayout();
                        cl.show(mainPanel, "ThankYouCard");
                    }
                }
                catch(Exception E)
                {
                    System.out.println("There was an error!");
                }

            }
        });
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go to payment type screen
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
                JSONReader r = new JSONReader("trips.json");
                try{
                    r.readJSON();
                }
                catch(Exception E){
                    System.out.println("There was an error!");
                }
            }
        });
        // Save the state at picking travelers
        saveButtonInTravelerState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add assigned travelers to trip
                for(int i = 0; i<assignedTravelerList.getModel().getSize();i++){
                    trip.addTravelers((Person) assignedTravelerList.getModel().getElementAt(i));
                }

                trip.commitTravelers(); //saves added travelers
                trip.setState(2);
                trip.writeTrip();
                //go to package screen
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        // Save the state at picking the package
        saveButtonInPackageState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.commitPackages();
                //Save setState
                trip.setState(3);
                trip.writeTrip();

                //go to payment type screen
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        // Save the state at picking which payment you want
        saveButtonInPaymentState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.setState(4);
                trip.writeTrip();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });///////////////////
        // Save the state at put in card details
        saveButtonInCardDetailsState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.setState(5);
                trip.writeTrip();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        // Save state at thank you note
        saveButtonInThankYouState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.setState(6);
                trip.writeTrip();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripCard");
            }
        });
        editTripButton.addActionListener(new ActionListener() {
            @Override //read JSON file
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        paymentMethodList.addComponentListener(new ComponentAdapter() {
            @Override //Gte the payment method value
            public void componentResized(ComponentEvent e) {
                for(int i = 0; i < paymentMethodList.getModel().getSize(); i++) {
                    //trip.addTravelers((Person) assignedTravelerList.getModel().getElementAt(i));
                    //String textValue = paymentMethodList.getSelectedValue().toString();
                    //trip.addPaymentMethod((String)paymentMethodList.getModel().getElementAt(i));
                    //trip.writeTrip();
                }

                super.componentResized(e);
            }
        });
        //Save the state at picking travelers
        saveButtonInTravelerState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JSONReader r = new JSONReader("trips.json");
                try
                {
                    r.readJSON();
                    //Object temp = r.getJSON();
                    DefaultListModel listModel = new DefaultListModel();
                    tripsList.setModel(listModel);
                }
                catch(Exception E)
                {
                    System.out.println("There was an error!");
                }
                //add assigned travelers to trip
                for(int i = 0; i<assignedTravelerList.getModel().getSize();i++){
                    trip.addTravelers((Person) assignedTravelerList.getModel().getElementAt(i));
                }

                trip.commitTravelers(); //saves added travelers
                trip.setState(2);
                trip.writeTrip();
                //go to package screen
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        // Save the state at picking the package
        saveButtonInPackageState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.commitPackages();
                //Save setState
                trip.setState(3);
                trip.writeTrip();

                //go to payment type screen
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        // Save the state at picking which payment you want
        saveButtonInPaymentState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.setState(4);
                trip.writeTrip();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        // Save the state at put in card details
        saveButtonInCardDetailsState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.setState(5);
                trip.writeTrip();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });

// Save state at thank you note
        saveButtonInThankYouState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip.setState(6);
                trip.writeTrip();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                trip.addNote((String)thankYouTextArea.getText());
                trip.writeTrip();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
final class AgentOptions{
    private static final Object syncLock = new Object();
    private static volatile AgentOptions _singletonAgents;

    private java.util.List<TravelAgent> OptionsList;

    private AgentOptions(){
        ArrayList<TravelAgent> placeHolder = new ArrayList<TravelAgent>();
        placeHolder.add(new TravelAgent("Agent 47", "4041234567", "agentk@gmail.com", 123568));
        placeHolder.add(new TravelAgent("Agent 007", "4041635537", "agentj@gmail.com", 123168));
        placeHolder.add(new TravelAgent("Agent Radcliff", "4041234567", "agentm@gmail.com", 123547));
        placeHolder.add(new TravelAgent("Agent Barry", "4041234867", "agentn@gmail.com", 123588));
        placeHolder.add(new TravelAgent("Agent O", "4041234167", "agento@gmail.com", 123538));
        OptionsList = Collections.unmodifiableList(placeHolder);
    }
    public static List GetsOptions(){
        if(_singletonAgents == null){
            synchronized (syncLock){
                if(_singletonAgents == null){
                    _singletonAgents = new AgentOptions();
                }
            }
        }
        return _singletonAgents.OptionsList;
    }
}
final class TravelerOptions{
    private static final Object syncLock = new Object();
    private static volatile TravelerOptions _singletonTraveler;

    private List<Traveler> OptionsList;

    private TravelerOptions(){
        ArrayList<Traveler> placeHolder = new ArrayList<>();
        placeHolder.add(new Traveler("Adam Smith", "4041234567", "adamsmith@gmail.com",
                "6455 Old Avenue"));
        placeHolder.add(new Traveler("John Smith", "4041234567", "adamsmith@gmail.com",
                "6455 Old Avenue"));
        placeHolder.add(new Traveler("Robb Smith", "4041234567", "adamsmith@gmail.com",
                "6455 Old Avenue"));
        placeHolder.add(new Traveler("Jake Smith", "4041234567", "adamsmith@gmail.com",
                "6455 Old Avenue"));
        placeHolder.add(new Traveler("Lucy Smith", "4041234567", "adamsmith@gmail.com",
                "6455 Old Avenue"));
        OptionsList = Collections.unmodifiableList(placeHolder);
    }
    public static List GetsOptions(){
        if(_singletonTraveler == null){
            synchronized (syncLock){
                if(_singletonTraveler == null){
                    _singletonTraveler = new TravelerOptions();
                }
            }
        }
        return _singletonTraveler.OptionsList;
    }

}
final class PackageOptions{
    private static final Object syncLock = new Object();
    private static volatile PackageOptions _singletonPackage;
    private List<Package> OptionsList;

    private PackageOptions(){
        ArrayList<Package> placeHolder = new ArrayList();
        placeHolder.add(new Package("Atlanta", "New Mexico", 1000, 10, "Private Helicopter"));
        placeHolder.add(new Package("Atlanta", "Guatemala", 2000, 11, "Helicopter"));
        placeHolder.add(new Package("Atlanta", "Great Britain", 3000, 12, "Tank"));
        placeHolder.add(new Package("Atlanta", "Africa", 4000, 13, "Van"));
        placeHolder.add(new Package("Atlanta", "Moscow", 5000, 14, "Cruise Ship"));
        OptionsList = Collections.unmodifiableList(placeHolder);
    }
    public static List GetsOptions(){
        if(_singletonPackage == null){
            synchronized (syncLock){
                if(_singletonPackage == null){
                    _singletonPackage = new PackageOptions();
                }
            }
        }
        return _singletonPackage.OptionsList;
    }
}
// Transportation Types
enum Transport {
    PrivateHelicopter,
    Helicopter,
    Tank,
    Van
}