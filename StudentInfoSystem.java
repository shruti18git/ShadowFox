import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String id, name, course;
    double grade;

    public Student(String id, String name, String course, double grade) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.grade = grade;
    }
}

public class StudentInfoSystem extends JFrame {
    private JTextField txtId, txtName, txtCourse, txtGrade;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Student> students;

    public StudentInfoSystem() {
        setTitle("Student Information System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        students = new ArrayList<>();

        // Top Panel (Form Inputs)
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Student ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Course:"));
        txtCourse = new JTextField();
        panel.add(txtCourse);

        panel.add(new JLabel("Grade:"));
        txtGrade = new JTextField();
        panel.add(txtGrade);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        panel.add(btnAdd);
        panel.add(btnUpdate);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Course", "Grade"}, 0);
        table = new JTable(model);
        JScrollPane tableScroll = new JScrollPane(table);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnClear);

        // Add components to Frame
        add(panel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtId.setText(model.getValueAt(row, 0).toString());
                txtName.setText(model.getValueAt(row, 1).toString());
                txtCourse.setText(model.getValueAt(row, 2).toString());
                txtGrade.setText(model.getValueAt(row, 3).toString());
            }
        });

        setVisible(true);
    }

    private void addStudent() {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String course = txtCourse.getText().trim();
            double grade = Double.parseDouble(txtGrade.getText().trim());

            if (id.isEmpty() || name.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            students.add(new Student(id, name, course, grade));
            model.addRow(new Object[]{id, name, course, grade});
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Grade must be a number.");
        }
    }

    private void updateStudent() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            try {
                String id = txtId.getText().trim();
                String name = txtName.getText().trim();
                String course = txtCourse.getText().trim();
                double grade = Double.parseDouble(txtGrade.getText().trim());

                students.set(selected, new Student(id, name, course, grade));
                model.setValueAt(id, selected, 0);
                model.setValueAt(name, selected, 1);
                model.setValueAt(course, selected, 2);
                model.setValueAt(grade, selected, 3);
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Grade must be a number.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a student to update.");
        }
    }

    private void deleteStudent() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            students.remove(selected);
            model.removeRow(selected);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select a student to delete.");
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtGrade.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        new StudentInfoSystem();
    }
}
