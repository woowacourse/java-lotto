package lotto.dao;

import lotto.domain.lottoresult.WinningLotto;
import lotto.dto.LottoHistoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoHistoryDAO {
    private static final String selectQuery = "SELECT num1, num2, num3, num4, num5, num6, bonus_num, "
            + "first_rank, second_rank, third_rank, fourth_rank, fifth_rank, fail_rank, winning_reward, earning_rate "
            + "FROM results a, winning_lotto b WHERE a.round=? and b.round=?";

    private final Connection connection;

    public LottoHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    public LottoHistoryDTO selectLottoHistory(int round) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(selectQuery);
        pstmt.setInt(1, round);
        pstmt.setInt(2, round);

        ResultSet rs = pstmt.executeQuery();

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
