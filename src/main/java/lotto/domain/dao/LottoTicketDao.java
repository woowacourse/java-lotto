package lotto.domain.dao;

import lotto.DBUtils;
import lotto.domain.lottomanager.LottoTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LottoTicketDao extends LottoDaoUtils {
    private static final int ROUND_COLUMN = 1;

    private static LottoTicketDao lottoTicketDao = new LottoTicketDao();

    public static LottoTicketDao getInstance() {
        return lottoTicketDao;
    }

    public void addLottoTicket(LottoTicket lottoTicket) {
        String insertLottoQuery = "INSERT INTO lottoTicket (round, number_1, number_2, number_3" +
                ", number_4, number_5, number_6) VALUES(?,?,?,?,?,?,?)";

        try (Connection connection = DBUtils.getConnection()) {
            int round = getRound(connection);

            PreparedStatement pstmt = connection.prepareStatement(insertLottoQuery);

            pstmt.setInt(ROUND_COLUMN, round);
            setLottoNumbers(pstmt, getLottoNumbers(lottoTicket.getLottoTicket()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("addLottoTicket() : " + e.getMessage());
        }
    }
}
