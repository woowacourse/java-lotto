package lotto.database.dao;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import lotto.dto.PurchaseDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LottoTicketDAO {
    private final Connection connection;

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

}
