package lotto.dao;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDAO {

    public void addRound() throws SQLException {
        String query = "INSERT INTO round () VALUES ()";
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.executeUpdate();
        DBConnection.closeConnection(con);
    }

    public int findlast() throws SQLException {
        String query = "SELECT * FROM round ORDER BY id DESC LIMIT 1";
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        DBConnection.closeConnection(con);
        return 1;
    }

    public List<Integer> findAll() throws SQLException {
        String query = "SELECT * FROM round";
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> rounds = new ArrayList<>();
        while (rs.next()) {
            rounds.add(rs.getInt("id"));
        }
        DBConnection.closeConnection(con);
        return rounds;
    }

}
