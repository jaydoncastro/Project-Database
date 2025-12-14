package com.autompg.gui;

import com.autompg.db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AutoMPGTextSearchGUI {

    JTextField input;
    JTextArea output;

    public AutoMPGTextSearchGUI() {
        JFrame frame = new JFrame("Auto MPG Search");

        input = new JTextField(15);
        JButton button = new JButton("Search");
        output = new JTextArea(20, 50);

        button.addActionListener(e -> runQuery());

        JPanel top = new JPanel();
        top.add(new JLabel("Enter ALL or MPG value:"));
        top.add(input);
        top.add(button);

        frame.add(top, BorderLayout.NORTH);
        frame.add(new JScrollPane(output), BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void runQuery() {
        output.setText("");
        String userInput = input.getText();
        String query;

        if (userInput.equalsIgnoreCase("ALL")) {
            query = "SELECT * FROM AutoMPG";
        } else {
            query = "SELECT * FROM AutoMPG WHERE mpg >= " + userInput;
        }

        try (
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                output.append(
                        rs.getDouble("mpg") + "  " +
                        rs.getInt("horsepower") + "  " +
                        rs.getString("car_name") + "\n"
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AutoMPGTextSearchGUI();
    }
}
