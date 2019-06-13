package lotto.dao;

import lottogame.domain.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class LottoResultDAO {
    private static LottoResultDAO lottoResultDAO;
    private Connection con;

    private LottoResultDAO() {
        con = DAOConnector.getConnection();
    }

    public static LottoResultDAO getInstance() {
        if (lottoResultDAO == null) {
            lottoResultDAO = new LottoResultDAO();
        }
        return lottoResultDAO;
    }

    public int findPresentRound() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int round = 0;
        try {
            String query = "select MAX(round) as round from lottoresult";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                round = rs.getInt("round");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return round;
    }

    public void updateLottoResult(int round, Map<Rank, Integer> result) {
        PreparedStatement pstmt = null;
        try {
            String query = "update lottoresult set \n" +
                    "    first_matcher = ?," +
                    "    second_matcher = ?," +
                    "    third_matcher = ?," +
                    "    fourth_matcher = ?," +
                    "    fifth_matcher = ? " +
                    "    where round = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,result.get(Rank.FIRST));
            pstmt.setInt(2,result.get(Rank.SECOND));
            pstmt.setInt(3,result.get(Rank.THIRD));
            pstmt.setInt(4,result.get(Rank.FOURTH));
            pstmt.setInt(5,result.get(Rank.FIFTH));
            pstmt.setInt(6,round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewLottoRound() {
        PreparedStatement pstmt = null;
        try {
            String query = "insert into lottoresult value();";
            pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
