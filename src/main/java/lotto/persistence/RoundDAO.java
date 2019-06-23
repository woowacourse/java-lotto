package lotto.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDAO {
    private final Connection con;

    public RoundDAO(Connection con) {
        this.con = con;
    }

    public void addRound(int prize, double interestRate) throws SQLException {
        String query = "INSERT INTO round (prize, interest_rate) VALUES(?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(prize));
        pstmt.setString(2, String.valueOf(interestRate));
        pstmt.executeUpdate();
    }

    public int getPrizeOfId(int id) throws SQLException {
        String query = "SELECT prize FROM round WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(id));
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getInt("prize");
    }

    public double getInterestRateOfId(int id) throws SQLException {
        String query = "SELECT interest_rate FROM round WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(id));
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1.0;

        return rs.getDouble("interest_rate");
    }

    public int getLatestRoundId() throws SQLException {
        String query = "SELECT MAX(id) AS ThisId FROM round";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("진행한 로또 회차가 하나도 없습니다.");
        }
        return rs.getInt("ThisId");
    }

    public List<Integer> getAllIds() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT id FROM round";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("진행한 로또 회차가 하나도 없습니다.");
        }
        do {
            ids.add(rs.getInt("id"));
        } while (rs.next());
        return ids;
    }

    public void removeRoundById(int roundId) throws SQLException {
        String query = "DELETE FROM round WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(roundId));
        pstmt.executeUpdate();
    }
}
