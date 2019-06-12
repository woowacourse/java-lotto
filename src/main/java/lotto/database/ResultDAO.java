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

    public void addResult(int roundId, Map<Rank, Integer> result) throws SQLException {
        String query = "INSERT INTO result (ro_id, miss, fifth, fourth, third, second, first) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(roundId));
        pstmt.setString(2, String.valueOf(result.get(Rank.MISS)));
        pstmt.setString(3, String.valueOf(result.get(Rank.FIFTH)));
        pstmt.setString(4, String.valueOf(result.get(Rank.FOURTH)));
        pstmt.setString(5, String.valueOf(result.get(Rank.THIRD)));
        pstmt.setString(6, String.valueOf(result.get(Rank.SECOND)));
        pstmt.setString(7, String.valueOf(result.get(Rank.FIRST)));
        pstmt.executeUpdate();
    }

    public Map<Rank, Integer> findResultByRoundId(int roundId) throws SQLException {
        String query = "SELECT * FROM result WHERE ro_id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(roundId));
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            throw new SQLException(String.format("데이터베이스에서 회차 번호가 %d인 당첨 결과를 찾는 데 실패했습니다.", roundId));
        }

        Map<Rank, Integer> result = new HashMap<>();
        result.put(Rank.MISS, rs.getInt("miss"));
        result.put(Rank.FIFTH, rs.getInt("fifth"));
        result.put(Rank.FOURTH, rs.getInt("fourth"));
        result.put(Rank.THIRD, rs.getInt("third"));
        result.put(Rank.SECOND, rs.getInt("second"));
        result.put(Rank.FIRST, rs.getInt("first"));
        return result;
    }

    public void removeResult(int roundId) throws SQLException {
        String query = "DELETE FROM result WHERE ro_id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(roundId));
        pstmt.execute();
    }
}
