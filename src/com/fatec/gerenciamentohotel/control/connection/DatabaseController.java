package com.fatec.gerenciamentohotel.control.connection;

import java.io.*;
import java.sql.*;

public class DatabaseController {
    private String sourcePath;

    public DatabaseController() {
        this.sourcePath = System.getProperty("user.dir") + "/hotel_script.sql";
        create();
    }

    private void create() {
        try {
            Connection con = ConnectionDB.getInstance().getConnection();
            // System.out.println(sourcePath);
            BufferedReader br = new BufferedReader(new FileReader(sourcePath));
            String line, query = "";
            while ((line = br.readLine()) != null) {
                query = query.concat(line);
            }
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getStatement());
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage() + "\nBanco já existe");
        }

    }
}
