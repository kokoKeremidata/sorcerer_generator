import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;

public class UI extends JFrame {

    // Input fields
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> gradeComboBox;
    private JProgressBar cursedEnergyBar;
    private JProgressBar speedBar;
    private JProgressBar strengthBar;
    private JProgressBar iqBar;
    private JProgressBar biqBar;
    private JLabel cursedEnergyLabel;
    private JLabel speedLabel;
    private JLabel strengthLabel;
    private JLabel iqLabel;
    private JLabel biqLabel;
    private JLabel domainLabel;
    private JComboBox<String> personalityComboBox;
    private JTextArea cursedTechniqueArea;
    private Sorcerer sorcerer = new Sorcerer("",0,"","",0,0,0,0,0,false,"");



    // Display area
    private JTextArea displayArea;
    private Random random;

    public UI() {
        setTitle("Jujutsu Sorcerer Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Theme.DARK_PURPLE);

        random = new Random();

        // Create main panels
        JPanel inputPanel = createInputPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel displayPanel = createDisplayPanel();

        // Add panels to frame
        add(inputPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(displayPanel, BorderLayout.CENTER);

        Theme.stylePanel(inputPanel);
        Theme.stylePanel(buttonPanel);
        Theme.stylePanel(displayPanel);

        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Sorcerer Attributes"));
        panel.setPreferredSize(new Dimension(350, 600));
        Theme.stylePanel(panel);

        // Name
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        sorcerer.setName(nameField.getText());
        Theme.styleTextField(nameField);
        // Age
        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);
        try{
            sorcerer.setAge(Integer.parseInt(ageField.getText()));
        }catch(Exception e){}
        Theme.styleTextField(ageField);

        // Grade
        panel.add(new JLabel("Grade:"));
        String[] grades = {"4", "3", "2", "1", "Special"};
        gradeComboBox = new JComboBox<>(grades);
        panel.add(gradeComboBox);
        sorcerer.setGrade(gradeComboBox.getSelectedItem().toString());
        Theme.styleComboBox(gradeComboBox);

        // Cursed Technique
        panel.add(new JLabel("Cursed Technique:"));
        JPanel techniquePanel = new JPanel(new BorderLayout());
        cursedTechniqueArea = new JTextArea(2, 10);
        JScrollPane scrollPane = new JScrollPane(cursedTechniqueArea);
        techniquePanel.add(scrollPane, BorderLayout.CENTER);
        panel.add(techniquePanel);
        Theme.styleTextArea(cursedTechniqueArea);
        Theme.styleScroll(scrollPane);

        // Random Technique Button (below the text field)
        panel.add(new JLabel("")); // Empty label for spacing
        JButton randomTechButton = new JButton("Random Technique");
        randomTechButton.addActionListener(e -> {

            String[] techniques = {"Limitless", "Ten Shadows", "Boogie Woogie", "Straw Doll", "Shrine", "Ratio", "Idle Transfiguration", "Tool Manipulation", "Construction","Rot","Puppet Manipulation","Cursed Spirit Manipulation", "Immortality","Cloning","Brain Swap","Auspicious Beasts Summon","Inverse","Crow Manipulation","Miracles","Wound Stop","Ice Formation","Love Rendezvous","Judgeman","Contractual Creation","Comedian","CE Discharge","Copy","Private Pure Love Train","Star Rage","Antigravity","Technique Extinguish","Mythical Beast Amber","Heart Catch","Prayer Song","Blood Manipulation","Cursed Speech","Projection Sorcery"};
            cursedTechniqueArea.setText(techniques[random.nextInt(techniques.length)]);
        });
        panel.add(randomTechButton);
        sorcerer.setCt(cursedTechniqueArea.getText());
        Theme.styleButton(randomTechButton);

        // Cursed Energy
        panel.add(new JLabel("Cursed Energy (0-100):"));
        cursedEnergyBar = createProgressBar(0, 100, 50);
        panel.add(cursedEnergyBar);
        Theme.styleBar(cursedEnergyBar);

        // Speed
        panel.add(new JLabel("Speed (0-100):"));
        speedBar = createProgressBar(0, 100, 50);
        panel.add(speedBar);
        Theme.styleBar(speedBar);

        // Strength
        panel.add(new JLabel("Strength (0-100):"));
        strengthBar = createProgressBar(0, 100, 50);
        panel.add(strengthBar);
        Theme.styleBar(strengthBar);

        // IQ (special handling for 530000)
        panel.add(new JLabel("IQ (75-150):"));
        iqBar = createProgressBar(75, 150, 100);
        panel.add(iqBar);
        Theme.styleBar(iqBar);

        // BIQ
        panel.add(new JLabel("Battle IQ (75-150):"));
        biqBar = createProgressBar(75, 150, 100);
        panel.add(biqBar);
        Theme.styleBar(biqBar);

        // Domain Expansion
        panel.add(new JLabel("Domain Expansion:"));
        JPanel domainPanel = new JPanel();
        domainLabel = new JLabel("No", SwingConstants.RIGHT);
        domainPanel.add(domainLabel);
        panel.add(domainPanel);
        Theme.stylePanel(domainPanel);


        // Personality
        panel.add(new JLabel("Personality:"));
        String[] personalities = {
                "Calm", "Aggressive", "Playful", "Serious",
                "Sadistic", "Nervous", "Confident", "Lazy"
        };
        personalityComboBox = new JComboBox<>(personalities);
        panel.add(personalityComboBox);
        sorcerer.setPersonality(personalityComboBox.getSelectedItem().toString());
        Theme.styleComboBox(personalityComboBox);

        // Randomize All Button (only randomizes stats, not grade/personality)
        panel.add(new JLabel(""));
        JButton randomizeStatsButton = new JButton("Randomize Stats");
        randomizeStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomizeStats();
            }
        });
        panel.add(randomizeStatsButton);
        Theme.styleButton(randomizeStatsButton);

        return panel;
    }
    private JProgressBar createProgressBar(int min, int max, int initial) {
        JProgressBar progressBar = new JProgressBar(min, max);
        progressBar.setValue(initial);
        progressBar.setStringPainted(true); // Show value inside bar
        progressBar.setEnabled(false); // Make non-interactive
        progressBar.setForeground(new Color(70, 130, 180)); // Nice blue color
        progressBar.setString(String.valueOf(initial));
        progressBar.setPreferredSize(new Dimension(200, 20));
        return progressBar;
    }

    private void randomizeStats() {
        // Cursed Energy (0-100)
        int ce = random.nextInt(101);
        cursedEnergyBar.setValue(ce);
        cursedEnergyBar.setString("" + ce);
        sorcerer.setCe(ce);
        // Speed (0-100)
        int speed = random.nextInt(101);
        speedBar.setValue(speed);
        speedBar.setString("" + speed);
        sorcerer.setSpeed(speed);
        // Strength (0-100)
        int strength = random.nextInt(101);
        strengthBar.setValue(strength);
        strengthBar.setString("" + strength);
        sorcerer.setStrength(strength);
        // IQ (75-150 with 1% chance for 530,000)
        int iq;
        if (random.nextInt(100) == 0) { // 1% chance
            iq = 530000;
        }else if(random.nextInt(100) <= 5){ //5% chance for over 150 iq
            iq = random.nextInt(150, 180);
        }else{
            iq = 75 + random.nextInt(76); // 75-150
        }
        iqBar.setValue(Math.min(iq, 150)); // Show max 150 on progress bar
        iqBar.setString("" + iq);
        sorcerer.setIq(iq);
        // BIQ (75-150)
        int biq = 75 + random.nextInt(76);
        biqBar.setValue(Math.min(biq, 150));
        biqBar.setString("" + biq);
        sorcerer.setBiq(biq);
        // Domain Expansion (20% chance for Yes (only for grade 1 and up))
        boolean de = false;
        if(random.nextFloat() < 0.1 && (gradeComboBox.getSelectedItem().equals("1") || gradeComboBox.getSelectedItem().equals("Special"))){
            domainLabel.setText("Yes");
            de=true;
        }else{
            domainLabel.setText("No");
        }
        sorcerer.setDe(de);
        //Heavenly restriction compensation (Strength)
        if(ce==0){
            speed = 1000;
            speedBar.setValue(speed);
            speedBar.setString("" + speed);
            sorcerer.setSpeed(speed);

            strength = 1000;
            strengthBar.setValue(strength);
            strengthBar.setString("" + strength);
            sorcerer.setStrength(strength);

            biq +=50;
            biqBar.setValue(biq);
            biqBar.setString("" + biq);
            sorcerer.setBiq(biq);
        }
        //Heavenly restriction compensation (Cursed energy)
        if(strength==0){
            speed = 1;
            speedBar.setValue(speed);
            speedBar.setString("" + speed);
            sorcerer.setSpeed(speed);

            ce = 199;
            cursedEnergyBar.setValue(strength);
            cursedEnergyBar.setString("" + strength);
            sorcerer.setCe(ce);
        }
    }
    // Get values from user input to put into the sorcerer
    private void updateSorcererFromFields() {
        sorcerer.setName(nameField.getText());
        try {
            sorcerer.setAge(Integer.parseInt(ageField.getText()));
        } catch (NumberFormatException e) {
            sorcerer.setAge(0);
        }
        sorcerer.setGrade(gradeComboBox.getSelectedItem().toString());
        sorcerer.setCt(cursedTechniqueArea.getText());
        sorcerer.setPersonality(personalityComboBox.getSelectedItem().toString());
    }
    private Sorcerer current;

    private JPanel createButtonPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton createButton = new JButton(" Create Sorcerer ");
        JButton saveButton = new JButton(" Save to File ");
        JButton loadButton = new JButton(" Load from File ");
        JButton clearButton = new JButton(" Clear ");

        panel.add(createButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(clearButton);

        Theme.styleButton(createButton);
        Theme.styleButton(saveButton);
        Theme.styleButton(loadButton);
        Theme.styleButton(clearButton);

        //create a sorcerer into the current field, ready to be saved or changed
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSorcererFromFields();
                current = sorcerer;
                displayCurrent();
            }
        });
        //save the current sorcerer to a file
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // First check if current exists
                Theme.styleDialog();
                if(current == null){
                    JOptionPane.showMessageDialog(null, "Please create a sorcerer first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Then check if name is empty
                if(current.getName()==null || current.getName().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please name the sorcerer first!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ArrayList<Sorcerer> sorcerers = new ArrayList<>();

                // Load existing sorcerers
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sorcerers.bin"))) {
                    sorcerers = (ArrayList<Sorcerer>) ois.readObject();
                } catch (FileNotFoundException ex) {
                    // file doesn't exist yet
                    JOptionPane.showMessageDialog(null,"File not found.","Error",JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Add the current sorcerer to the list
                sorcerers.add(current);

                // Save entire list
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sorcerers.bin"))) {
                    oos.writeObject(sorcerers);
                    JOptionPane.showMessageDialog(null, "Sorcerer saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        //list all sorcerers in the file, the user can choose one to display as the current sorcerer or edit their stats to get a new sorcerer
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Sorcerer> sorcerers = new ArrayList<>();

                // Load existing sorcerers if file exists
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sorcerers.bin"))) {
                    sorcerers = (ArrayList<Sorcerer>) ois.readObject();
                } catch (FileNotFoundException ex) {
                    // file doesn't exist yet, no problem
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create list of sorcerer names to display in a dialog window
                String[] names = sorcerers.stream()
                        .filter(Objects::nonNull)
                        .map(Sorcerer::getName)
                        .filter(Objects::nonNull)
                        .toArray(String[]::new);

                JList<String> list = new JList<>(names);
                list.setPreferredSize(new Dimension(400, 300));
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.setBackground(Theme.LAVENDER);
                Theme.styleDialog();
                JScrollPane scrollPane = new JScrollPane(list);
                Theme.styleScroll(scrollPane);

                int result = JOptionPane.showConfirmDialog(
                        null,scrollPane,
                        "Select Sorcerer",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                //selected sorcerer
                if (result == JOptionPane.OK_OPTION && list.getSelectedIndex() != -1) {
                    Sorcerer selected = sorcerers.get(list.getSelectedIndex());
                    current = selected;

                    displayCurrent();
                    nameField.setText(current.getName());
                    ageField.setText(Integer.toString(current.getAge()));
                    gradeComboBox.setSelectedItem(current.getGrade());
                    cursedTechniqueArea.setText(current.getCt());
                    cursedEnergyBar.setValue(current.getCe());
                    cursedEnergyBar.setString("" + current.getCe());
                    speedBar.setValue(current.getSpeed());
                    speedBar.setString("" + current.getSpeed());
                    strengthBar.setValue(current.getStrength());
                    strengthBar.setString("" + current.getStrength());
                    iqBar.setValue(current.getIq());
                    iqBar.setString("" + current.getIq());
                    biqBar.setValue(current.getBiq());
                    biqBar.setString("" + current.getBiq());
                    if(current.isDe())
                        domainLabel.setText("Yes");
                    else
                        domainLabel.setText("No");
                    personalityComboBox.setSelectedItem(current.getPersonality());
                    System.out.println("Selected sorcerer: " + current.getName());
                }
            }
        });
        //clears the current sorcerer
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                current = null;
            }
        });

        return panel;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Current Sorcerer"));

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        Theme.styleTextArea(displayArea);
        Theme.styleScroll(scrollPane);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
    //method to display current sorcerer in textfield
    public void displayCurrent(){
        displayArea.setText(
                current.getName()+
                        "\nAge: "+current.getAge()+
                        "\nGrade "+current.getGrade()+
                        "\nCT: "+current.getCt()+
                        "\nCE: "+current.getCe()+
                        "\nSpeed: "+current.getSpeed()+
                        "\nStrength: "+current.getStrength()+
                        "\nIQ: "+current.getIq()+
                        "\nBiq: "+current.getBiq()+
                        "\nDE: "+current.isDe()+
                        "\nPersonality: "+current.getPersonality());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UI());
    }
}