package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class WinningNumbersDAO {
    protected static List<Integer> fetchWinningNumbers(int round) throws SQLException {
        final DAO dao = DAO.getInstance();
        final PreparedStatement pstmt = dao.connect().prepareStatement(
                "SELECT round, first_num, second_num, third_num, fourth_num, fifth_num, sixth_num, bonus_num FROM winning_numbers WHERE round = ?"
        );
        pstmt.setInt(1, round);
        final List<Integer> fetched = fetchFromResult(pstmt.executeQuery());
        dao.close();
        if (fetched.isEmpty()) {
            throw new SQLException();
        }
        return fetched;
    }

    private static List<Integer> fetchFromResult(ResultSet result) throws SQLException {
        List<Integer> fetched = new ArrayList<>();
        while (result.next()) {
            for (int i = 2; i <= Lotto.NUMBER_OF_PICKS + 2; i++) {
                fetched.add(result.getInt(i));
            }
        }
        return fetched;
    }

    protected static void register(WinningNumbersWeb winningNumbers) {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                registerHelper(winningNumbers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private static void registerHelper(WinningNumbersWeb winningNumbers) throws SQLException {
        final List<LottoNumber> main = winningNumbers.mainNumbers();
        final LottoNumber bonus = winningNumbers.bonusNumber();
        final DAO dao = DAO.getInstance();
        final PreparedStatement pstmt = dao.connect().prepareStatement(
                "INSERT INTO winning_numbers VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        );
        pstmt.setInt(1, winningNumbers.round());
        for (int i = 0; i < Lotto.NUMBER_OF_PICKS; i++) {
            pstmt.setString(i + 2, main.get(i).toString());
        }
        pstmt.setString(8, bonus.toString());
        pstmt.executeUpdate();
        dao.close();
    }
}
