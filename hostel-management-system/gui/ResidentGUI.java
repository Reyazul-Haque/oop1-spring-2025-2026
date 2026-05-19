package gui;

import entity.Resident;
import fileio.ResidentFileIO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class ResidentGUI extends JFrame {

    private JTextField idField, nameField, ageField, roomField, searchField;
    private JTable table;
    private DefaultTableModel model;

    public ResidentGUI() {

        setTitle("Hostel Management System");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));

        form.setBorder(
                BorderFactory.createTitledBorder("Resident Information"));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        roomField = new JTextField();

        form.add(new JLabel("Resident ID (8 digits)"));
        form.add(idField);

        form.add(new JLabel("Resident Name"));
        form.add(nameField);

        form.add(new JLabel("Age"));
        form.add(ageField);

        form.add(new JLabel("Room Number"));
        form.add(roomField);

        JPanel top = new JPanel(new BorderLayout());

        searchField = new JTextField();

        JButton searchBtn = new JButton("Search");

        JPanel searchPanel = new JPanel(new BorderLayout());

        JPanel searchBox = new JPanel(new BorderLayout());

        searchBox.add(searchField, BorderLayout.CENTER);
        searchBox.add(searchBtn, BorderLayout.WEST);

        searchPanel.add(searchBox, BorderLayout.CENTER);

        top.add(form, BorderLayout.CENTER);
        top.add(searchPanel, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[] { "ID", "Name", "Age", "Room Number" }, 0) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        JButton viewBtn = new JButton("View All");
        JButton clearBtn = new JButton("Clear");

        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(viewBtn);
        buttons.add(clearBtn);

        add(buttons, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addResident());

        updateBtn.addActionListener(e -> updateResident());

        deleteBtn.addActionListener(e -> deleteResident());

        viewBtn.addActionListener(e -> viewAll());

        searchBtn.addActionListener(e -> search());

        clearBtn.addActionListener(e -> clear());

        table.getSelectionModel().addListSelectionListener(
                e -> loadSelected());

        try {

            ResidentFileIO.createFileIfNotExists();

        } catch (Exception ignored) {
        }

        viewAll();
    }

    private boolean validateAll(boolean checkDuplicate) {

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String room = roomField.getText().trim();

        if (id.isEmpty()
                || name.isEmpty()
                || age.isEmpty()
                || room.isEmpty()) {

            showError("All fields are required!");
            return false;
        }

        if (!id.matches("\\d{8}")) {

            showError("ID must be exactly 8 digits!");
            return false;
        }

        try {

            Integer.parseInt(age);

        } catch (Exception e) {

            showError("Age must be a number!");
            return false;
        }

        if (name.contains(",")
                || room.contains(",")) {

            showError("Comma not allowed!");
            return false;
        }

        if (checkDuplicate &&
                ResidentFileIO.idExists(id)) {

            showError("Duplicate ID!");
            return false;
        }

        return true;
    }

    private void addResident() {

        try {

            if (!validateAll(true))
                return;

            ResidentFileIO.addResident(
                    new Resident(
                            idField.getText(),
                            nameField.getText(),
                            ageField.getText(),
                            roomField.getText()));

            showInfo("Resident Added Successfully");

            clear();

            viewAll();

        } catch (IOException e) {

            showError(e.getMessage());
        }
    }

    private void updateResident() {

        try {

            if (!validateAll(false))
                return;

            ResidentFileIO.updateResident(
                    new Resident(
                            idField.getText(),
                            nameField.getText(),
                            ageField.getText(),
                            roomField.getText()));

            showInfo("Resident Updated");

            viewAll();

        } catch (IOException e) {

            showError(e.getMessage());
        }
    }

    private void deleteResident() {

        try {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Delete Resident?");

            if (confirm != JOptionPane.YES_OPTION)
                return;

            ResidentFileIO.deleteResident(
                    idField.getText());

            showInfo("Resident Deleted");

            viewAll();

        } catch (IOException e) {

            showError(e.getMessage());
        }
    }

    private void viewAll() {

        refresh(ResidentFileIO.getAllResident());
    }

    private void search() {

        String key = searchField.getText();

        if (key.isEmpty()) {

            showError("Enter ID or Name");
            return;
        }

        refresh(ResidentFileIO.searchResident(key));
    }

    private void refresh(Object[][] data) {

        model.setRowCount(0);

        for (Object[] r : data)
            model.addRow(r);
    }

    private void loadSelected() {

        int r = table.getSelectedRow();

        if (r >= 0) {

            idField.setText(
                    model.getValueAt(r, 0).toString());

            nameField.setText(
                    model.getValueAt(r, 1).toString());

            ageField.setText(
                    model.getValueAt(r, 2).toString());

            roomField.setText(
                    model.getValueAt(r, 3).toString());
        }
    }

    private void clear() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        roomField.setText("");
        searchField.setText("");

        table.clearSelection();
    }

    private void showError(String msg) {

        JOptionPane.showMessageDialog(
                this,
                msg,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {

        JOptionPane.showMessageDialog(
                this,
                msg,
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
}