package lotto.dao.lotto;

import lotto.dao.DBCPDataSource;
import lotto.dto.LottoTicketDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.dao.lotto.sqls.LottoTicketDAOSQLs.INSERT_LOTTO_TICKET;
import static lotto.dao.lotto.sqls.LottoTicketDAOSQLs.SELECT_LOTTO_TICKETS_BY_LOTTO_ROUND_ID;

public class LottoTicketDAO {
    public static List<LottoTicketDTO> selectLottoTicketsByLottoRoundId(int lottoRoundId) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOTTO_TICKETS_BY_LOTTO_ROUND_ID)) {

            preparedStatement.setInt(1, lottoRoundId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<LottoTicketDTO> lottoTicketDTOs = new ArrayList<>();
            while (resultSet.next()) {
                String lottoTicket = resultSet.getString("lotto_ticket");
                lottoTicketDTOs.add(new LottoTicketDTO(lottoTicket));
            }
            return lottoTicketDTOs;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void insertLottoTicket(LottoTicketDTO lottoTicketDTO, int lottoRoundId) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOTTO_TICKET)) {
            preparedStatement.setInt(1, lottoRoundId);
            preparedStatement.setString(2, lottoTicketDTO.getLottoTicket());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
