package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoRoundDAO {
    private static LottoRoundDAO instance;

    private static final String insertQuery = "INSERT INTO lotto_rounds VALUES (?)";
    private static final String selectQuery = "SELECT * FROM lotto_rounds WHERE round>0";
    private static final String selectMaxQuery = "SELECT MAX(round) FROM lotto_rounds";

    private final Connection connection;

    private LottoRoundDAO(Connection connection) {
        this.connection = connection;
    }

    public static LottoRoundDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new LottoRoundDAO(connection);
        }
        return instance;
    }

    public void insertRound(int round) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(insertQuery);
        pstmt.setInt(1, round);
        pstmt.executeUpdate();
    }

    public List<Integer> selectLottoRounds() throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(selectQuery);
        ResultSet rs = pstmt.executeQuery();

        List<Integer> lottoRounds = new ArrayList<>();
        while (rs.next()) {
            lottoRounds.add(rs.getInt("round"));
        }

        return lottoRounds;
    }

    public int selectMaxRound() throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(selectMaxQuery);
        ResultSet rs = pstmt.executeQuery();

        return (rs.next()) ? rs.getInt("MAX(round)") : 0;
    }
}