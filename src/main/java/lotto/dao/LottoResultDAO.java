package lotto.dao;

import lotto.domain.lottoresult.LottoRank;
import lotto.domain.lottoresult.RankCount;
import lotto.dto.LottoResultDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoResultDAO {
    private static LottoResultDAO instance;

    private static final String insertQuery = "INSERT INTO results (round, fail_rank, fifth_rank, fourth_rank, third_rank, second_rank, first_rank, winning_reward, earning_rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String selectQuery = "SELECT first_rank, second_rank, third_rank, fourth_rank, fifth_rank, fail_rank, winning_reward, earning_rate FROM results WHERE round=?";

    private final Connection connection;

    private LottoResultDAO(Connection connection) {
        this.connection = connection;
    }

    public static LottoResultDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new LottoResultDAO(connection);
        }
        return instance;
    }

    public void insertResult(int round, LottoResultDTO lottoResultDTO) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(insertQuery);
        int parameterIndex = 1;

        pstmt.setInt(parameterIndex++, round);
        for (RankCount rankCount : lottoResultDTO.getRankCounts()) {
            pstmt.setInt(parameterIndex++, rankCount.getCount());
        }
        pstmt.setBigDecimal(parameterIndex++, lottoResultDTO.getWinningReward());
        pstmt.setBigDecimal(parameterIndex, lottoResultDTO.getEarningRate());

        pstmt.executeUpdate();
    }

    public LottoResultDTO selectLottoResultByRound(int round) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(selectQuery);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        return (rs.next()) ? getLottoResultDTO(rs, 1) : new LottoResultDTO();
    }

    static LottoResultDTO getLottoResultDTO(ResultSet rs, int index) throws SQLException {
        LottoResultDTO lottoResultDTO = new LottoResultDTO();

        lottoResultDTO.setRankCounts(getRankCounts(rs, index));
        index += LottoRank.values().length;
        lottoResultDTO.setWinningReward(rs.getBigDecimal(index++));
        lottoResultDTO.setEarningRate(rs.getBigDecimal(index));

        return lottoResultDTO;
    }

    static private List<RankCount> getRankCounts(ResultSet rs, int index) throws SQLException {
        List<RankCount> rankCounts = new ArrayList<>();
        for (LottoRank rank : LottoRank.values()) {
            rankCounts.add(new RankCount(rank, rs.getInt(index++)));
        }

        return Collections.unmodifiableList(
                rankCounts.stream()
                .sorted(Comparator.comparingInt(rankCount -> rankCount.getRank().getReward()))
                .collect(Collectors.toList())
        );
    }
}