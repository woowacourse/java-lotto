package lotto.dao;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.factory.ManualTicketFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoTicketsDao {
    private Connection connection;

    public LottoTicketsDao(final Connection connection) {
        this.connection = connection;
    }

    public void addLottoTickets(int round, LottoTickets lottoTickets) throws SQLException {
        String query = "INSERT INTO lottoTickets(round, lottoNumbers) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            preparedStatement.setInt(1, round);
            preparedStatement.setString(2, lottoTicket.toString());
            preparedStatement.executeUpdate();
        }
    }

    public LottoTickets findLottoByRound(int round) throws SQLException {
        String query = "SELECT * FROM lottoTickets WHERE round=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (resultSet.next()) {
            String result = setInput(resultSet.getString("lottoNumbers"));
            lottoTickets.add(new ManualTicketFactory(result).create());
        }
        return new LottoTickets(lottoTickets);
    }

    private String setInput(String lottoNumbers) {
        return lottoNumbers.replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }

    public void deleteLottoByRound(int round) throws SQLException {
        String query = "DELETE FROM lottoTickets WHERE  round = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        preparedStatement.executeUpdate();
    }
}
