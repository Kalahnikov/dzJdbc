package ru.flamexander.db.interaction.ex05_repositories;

import java.io.*;
import java.sql.SQLException;

public class DbMigrator {
    private DataSource dataSource;

    public DbMigrator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void init(String fileName) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while ((br.readLine()) != null) {
              String line = br.readLine();
              stringBuilder = stringBuilder.append(line).append("/n");
            }
            dataSource.getStatement().executeUpdate(String.valueOf(stringBuilder));
            br.close();
        } catch (IOException e) {
            System.out.println("ERROR: unable to read file " + fileName);
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
