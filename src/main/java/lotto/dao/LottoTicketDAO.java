package lotto.dao;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoNumberGroup;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoTicketDAO {
    private static LottoTicketDAO instance;

    private static final String insertQuery = "INSERT INTO lotto_tickets (round, num1, num2, num3, num4, num5, num6) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String selectQuery = "SELECT * FROM lotto_tickets WHERE round = ?";

    private Connection connection;

    private LottoTicketDAO(Connection connection) {
        this.connection = connection;
    }

    public static LottoTicketDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new LottoTicketDAO(connection);
        }

        if (instance.connection != connection) {
            instance.connection = connection;
        }
        return instance;
    }

    public void insertLottoTickets(int round, LottoTicketGroup lottoTickets) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(insertQuery);
        pstmt.setInt(1, round);

        for (LottoTicket lottoTicket : lottoTickets) {
            insertLottoTicket(pstmt, lottoTicket);
        }
    }

    private void insertLottoTicket(PreparedStatement pstmt, LottoTicket lottoTicket) throws SQLException {
        int index = 2;
        for (LottoNumber lottoNumber : lottoTicket) {
            pstmt.setInt(index++, lottoNumber.getNumber());
        }
        pstmt.executeUpdate();
    }

    public LottoTicketGroup selectByLottoRound(int round) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(selectQuery);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (rs.next()) {
            List<Integer> lottoNums = getNumbers(rs, 3);
            lottoTickets.add(LottoTicket.create(() -> lottoNums));
        }

        return new LottoTicketGroup(lottoTickets);
    }

    private List<Integer> getNumbers(ResultSet rs, int index) throws SQLException {
        List<Integer> lottoNums = new ArrayList<>();
        for (int i = 0; i < LottoNumberGroup.LOTTO_SIZE; i++) {
            lottoNums.add(rs.getInt(index++));
        }

        return lottoNums;
    }
}