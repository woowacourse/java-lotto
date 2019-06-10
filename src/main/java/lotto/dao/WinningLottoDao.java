package lotto.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lotto.DataBase;
import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.domain.WinningLotto;

import java.sql.*;

public class WinningLottoDao {
    private final DataBase dataBase;

    public WinningLottoDao(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public int addWinningLotto(WinningLotto winningLotto) throws SQLException {
        String query = "INSERT INTO winning_lotto VALUES (?, ?, ?)";
        PreparedStatement pstmt = dataBase.getConnection().prepareStatement(query);
        int times = countWinningLottoTimes();
        Gson gson = new GsonBuilder().create();

        pstmt.setInt(1, times + 1);
        pstmt.setString(2, gson.toJson(winningLotto.getWinningLotto()));
        pstmt.setString(3, gson.toJson(winningLotto.getBonusNum()));
        return pstmt.executeUpdate();
    }

    public WinningLotto findByTimes(int times) throws SQLException {
        String query = "SELECT * FROM winning_lotto WHERE times = ?";
        PreparedStatement pstmt = dataBase.getConnection().prepareStatement(query);
        pstmt.setInt(1, times);
        ResultSet rs = pstmt.executeQuery();

        Gson gson = new Gson();

        if (!rs.next()) return null;

        Lotto lotto = gson.fromJson(rs.getString("winning_number"), Lotto.class);
        Number bonusNumber = gson.fromJson(rs.getString("bonus_number"), Number.class);

        return new WinningLotto(lotto, bonusNumber);
    }

    public int countWinningLottoTimes() throws SQLException{
        String query = "SELECT COUNT(*) FROM winning_lotto";
        PreparedStatement pstmt = dataBase.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return Integer.parseInt(rs.getString(1));
    }
}
