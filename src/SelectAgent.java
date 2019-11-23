import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
