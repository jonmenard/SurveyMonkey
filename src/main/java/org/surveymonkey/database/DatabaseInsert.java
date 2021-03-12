package org.surveymonkey.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseInsert {

    public void sample() {

        Connection connection = ConnectionFactory.getConnection();
        String sql = "  ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, 2);
            ResultSet results = pstmt.executeQuery();
            while (results.next()) {
                results.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in getProject()");
            e.printStackTrace();
        } finally {
            DbUtil.close(connection);
        }

    }

}
