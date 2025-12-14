package com.autompg.gui;

import com.autompg.db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AutoMPGSliderGUI {

    JTextArea output;

    public AutoMPGSliderGUI() {
        JFrame frame = new JFrame("Auto MPG Slider Filter");

        JSlider mpgSlider = new JSlider(0, 50, 20);
        JSlider hpSlider = new JSlider(0, 250, 100);

        mpgSlider.setMajorTickSpacing(10);
        mpgSlider.setPaintTicks(true);
        mpgSlider.setPaintLabels(true);

        hpSlider.setMajorTickSpacing(50);
        hpSlider.setPaintTicks(true);
        hpSlider.setPaintLabels(true);

        JButton filterBtn = new JButton("Filter");
        output = new JTextArea(20, 50);

        filterBtn.addActionListener(e -> filter(mpgSlider.getValue(), hpSlider.getValue()));

        JPanel top = new JPanel();
        top.add(new JLabel("MPG ≥"));
        top.add(mpgSlider);
        top.add(new JLabel("HP ≤"));
        top.add(hpSlider);
        top.add(filterBtn);

        frame.add(top, BorderLayout.NORTH);
        frame.add(new JScrollPane(output), BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void filter(int mpg, int hp) {
        output.setText("");

        String sql = "SELECT * FROM AutoMPG WHERE mpg >= ? AND horsepower <= ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, mpg);
            pstmt.setInt(2, hp);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                output.append(
                        rs.getDouble("mpg") + "  " +
                        rs.getInt("horsepower") + "  " +
                        rs.getString("car_name") + "\n"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AutoMPGSliderGUI();
    }
}
