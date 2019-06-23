package lotto.dao.lotto;

import lotto.dao.JdbcTemplate.JdbcTemplate;
import lotto.dto.LottoTicketDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.dao.lotto.sqls.LottoTicketDAOSQLs.INSERT_LOTTO_TICKET;
import static lotto.dao.lotto.sqls.LottoTicketDAOSQLs.SELECT_LOTTO_TICKETS_BY_LOTTO_ROUND_ID;

public class LottoTicketDAO {
    private static class LottoTicketDAOLazyHolder {
        private static final LottoTicketDAO INSTANCE = new LottoTicketDAO();
    }

    public static LottoTicketDAO getInstance() {
        return LottoTicketDAOLazyHolder.INSTANCE;
    }

    public List<LottoTicketDTO> selectLottoTicketsByLottoRoundId(int lottoRoundId) throws SQLException {
        Map<String, Integer> rowMapper = Collections.singletonMap("lottoRoundId", lottoRoundId);
        List<LottoTicketDTO> lottoTicketDTOs = new ArrayList<>();
        JdbcTemplate.query(SELECT_LOTTO_TICKETS_BY_LOTTO_ROUND_ID, rowMapper, (preparedStatement) -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String lottoTicket = resultSet.getString("lotto_ticket");
                lottoTicketDTOs.add(new LottoTicketDTO(lottoTicket));
            }
        });

        return lottoTicketDTOs;
    }

    public void insertLottoTicket(LottoTicketDTO lottoTicketDTO, int lottoRoundId) throws SQLException {
        Map<String, Object> rowMapper = new HashMap<>();
        rowMapper.put("lottoRoundId", lottoRoundId);
        rowMapper.put("lottoTicket", lottoTicketDTO.getLottoTicket());

        JdbcTemplate.query(INSERT_LOTTO_TICKET, rowMapper, (PreparedStatement::executeUpdate));
    }
}
