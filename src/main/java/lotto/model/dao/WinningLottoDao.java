package lotto.model.dao;

import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.config.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDao {

    public void saveWinningLotto(Lotto winningLottoTicket, LottoNumber bonusNumber) throws SQLException {
        int round = getLatestRound();
        String query = "INSERT INTO winning_lotto_info (round, first, second, third, fourth, fifth, sixth, bonus_number) VALUES(?,?,?,?,?,?,?,?)";
        Connection connection = DBConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, round);
        ps.setInt(2, winningLottoTicket.getFirst());
        ps.setInt(3, winningLottoTicket.getSecond());
        ps.setInt(4, winningLottoTicket.getThird());
        ps.setInt(5, winningLottoTicket.getFourth());
        ps.setInt(6, winningLottoTicket.getFifth());
        ps.setInt(7, winningLottoTicket.getSixth());
        ps.setInt(8, bonusNumber.getNumber());
        ps.executeUpdate();
        DBConnector.closeConnection(connection);
    }

    public int getLatestRound() throws SQLException {
        String query = "SELECT MAX(ROUND) as MAX_ROUND FROM winning_lotto_info";
        Connection connection = DBConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int round = 77;
        if (rs.next()) {
            round = rs.getInt("MAX_ROUND");
        }
        DBConnector.closeConnection(connection);
        return round + 1;
    }

    public List<String> fetchRequestWinningLotto(int requestRound) throws SQLException {
        List<String> winningLotto = new ArrayList<>();
        String query = "SELECT * FROM winning_lotto_info WHERE round = ?";
        Connection connection = DBConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, requestRound);
        ResultSet resultSet = ps.executeQuery();

        retrieveDataFromDB(resultSet, winningLotto);

        DBConnector.closeConnection(connection);
        return winningLotto;
    }

    private void retrieveDataFromDB(ResultSet resultSet, List<String> winningLotto) throws SQLException {
        if (resultSet.next()) {
            List<String> numbers = new ArrayList<>();
            numbers.add(Integer.toString(resultSet.getInt("first")));
            numbers.add(Integer.toString(resultSet.getInt("second")));
            numbers.add(Integer.toString(resultSet.getInt("third")));
            numbers.add(Integer.toString(resultSet.getInt("fourth")));
            numbers.add(Integer.toString(resultSet.getInt("fifth")));
            numbers.add(Integer.toString(resultSet.getInt("sixth")));
            numbers.add("보너스 번호 : " + resultSet.getInt("bonus_number"));
            winningLotto.add(String.join(",", numbers));
        }
    }

    public void deleteWinningLotto(int targetRound) throws SQLException {
        String query = "DELETE FROM winning_lotto_info WHERE round = ?";
        PreparedStatement ps = DBConnector.getConnection().prepareStatement(query);
        ps.setInt(1, targetRound - 1);
        ps.executeUpdate();
    }
}
