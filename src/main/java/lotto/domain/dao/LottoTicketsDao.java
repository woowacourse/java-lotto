package lotto.domain.dao;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.factory.ManualTicketFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoTicketsDao {
    Connection connection;

    public LottoTicketsDao(Connection connection) {
        this.connection = connection;
    }

    public void addLottoTickets(int round, LottoTickets lottoTickets) throws SQLException {
        String query = "INSERT INTO lottoTickets VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            preparedStatement.setInt(1, round);
            preparedStatement.setString(2, lottoTicket.toString());
            preparedStatement.executeUpdate();
        }
    }

    public LottoTickets findByRoundLotto(int round) throws SQLException {
        String query = "SELECT * FROM lottoTickets WHERE round=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<LottoTicket> dbLottoTickets = new ArrayList<>();
        while (resultSet.next()) {
            String result = setInput(resultSet.getString("lottoNumbers"));
            dbLottoTickets.add(new ManualTicketFactory(result).create());
        }
        return new LottoTickets(dbLottoTickets);
    }

    private String setInput(String lottoNumbers) {
        return lottoNumbers.replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }
}
