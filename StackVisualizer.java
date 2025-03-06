import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> q;

    // Constructor to initialize the queue
    public MyStack() {
        q = new LinkedList<>();
    }

    // Push element onto stack
    public void push(int x) {
        q.add(x);
        for (int i = 0; i < q.size() - 1; i++) {
            q.add(q.poll());
        }
    }

    // Pop the top element from the stack
    public int pop() {
        return q.poll();
    }

    // Get the top element of the stack
    public int top() {
        return q.peek();
    }

    // Check if the stack is empty
    public boolean empty() {
        return q.isEmpty();
    }

    // Get the size of the stack
    public int size() {
        return q.size();
    }

    // Method to get the current elements of the stack
    public java.util.List<Integer> getElements() {
        return new LinkedList<>(q);
    }
}

public class StackVisualizer extends JFrame {
    private MyStack stack;
    private JPanel stackPanel;
    private JTextField inputField;
    private JLabel statusLabel;

    public StackVisualizer() {
        stack = new MyStack();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Stack Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout(10, 10));

        // Stack visualization panel
        stackPanel = new JPanel();
        stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));
        stackPanel.setBackground(Color.WHITE);
        add(new JScrollPane(stackPanel), BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputField = new JTextField(5);
        JButton pushButton = new JButton("Push");
        JButton popButton = new JButton("Pop");
        JButton peekButton = new JButton("Peek");
        JButton sizeButton = new JButton("Size");
        JButton isEmptyButton = new JButton("Is Empty");
        JButton clearButton = new JButton("Clear");

        inputPanel.add(new JLabel("Enter Number:"));
        inputPanel.add(inputField);
        inputPanel.add(pushButton);
        inputPanel.add(popButton);
        inputPanel.add(peekButton);
        inputPanel.add(sizeButton);
        inputPanel.add(isEmptyButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);

        // Status label
        statusLabel = new JLabel("Stack Operations Status", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        add(statusLabel, BorderLayout.SOUTH);

        // Button Actions
        pushButton.addActionListener(e -> push());
        popButton.addActionListener(e -> pop());
        peekButton.addActionListener(e -> peek());
        sizeButton.addActionListener(e -> displaySize());
        isEmptyButton.addActionListener(e -> checkIfEmpty());
        clearButton.addActionListener(e -> clearStack());

        setVisible(true);
    }

    private void push() {
        try {
            int x = Integer.parseInt(inputField.getText());
            stack.push(x);
            animatePush(x);
            inputField.setText("");
            statusLabel.setText("Pushed: " + x);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pop() {
        if (stack.empty()) {
            JOptionPane.showMessageDialog(this, "Stack is empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int poppedValue = stack.pop();
        animatePop(poppedValue);
        statusLabel.setText("Popped: " + poppedValue);
    }

    private void peek() {
        if (stack.empty()) {
            JOptionPane.showMessageDialog(this, "Stack is empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int topValue = stack.top();
        statusLabel.setText("Top Element: " + topValue);
    }

    private void displaySize() {
        int size = stack.size();
        statusLabel.setText("Stack Size: " + size);
    }

    private void checkIfEmpty() {
        boolean isEmpty = stack.empty();
        statusLabel.setText("Stack is " + (isEmpty ? "empty" : "not empty"));
    }

    private void clearStack() {
        stack = new MyStack();
        updateStackDisplay();
        statusLabel.setText("Stack Cleared");
    }

    private void updateStackDisplay() {
        stackPanel.removeAll();
        java.util.List<Integer> elements = stack.getElements();
        for (int i = elements.size() - 1; i >= 0; i--) {
            JLabel label = createStackElementLabel(elements.get(i));
            stackPanel.add(label);
        }
        stackPanel.revalidate();
        stackPanel.repaint();
    }

    private JLabel createStackElementLabel(int value) {
        JLabel label = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.CYAN);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setMaximumSize(new Dimension(100, 30));
        return label;
    }

    private void animatePush(int value) {
        JLabel label = createStackElementLabel(value);
        stackPanel.add(label, 0);
        stackPanel.revalidate();
        stackPanel.repaint();
    }

    private void animatePop(int value) {
        if (stackPanel.getComponentCount() > 0) {
            Component topComponent = stackPanel.getComponent(0);
            stackPanel.remove(topComponent);
            stackPanel.revalidate();
            stackPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StackVisualizer::new);
    }
}
