package lotto.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDAOImpl implements RoundDAO {
    public static RoundDAOImpl getInstance() {
        return new RoundDAOImpl();
    }

    public void addRound(int prize, double interestRate) {
        try (Connection con = Connector.getConnection()) {
            String query = "INSERT INTO round (prize, interest_rate) VALUES(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, String.valueOf(prize));
            pstmt.setString(2, String.valueOf(interestRate));
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public int getPrizeOfId(int id) {
        try (Connection con = Connector.getConnection()) {
            String query = "SELECT prize FROM round WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, String.valueOf(id));
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException(id + "에 해당하는 상금을 찾는데 실패했습니다.");
            }
            int result = rs.getInt("prize");
            rs.close();
            return result;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public double getInterestRateOfId(int id) {
        try (Connection con = Connector.getConnection()) {
            String query = "SELECT interest_rate FROM round WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, String.valueOf(id));
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException(id + "에 해당하는 수익률을 찾는데 실패했습니다.");
            }
            double result = rs.getDouble("interest_rate");
            rs.close();
            return result;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public int getLatestRoundId() {
        try (Connection con = Connector.getConnection()) {
            String query = "SELECT MAX(id) AS ThisId FROM round";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("진행한 로또 회차가 하나도 없습니다.");
            }
            int result = rs.getInt("ThisId");
            rs.close();
            return result;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public List<Integer> getAllIds() {
        try (Connection con = Connector.getConnection()) {
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
            rs.close();
            return ids;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public void removeRoundById(int roundId) {
        try (Connection con = Connector.getConnection()) {
            String query = "DELETE FROM round WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, String.valueOf(roundId));
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
