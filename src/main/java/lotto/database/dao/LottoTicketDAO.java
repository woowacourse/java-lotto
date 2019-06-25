package lotto.database.dao;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import lotto.dto.PurchaseDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoTicketDAO {
    private final Connection connection;
    private final int LOTTO_NUMBER_LENGTH = 6;

    public LottoTicketDAO(Connection connection) {
        this.connection = connection;
    }

    public void addLottoTickets(PurchaseDTO purchaseDTO) throws SQLException {
        String query = "INSERT INTO lotto (round, num1, num2, num3, num4, num5, num6) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, purchaseDTO.getRound());
        for (LottoTicket lottoTicket : purchaseDTO.getLottoTickets().getLottoTickets()) {
            addLottoTicket(pstmt, lottoTicket);
        }
    }

    private void addLottoTicket(PreparedStatement pstmt, LottoTicket lottoTicket) throws SQLException {
        int index = 2;
        for (LottoNumber lottoNumber : lottoTicket.getLottoNumbers()) {
            pstmt.setInt(index++, lottoNumber.getNumber());
        }
        pstmt.executeUpdate();
    }

    public List<List<Integer>> getLottoNumbersByRound(final int round) throws SQLException {
        String query = "SELECT num1,num2,num3,num4,num5,num6 FROM lotto WHERE round = (?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        while (rs.next()) {
            lottoNumbers.add(getLottoNumber(rs));
        }
        rs.close();
        return lottoNumbers;
    }

    public List<Integer> getLottoNumber(ResultSet rs) throws SQLException {
        List<Integer> number = new ArrayList<>();
        for (int i = 1; i <= LOTTO_NUMBER_LENGTH; i++) {
            number.add(rs.getInt(i));
        }
        return number;
    }
}
