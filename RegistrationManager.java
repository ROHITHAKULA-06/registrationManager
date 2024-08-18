/**
 *
 * @author rohit
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class RegistrationManager extends Frame implements ActionListener {
    TextField nameField, emailField, phoneField;
    CheckboxGroup genderGroup;
    Checkbox male, female;
    Choice countryChoice;
    TextArea displayArea;
    ArrayList<String> userData;

    public RegistrationManager() {
        super("AWT Registration Manager");
        userData = new ArrayList<>();
        setLayout(new FlowLayout());
        add(new Label("Name:"));
        nameField = new TextField(20);
        add(nameField);
        add(new Label("Email:"));
        emailField = new TextField(20);
        add(emailField);
        add(new Label("Phone:"));
        phoneField = new TextField(15);
        add(phoneField);
        add(new Label("Gender:"));
        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, true);
        female = new Checkbox("Female", genderGroup, false);
        add(male);
        add(female);
        add(new Label("Country:"));
        countryChoice = new Choice();
        countryChoice.add("India");
        countryChoice.add("USA");
        countryChoice.add("UK");
        countryChoice.add("Canada");
        add(countryChoice);

        Button submitButton = new Button("Submit");
        submitButton.addActionListener(this);
        add(submitButton);
        Button viewButton = new Button("View Details");
        viewButton.addActionListener(this);
        add(viewButton);

        Button exportButton = new Button("Export");
        exportButton.addActionListener(this);
        add(exportButton);

        displayArea = new TextArea(10, 40);
        add(displayArea);

        setSize(650, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Submit")) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String gender = genderGroup.getSelectedCheckbox().getLabel();
            String country = countryChoice.getSelectedItem();

            String userDetails = name + "," + email + "," + phone + "," + gender + "," + country;
            userData.add(userDetails);
            displayArea.setText("User Registered: " + name);

            nameField.setText("");
            emailField.setText("");
            phoneField.setText("");
            genderGroup.setSelectedCheckbox(male);
            countryChoice.select(0);

        } else if (command.equals("View Details")) {
            displayArea.setText("");
            for (String user : userData) {
                displayArea.append(user + "\n");
            }

        } else if (command.equals("Export")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_data.txt"))) {
                for (String user : userData) {
                    writer.write(user);
                    writer.newLine();
                }
                displayArea.setText("Data exported to user_data.txt");
            } catch (IOException ex) {
                displayArea.setText("Error exporting data.");
            }
        }
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}