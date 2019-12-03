import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private JTextField textField1;
    private JButton Add;
    private JList list2;
    private JButton continueButton;
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

    public SelectAgent() {
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "ListTripsCard");
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
        placeHolder.add(new TravelAgent("AgentK", "4041234567", "agentk@gmail.com", 123568));
        placeHolder.add(new TravelAgent("AgentJ", "4041635537", "agentj@gmail.com", 123168));
        placeHolder.add(new TravelAgent("AgentM", "4041234567", "agentm@gmail.com", 123547));
        placeHolder.add(new TravelAgent("AgentN", "4041234867", "agentn@gmail.com", 123588));
        placeHolder.add(new TravelAgent("AgentO", "4041234167", "agento@gmail.com", 123538));
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
