package model;

import service.LottoHistoryVO;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LottoHistoryDAO {
    public static void writeHistory(String encodedLottos, int round) {
        try {
            final DAO dao = DAO.getInstance();
            final PreparedStatement pstmt = dao.connect().prepareStatement(
                    "INSERT INTO purchase_history (round, lottos) VALUES (?, ?)"
            );
            pstmt.setInt(1, round);
            pstmt.setString(2, encodedLottos);
            pstmt.executeUpdate();
            dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LottoHistoryVO getHistoryByDate(String date) throws SQLException {
        final DAO dao = DAO.getInstance();
        final PreparedStatement pstmt = dao.connect().prepareStatement(
                "SELECT round, lottos FROM purchase_history WHERE date = ?"
        );
        pstmt.setTimestamp(1, Timestamp.from(Instant.parse(date)));
        final ResultSet result = pstmt.executeQuery();
        LottoHistoryVO history = null;
        if (result.next()) {
            history = new LottoHistoryVO(result.getInt(1), result.getString(2));
        }
        dao.close();
        if (history == null) {
            throw new IllegalArgumentException();
        }
        return history;
    }

    public static List<Timestamp> retrieveDatesFromHistory() {
        try {
            final DAO dao = DAO.getInstance();
            final PreparedStatement pstmt = dao.connect().prepareStatement(
                    "SELECT date FROM purchase_history LIMIT 1000"
            );
            final List<Timestamp> fetched = new ArrayList<>();
            final ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                fetched.add(result.getTimestamp(1));
            }
            dao.close();
            return fetched;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}