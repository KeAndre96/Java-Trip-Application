import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONString;
import org.json.JSONWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONTokener;

public class SelectAgent {
    protected JPanel mainPanel;
    private JList list1;
    private JButton selectButton;
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
    private JList list2;
    private JList list3;
    private JList list4;
    private JButton addButton;
    private JButton continueButton1;
    private JList list5;
    private JButton payButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton payButton1;
    private JTextArea textArea1;
    private JButton addNoteButton;
    private JTextArea textArea2;
    private JButton doneButton;
    private JList list6;
    private JButton addButton1;
    private JButton continueButton2;
    private JButton saveButton;
    private String temp;
    private ArrayList<String> travelersTemp;

    public SelectAgent() {
        trip t = new trip();
        travelersTemp = new ArrayList<>();
        JSONObject jo = new JSONObject();
        //JSONObject jread = (JSONObject) r.readJSON("trips.json");
        temp= "";
        // List name of Agents in Trip
        DefaultListModel listModel1 = new DefaultListModel();
        List<Person> agents = AgentOptions.GetsOptions();
        for(int i = 0; i < agents.size(); i++){
            listModel1.addElement(agents.get(i).getName());
        }
        list1.setModel(listModel1);

        // List Travelers
        DefaultListModel listModel2 = new DefaultListModel();
        List<Traveler> travelers = TravelerOptions.GetsOptions();
        for(int i = 0; i < agents.size(); i++){
            listModel2.addElement(travelers.get(i).getName());
        }
        list2.setModel(listModel2);

        // List Packages
        DefaultListModel listModel4 = new DefaultListModel();
        List<Package> packages = PackageOptions.GetsOptions();
        for(int i = 0; i < packages.size(); i++){
            listModel4.addElement(packages.get(i).getTravelsTo());
        }
        list3.setModel(listModel4);

        // Go to page that shows the list of trips to edit or creating a trip
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
            }
        });
        createTripButton.addActionListener(new ActionListener() { //create a trip and go to "add travelers" screen
            @Override
            public void actionPerformed(ActionEvent e) {
                //create trip

                //switch to travelers card
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "AddTravelersCard");
            }
        });
        // Adding Travelers to a Trip List
        DefaultListModel listModel3 = new DefaultListModel();
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel3.addElement(list2.getSelectedValue());
                for(int i = 0; i < travelers.size(); i++){
                    if(travelers.get(i).getName().equalsIgnoreCase((String) list2.getSelectedValue())){
                        travelersTemp.add(travelers.get(i).getName());
                    }
                }
                for(String traveler: travelersTemp)
                {
                    jo.put("travelers", traveler);/////
                }
                //t.addedTravelers();
                list6.setModel(listModel3);
            }
        });
        continueButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "AddPackageCard");
                JSONArray ja = new JSONArray();
                ja.put(jo);
                t.addedTravelers(ja);
            }
        });
        DefaultListModel listModel5 = new DefaultListModel();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel5.addElement(list3.getSelectedValue());
                for(int i = 0; i < packages.size(); i++){
                    if(packages.get(i).getTravelsTo().equalsIgnoreCase((String) list3.getSelectedValue())){
                        t.addPackages(packages.get(i).getTravelsTo());
                    }
                }

                list4.setModel(listModel5);
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < agents.size(); i++){
                    if(agents.get(i).getName().equalsIgnoreCase((String) list1.getSelectedValue())){
                        temp = agents.get(i).getName();
                        t.addAgent(agents.get(i).getName());
                    }
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override //Save from Package Screen
            public void actionPerformed(ActionEvent actionEvent) {
                t.state = 3;
                t.state(3);
                t.writeTrip();
            }
        });
        editTripButton.addActionListener(new ActionListener() {
            @Override //Edit Trip Listener
            public void actionPerformed(ActionEvent actionEvent) {
                JSONReader r = new JSONReader("trips.json");
                try
                {
                   r.readJSON();
                }
                catch(Exception E)
                {
                    System.out.println("There was an error!");
                }

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
        placeHolder.add(new Package("Atlanta", "New Mexico", 1000, 10));
        placeHolder.add(new Package("Atlanta", "Guatemala", 2000, 11));
        placeHolder.add(new Package("Atlanta", "Great Britain", 3000, 12));
        placeHolder.add(new Package("Atlanta", "Africa", 4000, 13));
        placeHolder.add(new Package("Atlanta", "Moscow", 5000, 14));
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
