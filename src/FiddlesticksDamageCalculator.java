import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiddlesticksDamageCalculator extends JFrame {
    private JTextField apTextField;
    private JComboBox<String> levelComboBox;
    private JButton calculateButton;
    private JLabel resultLabel;

    private JTextField mrTextField;

    public FiddlesticksDamageCalculator() {
        setTitle("Fiddlesticks Damage Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        createUI();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDamage();
            }
        });
    }

    private void createUI() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        apTextField = new JTextField(10);
        levelComboBox = new JComboBox<>(new String[]{"1", "2", "3"});
        calculateButton = new JButton("Calculate Damage");
        resultLabel = new JLabel("Result: ");
        mrTextField = new JTextField(10);

        panel.add(new JLabel("Ability Power: "));
        panel.add(apTextField);
        panel.add(new JLabel("Ultimate Level: "));
        panel.add(levelComboBox);
        panel.add(calculateButton);
        panel.add(resultLabel);
        panel.add(new JLabel("Magic Resistance: "));
        panel.add(mrTextField);
        panel.add(new JLabel()); // Empty LAbel for spacing

        panel.add(calculateButton);
        panel.add(resultLabel);

        add(panel, BorderLayout.CENTER);


        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(resultLabel);
        panel.add(verticalBox);

        add(panel);

    }

    private void calculateDamage() {
        try {
            double abilityPower = Double.parseDouble(apTextField.getText()); // Added for ability power
            int selectedLevel = Integer.parseInt((String) levelComboBox.getSelectedItem()); // Added for Ultimate level
            double magicResistance = Double.parseDouble(mrTextField.getText());  // Added for magic resistance

            // Calculate damage based on the selected level
            double calculatedDamage = calculateDamageFormula(abilityPower, selectedLevel, magicResistance);

            long roundedDamage = Math.round(calculatedDamage);

            resultLabel.setText("Result: " + roundedDamage);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric values.");
        }
    }

    private double calculateDamageFormula(double abilityPower, int level, double magicResist) {
        switch (level) {
            case 1:
                return ((37.5 + (abilityPower * 0.125)) * 4 * 5) * (100.0 / (100.0 + magicResist));
            case 2:
                return ((62.5 + (abilityPower * 0.125)) * 4 * 5) * (100.0 / (100.0 + magicResist));
            case 3:
                return ((87.5 + (abilityPower * 0.125)) * 4 * 5) * (100.0 / (100.0 + magicResist));
            default:
                throw new IllegalArgumentException("Invalid level selected");
        }
    }
}
