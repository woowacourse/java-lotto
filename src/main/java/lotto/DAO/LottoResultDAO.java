package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
