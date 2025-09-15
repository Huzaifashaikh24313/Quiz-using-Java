import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {
    String[] questions = {
            "1. Who is known as the Father of the Nation in India?",
            "2. What is the capital city of India?",
            "3. Who was the first Prime Minister of India?",
            "4. Which is the national animal of India?",
            "5. In which year did India get Independence?"

    };

    String[][] options = {
            {"Mahatma Gandhi", "Jawaharlal Nehru", "Subhash Chandra Bose", "Bhagat Singh"},
            {"Mumbai", "New Delhi", "Kolkata", "Chennai"},
            {"Mahatma Gandhi", "Sardar Patel", "Jawaharlal Nehru", "Dr. B. R. Ambedkar"},
            {"Lion", "Tiger", "Elephant", "Peacock"},
            {"1942", "1947", "1950", "1962"}
    };

    String[] answers = {
            "Mahatma Gandhi",
            "New Delhi",
            "Jawaharlal Nehru",
            "Tiger",
            "1947"
    };

    int index = 0, correct = 0;
    JFrame frame;
    JLabel questionLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup bg;
    JButton nextButton;

    public QuizApp() {
        frame = new JFrame("Simple Quiz App  -by Huzaifa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        // Question
        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(questionLabel, BorderLayout.NORTH);

        // Options
        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        bg = new ButtonGroup();
        bg.add(opt1);
        bg.add(opt2);
        bg.add(opt3);
        bg.add(opt4);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionsPanel.add(opt1);
        optionsPanel.add(opt2);
        optionsPanel.add(opt3);
        optionsPanel.add(opt4);
        frame.add(optionsPanel, BorderLayout.CENTER);

        // Button
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        frame.add(nextButton, BorderLayout.SOUTH);

        loadQuestion();
        frame.setVisible(true);
    }

    public void loadQuestion() {
        if (index < questions.length) {
            questionLabel.setText(questions[index]);
            opt1.setText(options[index][0]);
            opt2.setText(options[index][1]);
            opt3.setText(options[index][2]);
            opt4.setText(options[index][3]);
            bg.clearSelection();
            resetOptionColors();

            // Change button text: "Next" or "Submit"
            if (index == questions.length - 1) {
                nextButton.setText("Submit");
            } else {
                nextButton.setText("Next");
            }
        } else {
            showResult();
        }
    }

    public void actionPerformed(ActionEvent e) {
        JRadioButton selectedOption = null;
        if (opt1.isSelected()) selectedOption = opt1;
        if (opt2.isSelected()) selectedOption = opt2;
        if (opt3.isSelected()) selectedOption = opt3;
        if (opt4.isSelected()) selectedOption = opt4;

        if (selectedOption != null) {
            // Check answer
            if (selectedOption.getText().equals(answers[index])) {
                selectedOption.setBackground(Color.GREEN);
                correct++;
            } else {
                selectedOption.setBackground(Color.RED);
                // Highlight correct one
                if (opt1.getText().equals(answers[index])) opt1.setBackground(Color.GREEN);
                if (opt2.getText().equals(answers[index])) opt2.setBackground(Color.GREEN);
                if (opt3.getText().equals(answers[index])) opt3.setBackground(Color.GREEN);
                if (opt4.getText().equals(answers[index])) opt4.setBackground(Color.GREEN);
            }

            // Disable button temporarily
            nextButton.setEnabled(false);

            // Move to next question after 0.3 seconds
            Timer timer = new Timer(300, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    index++;
                    loadQuestion();
                    nextButton.setEnabled(true);
                }
            });
            timer.setRepeats(false);
            timer.start();

        } else {
            JOptionPane.showMessageDialog(frame, "Please select an option!");
        }
    }

    private void resetOptionColors() {
        opt1.setBackground(null);
        opt2.setBackground(null);
        opt3.setBackground(null);
        opt4.setBackground(null);
    }

    public void showResult() {
        JOptionPane.showMessageDialog(frame, "Quiz Finished!\nYour Score: " + correct + "/" + questions.length);
        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}
