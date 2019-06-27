package lotto.dao;

import lotto.domain.lottoresult.WinningLotto;
import lotto.dto.LottoHistoryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoHistoryDAO {
    private static final String selectQuery = "SELECT num1, num2, num3, num4, num5, num6, bonus_num, "
            + "first_rank, second_rank, third_rank, fourth_rank, fifth_rank, fail_rank, winning_reward, earning_rate "
            + "FROM results a, winning_lotto b WHERE a.round=? and b.round=?";

    private static LottoHistoryDAO instance;

    private JdbcTemplate jdbcTemplate;

    private LottoHistoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static LottoHistoryDAO getInstance(JdbcTemplate jdbcTemplate) {
        if (instance == null) {
            instance = new LottoHistoryDAO(jdbcTemplate);
        }

        if (!instance.jdbcTemplate.equals(jdbcTemplate)) {
            instance.jdbcTemplate = jdbcTemplate;
        }
        return instance;
    }

    public LottoHistoryDTO selectLottoHistory(int round) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(round);
        parameters.add(round);

        ResultSet rs = jdbcTemplate.executeQuery(selectQuery, parameters);

        LottoHistoryDTO lottoHistoryDTO = (rs.next()) ? getLottoHistoryDTO(rs, 1) : new LottoHistoryDTO();
        lottoHistoryDTO.setRound(round);

        return lottoHistoryDTO;
    }

    private LottoHistoryDTO getLottoHistoryDTO(ResultSet rs, int index) throws SQLException {
        LottoHistoryDTO lottoHistoryDTO = new LottoHistoryDTO();
        lottoHistoryDTO.setWinningLottoDTO(WinningLottoDAO.getWinningLottoDTO(rs, index));
        lottoHistoryDTO.setLottoResultDTO(LottoResultDAO.getLottoResultDTO(rs, index + WinningLotto.SIZE));

        return lottoHistoryDTO;
    }
}
