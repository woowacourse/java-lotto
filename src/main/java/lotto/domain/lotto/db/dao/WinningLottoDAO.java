package lotto.domain.lotto.db.dao;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lotto.db.ConnectionHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDAO implements DAO<WinningLotto> {
    private final static String SEPERATOR = ",";
    private ConnectionHandler connectionHandler;
    private Connection connection = null;

    public WinningLottoDAO(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        this.connection = getConnection();
    }

    public int getRecentId() throws SQLException {
        String query = "SELECT MAX(id) FROM winning_lotto";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next() == false) {
            return 0;
        }
        return rs.getInt(1);
    }

    @Override
    public Connection getConnection() {
        connection = connectionHandler.getConnection();
        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("connection 오류:" + e.getMessage());
        }
    }

    @Override
    public void add(WinningLotto winningLotto) throws SQLException {

        String query = "INSERT INTO winning_lotto (lotto_numbers, bonus_number) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        List<String> tokens = new ArrayList<>();
        for (LottoNumber number : winningLotto.getWinningLotto().getNumbers()) {
            tokens.add(number.toString());
        }
        pstmt.setString(1, StringUtils.join(tokens, ','));
        pstmt.setInt(2, winningLotto.getBonusNumber());
        pstmt.executeUpdate();
    }

    @Override
    public void create() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS winning_lotto (\n" +
                "id INT AUTO_INCREMENT primary key NOT NULL,\n" +
                "lotto_numbers VARCHAR(17) NOT NULL,\n" +
                "bonus_number TINYINT NOT NULL" +
                ");";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    @Override
    public WinningLotto foundById(int id) throws SQLException {
        String query = "SELECT * FROM winning_lotto WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        rs.next();
        String input = rs.getString("lotto_numbers");

        String[] tokens = input.split(SEPERATOR);
        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            numbers.add(Integer.parseInt(token));
        }

        int bonusNumber = rs.getInt("bonus_number");

        return WinningLotto.create(numbers, bonusNumber);
    }

    @Override
    public void updateById(int id) {

    }

    @Override
    public void delete(int id) {

    }
}
