import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataStructureVisualizer extends JFrame {
    private MyStack stack;
    private MyQueue queue;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTextField inputField;
    private JLabel statusLabel;
    private boolean isStackMode;
    private JPanel stackPanel;
    private JPanel queuePanel;

    public DataStructureVisualizer() {
        stack = new MyStack();
        queue = new MyQueue();
        isStackMode = true; // Default to stack mode
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Data Structure Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout(10, 10));

        // Card layout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Stack panel
        stackPanel = new JPanel();
        stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));
        stackPanel.setBackground(Color.WHITE);
        cardPanel.add(new JScrollPane(stackPanel), "Stack");

        // Queue panel
        queuePanel = new JPanel();
        queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.Y_AXIS));
        queuePanel.setBackground(Color.WHITE);
        cardPanel.add(new JScrollPane(queuePanel), "Queue");

        add(cardPanel, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputField = new JTextField(5);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton peekButton = new JButton("Peek");
        JButton sizeButton = new JButton("Size");
        JButton isEmptyButton = new JButton("Is Empty");
        JButton clearButton = new JButton("Clear");
        JToggleButton toggleButton = new JToggleButton("Switch to Queue");

        inputPanel.add(new JLabel("Enter Number:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(peekButton);
        inputPanel.add(sizeButton);
        inputPanel.add(isEmptyButton);
        inputPanel.add(clearButton);
        inputPanel.add(toggleButton);

        add(inputPanel, BorderLayout.NORTH);

        // Status label
        statusLabel = new JLabel("Stack Mode", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        add(statusLabel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addElement());
        removeButton.addActionListener(e -> removeElement());
        peekButton.addActionListener(e -> peekElement());
        sizeButton.addActionListener(e -> displaySize());
        isEmptyButton.addActionListener(e -> checkIfEmpty());
        clearButton.addActionListener(e -> clearDataStructure());
        toggleButton.addActionListener(e -> toggleDataStructure(toggleButton));

        setVisible(true);
    }

    private void addElement() {
        try {
            int x = Integer.parseInt(inputField.getText());
            if (isStackMode) {
                stack.push(x);
                updateStackDisplay();
                statusLabel.setText("Pushed: " + x);
            } else {
                queue.enqueue(x);
                updateQueueDisplay();
                statusLabel.setText("Enqueued: " + x);
            }
            inputField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeElement() {
        try {
            if (isStackMode) {
                int poppedValue = stack.pop();
                updateStackDisplay();
                statusLabel.setText("Popped: " + poppedValue);
            } else {
                int dequeuedValue = queue.dequeue();
                updateQueueDisplay();
                statusLabel.setText("Dequeued: " + dequeuedValue);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE
::contentReference[oaicite:0]{index=0}
 
