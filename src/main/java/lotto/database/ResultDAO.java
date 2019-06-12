package lotto.database;

import lotto.domain.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResultDAO {
    private final Connection con;

    public ResultDAO(Connection con) {
        this.con = con;
    }

    void addResult(Map<Rank, Integer> result) throws SQLException {
        String query = "INSERT INTO result (miss, fifth, fourth, third, second, first) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(result.get(Rank.MISS)));
        pstmt.setString(2, String.valueOf(result.get(Rank.FIFTH)));
        pstmt.setString(3, String.valueOf(result.get(Rank.FOURTH)));
        pstmt.setString(4, String.valueOf(result.get(Rank.THIRD)));
        pstmt.setString(5, String.valueOf(result.get(Rank.SECOND)));
        pstmt.setString(6, String.valueOf(result.get(Rank.FIRST)));
        pstmt.executeUpdate();
    }

    public Map<Rank, Integer> findResultById(int id) throws SQLException {
        String query = "SELECT * FROM result WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(id));
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        Map<Rank, Integer> result = new HashMap<>();
        result.put(Rank.MISS, Integer.valueOf(rs.getString("miss")));
        result.put(Rank.FIFTH, Integer.valueOf(rs.getString("fifth")));
        result.put(Rank.FOURTH, Integer.valueOf(rs.getString("fourth")));
        result.put(Rank.THIRD, Integer.valueOf(rs.getString("third")));
        result.put(Rank.SECOND, Integer.valueOf(rs.getString("second")));
        result.put(Rank.FIRST, Integer.valueOf(rs.getString("first")));
        return result;
    }

    public void removeResult(int id) throws SQLException {
        String query = "DELETE FROM result WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(id));
        pstmt.execute();
    }
}
