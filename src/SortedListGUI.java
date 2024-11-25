import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SortedListGUI extends JFrame {
    private SortedList sortedList;
    private JTextField inputField;
    private JTextArea outputArea;
    private JTextField searchField;

    public SortedListGUI() {
        sortedList = new SortedList();
        initComponents();
    }

    private void initComponents() {
        setTitle("Sorted List Manager");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(20);
        JButton addButton = new JButton("Add Element");
        addButton.addActionListener(e -> addElement());
        inputPanel.add(new JLabel("Enter Element:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchElement());
        searchPanel.add(new JLabel("Search Element:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Output Area
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
    }

    private void addElement() {
        String element = inputField.getText().trim();
        if (!element.isEmpty()) {
            sortedList.add(element);
            outputArea.append("Added: " + element + "\n");
            outputArea.append("Current List:\n" + sortedList.toString() + "\n");
            inputField.setText("");
        }
    }

    private void searchElement() {
        String searchTerm = searchField.getText().trim();
        if (!searchTerm.isEmpty()) {
            int result = sortedList.binarySearch(searchTerm);

            if (result >= 0) {
                outputArea.append("Search Result: Found '" + searchTerm +
                        "' at index " + result + "\n");
            } else {
                int insertIndex = -(result + 1);
                outputArea.append("Search Result: '" + searchTerm +
                        "' not found. Would be inserted at index " +
                        insertIndex + "\n");
            }
            searchField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SortedListGUI().setVisible(true);
        });
    }
}