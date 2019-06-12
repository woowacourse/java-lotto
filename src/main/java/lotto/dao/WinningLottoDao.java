package lotto.dao;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;
import lotto.domain.factory.ManualTicketFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDao {
    private final Connection connection;

    public WinningLottoDao(final Connection connection) {
        this.connection = connection;
    }

    public void addWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        LottoTicket lottoTicket = winningLotto.getWinningLottoTicket();
        LottoNumber bonusBall = winningLotto.getBonusBall();
        String query = "INSERT into winningLotto(lottoNumbers, bonusBall, round) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, lottoTicket.toString());
        preparedStatement.setString(2, bonusBall.toString());
        preparedStatement.setInt(3, round);
        preparedStatement.executeUpdate();
    }

    public WinningLotto findWinningLottoByRound(int round) throws SQLException {
        String query = "SELECT * FROM winningLotto WHERE round = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();
        WinningLotto winningLotto;
        while (resultSet.next()) {
            winningLotto = new WinningLotto(new ManualTicketFactory(setInput(resultSet.getString("lottoNumbers"))).create(),
                    Integer.parseInt(resultSet.getString("bonusBall")));
            return winningLotto;
        }
        return null;
    }

    private String setInput(String lottoNumbers) {
        return lottoNumbers.replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }

    public void deleteWinningLottoByRound(int round) throws SQLException {
        String query = "DELETE FROM winningLotto WHERE round = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        preparedStatement.executeUpdate();
    }
}