package lotto.dao;

import lotto.dto.WinningLottoDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDao {
    private static WinningLottoDao dao;

    private WinningLottoDao() {

    }

    public static WinningLottoDao getDao() {
        if (dao == null) {
            dao = new WinningLottoDao();
            return dao;
        }
        return dao;
    }

    public WinningLottoDto selectWinningLotto(int round) {
        Connection conn = DBManager.getConnection();
        String sql = "SELECT num_1,num_2,num_3,num_4,num_5,num_6,num_bonus FROM winning_lotto where round_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, round);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return resultSet(rs);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public WinningLottoDto resultSet(ResultSet rs) throws SQLException {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            numbers.add(rs.getInt(i));
        }

        return new WinningLottoDto(numbers, rs.getInt(7));
    }

    public void insertWinningLotto(WinningLottoDto dto) {
        try {
            insertWinningLotto(dto.getNumbers(), dto.getBonus());
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private void insertWinningLotto(int round, List<Integer> numbers, int bonus) throws SQLException {
        Connection conn = DBManager.getConnection();
        String sql = "INSERT INTO winning_lotto(num_1,num_2,num_3,num_4,num_5,num_6,num_bonus,round_id) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 1; i < 7; i++) {
            stmt.setInt(i, numbers.get(i - 1));
        }
        stmt.setInt(7, bonus);
        stmt.setInt(8, round);
        stmt.executeUpdate();
    }

    private void insertWinningLotto(List<Integer> numbers, int bonus) throws SQLException {
        insertWinningLotto(DBManager.lastRound(), numbers, bonus);
    }

    public WinningLottoDto currentWinningLotto() {
        return selectWinningLotto(DBManager.lastRound());
    }
}
