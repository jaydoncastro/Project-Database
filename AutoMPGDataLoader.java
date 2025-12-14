package com.autompg.insert;

import com.autompg.db.DBConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AutoMPGDataLoader {

    public static void main(String[] args) {

        String sql = "INSERT INTO AutoMPG " +
                "(mpg, cylinders, displacement, horsepower, weight, acceleration, model_year, origin, car_name) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            BufferedReader br = new BufferedReader(new FileReader("data/auto-mpg.tsv"))
        ) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\t");

                pstmt.setDouble(1, Double.parseDouble(d[0]));
                pstmt.setInt(2, Integer.parseInt(d[1]));
                pstmt.setDouble(3, Double.parseDouble(d[2]));
                pstmt.setInt(4, Integer.parseInt(d[3]));
                pstmt.setInt(5, Integer.parseInt(d[4]));
                pstmt.setDouble(6, Double.parseDouble(d[5]));
                pstmt.setInt(7, Integer.parseInt(d[6]));
                pstmt.setInt(8, Integer.parseInt(d[7]));
                pstmt.setString(9, d[8]);

                pstmt.executeUpdate();
            }

            System.out.println("Data inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
