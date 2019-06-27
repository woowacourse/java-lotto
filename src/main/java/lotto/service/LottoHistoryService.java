package lotto.service;

import lotto.dao.DBConnection;
import lotto.dao.JdbcTemplate;
import lotto.dao.LottoHistoryDao;
import lotto.dao.LottoTicketDao;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.dto.LottoHistoryDTO;

import java.sql.SQLException;

public class LottoHistoryService {
    private static LottoHistoryService instance;

    private final LottoHistoryDao lottoHistoryDAO;
    private final LottoTicketDao lottoTicketDAO;

    private LottoHistoryService() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance(DBConnection.getConnection());
        lottoHistoryDAO = LottoHistoryDao.getInstance(jdbcTemplate);
        lottoTicketDAO = LottoTicketDao.getInstance(jdbcTemplate);
    }

    public static LottoHistoryService getInstance() {
        if (instance == null) {
            instance = new LottoHistoryService();
        }
        return instance;
    }

    public LottoHistoryDTO historyOf(int round) throws SQLException {
        LottoHistoryDTO lottoHistoryDTO = lottoHistoryDAO.selectLottoHistory(round);
        LottoTicketGroup lottos = lottoTicketDAO.selectByLottoRound(round);
        lottoHistoryDTO.setLottos(lottos);

        return lottoHistoryDTO;
    }
}
