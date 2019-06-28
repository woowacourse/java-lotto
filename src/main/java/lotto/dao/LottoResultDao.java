package lotto.dao;

import lotto.domain.lottoresult.LottoRank;
import lotto.domain.lottoresult.RankCount;
import lotto.dto.LottoResultDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoResultDao {
    private static final String insertQuery = "INSERT INTO results (round, fail_rank, fifth_rank, fourth_rank, third_rank, second_rank, first_rank, winning_reward, earning_rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String selectQuery = "SELECT first_rank, second_rank, third_rank, fourth_rank, fifth_rank, fail_rank, winning_reward, earning_rate FROM results WHERE round=?";

    private static LottoResultDao instance;

    private JdbcTemplate jdbcTemplate;

    private LottoResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static LottoResultDao getInstance(JdbcTemplate jdbcTemplate) {
        if (instance == null) {
            instance = new LottoResultDao(jdbcTemplate);
        }

        if (!instance.jdbcTemplate.equals(jdbcTemplate)) {
            instance.jdbcTemplate = jdbcTemplate;
        }
        return instance;
    }

    public void insertResult(int round, LottoResultDTO lottoResultDTO) throws SQLException {

        List<Object> parameters = new ArrayList<>();

        parameters.add(round);
        for (RankCount rankCount : lottoResultDTO.getRankCounts()) {
            parameters.add(rankCount.getCount());
        }

        parameters.add(lottoResultDTO.getWinningReward());
        parameters.add(lottoResultDTO.getEarningRate());

        jdbcTemplate.executeUpdate(insertQuery, parameters);
    }

    public LottoResultDTO selectLottoResultByRound(int round) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(round);

        ResultSet rs = jdbcTemplate.executeQuery(selectQuery, parameters);

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