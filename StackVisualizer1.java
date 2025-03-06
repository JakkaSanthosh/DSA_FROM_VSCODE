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

public class StackVisualizer1 {
    private MyStack stack;
    private DefaultListModel<String> listModel;
    private JList<String> stackList;
    private JTextField inputField;
    private JLabel statusLabel;

    public StackVisualizer1() {
        stack = new MyStack();
        initializeUI();
    }

    private void initializeUI() {
        // Set Nimbus look and feel for a modern appearance
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Stack Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // Stack visualization
        listModel = new DefaultListModel<>();
        stackList = new JList<>(listModel);
        stackList.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(new JScrollPane(stackList), BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputField = new JTextField(5);
        JButton pushButton = new JButton("Push");
        JButton popButton = new JButton("Pop");
        JButton peekButton = new JButton("Peek");
        JButton sizeButton = new JButton("Size");
        JButton clearButton = new JButton("Clear");

        inputPanel.add(new JLabel("Enter Number:"));
        inputPanel.add(inputField);
        inputPanel.add(pushButton);
        inputPanel.add(popButton);
        inputPanel.add(peekButton);
        inputPanel.add(sizeButton);
        inputPanel.add(clearButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Status label
        statusLabel = new JLabel("Stack Operations Status", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        frame.add(statusLabel, BorderLayout.SOUTH);

        // Button Actions
        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                push();
            }
        });

        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pop();
            }
        });

        peekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                peek();
            }
        });

        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySize();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearStack();
            }
        });

        frame.setVisible(true);
    }

    private void push() {
        try {
            int x = Integer.parseInt(inputField.getText());
            stack.push(x);
            updateStackDisplay();
            inputField.setText("");
            statusLabel.setText("Pushed: " + x);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pop() {
        if (stack.empty()) {
            JOptionPane.showMessageDialog(null, "Stack is empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int poppedValue = stack.pop();
        updateStackDisplay();
        statusLabel.setText("Popped: " + poppedValue);
    }

    private void peek() {
        if (stack.empty()) {
            JOptionPane.showMessageDialog(null, "Stack is empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int topValue = stack.top();
        statusLabel.setText("Top Element: " + topValue);
    }

    private void displaySize() {
        int size = stack.size();
        statusLabel.setText("Stack Size: " + size);
    }

    private void clearStack() {
        while (!stack.empty()) {
            stack.pop();
        }
        updateStackDisplay();
        statusLabel.setText("Stack Cleared");
    }

    private void updateStackDisplay() {
        listModel.clear();
        java.util.List<Integer> elements = stack.getElements();
        for (int i = elements.size() - 1; i >= 0; i--) {
            listModel.addElement("| " + elements.get(i) + " |");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StackVisualizer1();
            }
        });
    }
}
