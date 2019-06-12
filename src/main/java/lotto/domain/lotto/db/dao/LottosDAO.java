package lotto.domain.lotto.db.dao;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.db.ConnectionHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottosDAO implements DAO<Lottos> {
    private final static String SEPERATOR = ",";
    private ConnectionHandler connectionHandler;
    private Connection connection;

    public LottosDAO(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        this.connection = connectionHandler.getConnection();
    }

    @Override
    public Connection getConnection() {
        connection = connectionHandler.getConnection();
        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("connection 오류:" + e.getMessage());
        }
    }

    @Override
    public void add(Lottos lottos) throws SQLException {

    }

    public void add(Lottos lottos, int id) throws SQLException {
        for (Lotto lotto : lottos.getLottos()) {
            String query = "INSERT INTO lottos (round, lotto_numbers) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            List<String> tokens = new ArrayList<>();
            for (LottoNumber number : lotto.getNumbers()) {
                tokens.add(number.toString());
            }
            pstmt.setString(2, StringUtils.join(tokens, ','));
            pstmt.executeUpdate();
        }
    }

    @Override
    public void create() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS lottos (\n" +
                "round INT NOT NULL,\n" +
                "lotto_numbers VARCHAR(17) NOT NULL,\n" +
                "id INT AUTO_INCREMENT primary key NOT NULL\n" +
                ");";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    @Override
    public Lottos foundById(int id) throws SQLException {
        String query = "SELECT * FROM lottos WHERE round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();

        while (rs.next()) {
            String input = rs.getString("lotto_numbers");
            String[] tokens = input.split(SEPERATOR);
            List<Integer> numbers = new ArrayList<>();
            for (String token : tokens) {
                numbers.add(Integer.parseInt(token));
            }
            lottos.add(new Lotto(numbers));
        }
        return new Lottos(lottos);
    }

    @Override
    public void updateById(int id) {

    }

    @Override
    public void delete(int id) {

    }
}
