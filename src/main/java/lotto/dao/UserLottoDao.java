package lotto.dao;

import lotto.domain.exceptions.LottoTicketException;
import lotto.dto.UserLottoDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserLottoDao {
    private static UserLottoDao dao;

    private UserLottoDao() {
    }

    public static UserLottoDao getDao() {
        if (dao == null) {
            dao = new UserLottoDao();
            return dao;
        }
        return dao;
    }

    private List<Integer> rsToList(ResultSet rs) throws SQLException {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 2; i < 8; i++) {
            numbers.add(rs.getInt(i));
        }
        return numbers;
    }

    public void insertUserLottos(UserLottoDto dto) {
        try {
            insertUserLottos(dto.getNumbers());
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public void insertUserLottos(int round, List<List<Integer>> numbers) throws SQLException {
        for (List<Integer> number : numbers) {
            insertLotto(round, number);
        }
    }

    public void insertUserLottos(List<List<Integer>> numbers) throws SQLException {
        int currentRound = DBManager.lastRound();
        PreparedStatement stmt = DBManager.getConnection().prepareStatement("INSERT INTO lotto_game(round) VALUES(?)");
        stmt.setInt(1, currentRound + 1);
        stmt.executeUpdate();
        insertUserLottos(currentRound + 1, numbers);
    }

    private void insertLotto(int round, List<Integer> number) throws SQLException {
        Connection conn = DBManager.getConnection();
        String sql = "INSERT INTO user_lotto(num_1,num_2,num_3,num_4,num_5,num_6,round_id) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 1; i < 7; i++) {
            stmt.setInt(i, number.get(i - 1));
        }
        stmt.setInt(7, round);
        stmt.executeUpdate();
    }

    public List<List<Integer>> selectUserLottos(int round) {
        Connection conn = DBManager.getConnection();
        String sql = "SELECT * FROM user_lotto WHERE round_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, round);
            ResultSet rs = stmt.executeQuery();
            return getLists(rs);
        } catch (SQLException e) {
            throw new LottoTicketException();
        }
    }

    private List<List<Integer>> getLists(ResultSet rs) throws SQLException {
        List<List<Integer>> numbers = new ArrayList<>();
        while (rs.next()) {
            numbers.add(rsToList(rs));
        }
        return numbers;
    }

    public List<List<Integer>> currentUserLottos() {
        return selectUserLottos(DBManager.lastRound());
    }
}
