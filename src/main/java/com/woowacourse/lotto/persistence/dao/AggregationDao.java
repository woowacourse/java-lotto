package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.persistence.dto.AggregationDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AggregationDao {
    private final Connection conn;

    public AggregationDao(Connection conn) {
        this.conn = conn;
    }

    public long addAggregation(AggregationDto aggregation, long winningLottoId, List<Long> lottoIds) throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.INSERT_AGGREGATION, Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, aggregation.getLottoRound());
        query.setInt(2, aggregation.getCntFirst());
        query.setInt(3, aggregation.getCntSecond());
        query.setInt(4, aggregation.getCntThird());
        query.setInt(5, aggregation.getCntFourth());
        query.setInt(6, aggregation.getCntFifth());
        query.setInt(7, aggregation.getCntNone());
        query.setLong(8, aggregation.getPrizeMoneySum());

        if (query.executeUpdate() == 0) {
            throw new SQLException("No aggregation is inserted");
        }

        try (ResultSet generated = query.getGeneratedKeys()) {
            generated.next();
            long aggregationId = generated.getLong(1);
            addAggregatedLottos(aggregationId, lottoIds);
            addAggregatedWinningLotto(aggregationId, winningLottoId);
            return aggregationId;
        }
    }

    private void addAggregatedLottos(long aggregationId, List<Long> lottoIds) throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.INSERT_AGGREGATED_LOTTO);
        query.setLong(2, aggregationId);
        for (long id : lottoIds) {
            query.setLong(1, id);
            query.setLong(2, aggregationId);
            query.executeUpdate();
        }
    }

    private void addAggregatedWinningLotto(long aggregationId, long winningLottoId) throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.INSERT_AGGREGATED_WINNING_LOTTO);
        query.setLong(1, winningLottoId);
        query.setLong(2, aggregationId);
        query.executeUpdate();
    }

    public Optional<AggregationDto> findById(long id) throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.SELECT_BY_ID);
        query.setLong(1, id);

        try (ResultSet rs = query.executeQuery()) {
            return checkAndMapAggregationResult(rs);
        }
    }

    public List<AggregationDto> find(int topN) throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.SELECT_LATEST_N_ROUND);
        query.setInt(1, topN);
        try (ResultSet result = query.executeQuery()) {
            return mapAggregationResults(result);
        }
    }


    private List<AggregationDto> mapAggregationResults(ResultSet rs) throws SQLException {
        List<AggregationDto> results = new ArrayList<>();

        while (rs.next()) {
            mapAggregationResult(rs).ifPresent(results::add);
        }
        return results;
    }

    public int findLatestRound() throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.SELECT_MAX_ROUND);
        try (ResultSet result = query.executeQuery()) {
            if (!result.next()) {
                return 0;
            }
            return result.getInt(1);
        }
    }

    private Optional<AggregationDto> checkAndMapAggregationResult(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return Optional.empty();
        }
        return mapAggregationResult(rs);
    }

    private Optional<AggregationDto> mapAggregationResult(ResultSet rs) throws SQLException {
        AggregationDto aggregation = new AggregationDto();
        aggregation.setId(rs.getLong("id"));
        aggregation.setLottoRound(rs.getInt("lotto_round"));
        aggregation.setCntFirst(rs.getInt("cnt_first"));
        aggregation.setCntSecond(rs.getInt("cnt_second"));
        aggregation.setCntThird(rs.getInt("cnt_third"));
        aggregation.setCntFourth(rs.getInt("cnt_fourth"));
        aggregation.setCntFifth(rs.getInt("cnt_fifth"));
        aggregation.setCntNone(rs.getInt("cnt_none"));
        aggregation.setPrizeMoneySum(rs.getLong("prize_money_sum"));
        aggregation.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
        return Optional.of(aggregation);
    }

    public int deleteById(long id) throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.DELETE_BY_ID);
        query.setLong(1, id);
        return query.executeUpdate();
    }

    private static class AggregationDaoSql {
        private static final String INSERT_AGGREGATION = "INSERT INTO aggregation(lotto_round, cnt_first, cnt_second, cnt_third, cnt_fourth, cnt_fifth, cnt_none, prize_money_sum) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        private static final String INSERT_AGGREGATED_LOTTO = "INSERT INTO lotto_aggregated(lotto_id, aggregation_id) VALUES(?, ?)";
        private static final String INSERT_AGGREGATED_WINNING_LOTTO = "INSERT INTO winning_lotto_aggregated(winning_lotto_id, aggregation_id) VALUES(?, ?)";
        private static final String SELECT_BY_ID = "SELECT id, lotto_round, cnt_first, cnt_second, cnt_third, cnt_fourth, cnt_fifth, cnt_none, prize_money_sum, reg_date " +
            "FROM aggregation WHERE id=?";
        private static final String SELECT_LATEST_N_ROUND = "SELECT * FROM aggregation ORDER BY lotto_round DESC LIMIT ?";
        private static final String SELECT_MAX_ROUND = "SELECT MAX(lotto_round) FROM aggregation";
        private static final String DELETE_BY_ID = "DELETE FROM aggregation WHERE id=?";
    }
}
