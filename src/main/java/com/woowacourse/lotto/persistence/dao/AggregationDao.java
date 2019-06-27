package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.persistence.dto.AggregationDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AggregationDao {
    private static AggregationDao instance;

    private final DataSource dataSource;

    public static AggregationDao getInstance(DataSource ds) {
        if (instance == null) {
            instance = new AggregationDao(ds);
        }
        return instance;
    }

    private AggregationDao(DataSource ds) {
        this.dataSource = ds;
    }

    public long addAggregation(AggregationDto aggregation, long winningLottoId, List<Long> lottoIds) {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            // 1. agg 엔티티 추가
            long aggId = addAggregationEntity(aggregation, conn);
            // 2. winning lotto ref. 엔티티 추가
            addAggregatedLottos(aggId, lottoIds, conn);
            // 3. lotto ref. 엔티티 추가
            addAggregatedWinningLotto(aggId, winningLottoId, conn);
            conn.commit();
            return aggId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long addAggregationEntity(AggregationDto aggregation, Connection conn) throws SQLException {
        try (PreparedStatement query = conn.prepareStatement(AggregationDaoSql.INSERT_AGGREGATION, Statement.RETURN_GENERATED_KEYS)) {
            query.setInt(1, aggregation.getLottoRound());
            query.setInt(2, aggregation.getCntFirst());
            query.setInt(3, aggregation.getCntSecond());
            query.setInt(4, aggregation.getCntThird());
            query.setInt(5, aggregation.getCntFourth());
            query.setInt(6, aggregation.getCntFifth());
            query.setInt(7, aggregation.getCntNone());
            query.setLong(8, aggregation.getPrizeMoneySum());
            assertInserted(query.executeUpdate());
            return getGeneratedId(query);
        }
    }

    private void assertInserted(int updated) {
        if (updated == 0) {
            throw new IllegalStateException("Aggregation is not inserted");
        }
    }

    private long getGeneratedId(PreparedStatement query) throws SQLException {
        try (ResultSet generated = query.getGeneratedKeys()) {
            generated.next();
            return generated.getLong(1);
        }
    }

    private void addAggregatedLottos(long aggregationId, List<Long> lottoIds, Connection conn) throws SQLException {
        try (PreparedStatement query = conn.prepareStatement(AggregationDaoSql.INSERT_AGGREGATED_LOTTO)) {
            query.setLong(2, aggregationId);
            executeAddAggregatedLottosForEachLottoId(aggregationId, lottoIds, query);
        }
    }

    private void executeAddAggregatedLottosForEachLottoId(long aggregationId, List<Long> lottoIds, PreparedStatement query) throws SQLException {
        for (long id : lottoIds) {
            query.setLong(1, id);
            query.setLong(2, aggregationId);
            query.executeUpdate();
        }
    }

    private void addAggregatedWinningLotto(long aggregationId, long winningLottoId, Connection conn) throws SQLException {
        try (PreparedStatement query = conn.prepareStatement(AggregationDaoSql.INSERT_AGGREGATED_WINNING_LOTTO)) {
            query.setLong(1, winningLottoId);
            query.setLong(2, aggregationId);
            query.executeUpdate();
        }
    }

    public Optional<AggregationDto> findById(long id) {
        try (Connection conn = dataSource.getConnection()) {
            return handleFindByIdQuery(id, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<AggregationDto> handleFindByIdQuery(long id, Connection conn) throws SQLException {
        try (PreparedStatement query = conn.prepareStatement(AggregationDaoSql.SELECT_BY_ID)) {
            query.setLong(1, id);

            return executeAndGetFoundAggregation(query);
        }
    }

    private Optional<AggregationDto> executeAndGetFoundAggregation(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.executeQuery()) {
            return checkAndMapAggregationResult(rs);
        }
    }

    public List<AggregationDto> findLatestN(int limit) {
        try (Connection conn = dataSource.getConnection()) {
            return executeFindLatestNResultsQuery(limit, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<AggregationDto> executeFindLatestNResultsQuery(int limit, Connection conn) throws SQLException {
        try (PreparedStatement query = conn.prepareStatement(AggregationDaoSql.SELECT_LATEST_N_ROUND)) {
            query.setInt(1, limit);
            return executeAndGetFoundAggregations(query);
        }
    }

    private List<AggregationDto> executeAndGetFoundAggregations(PreparedStatement query) throws SQLException {
        try (ResultSet result = query.executeQuery()) {
            return mapAggregationResults(result);
        }
    }


    private List<AggregationDto> mapAggregationResults(ResultSet rs) throws SQLException {
        List<AggregationDto> results = new ArrayList<>();

        while (rs.next()) {
            results.add(mapAggregationResult(rs));
        }
        return results;
    }

    public int findLatestRound() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(AggregationDaoSql.SELECT_MAX_ROUND)) {
            return executeAndGetLatestRound(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int executeAndGetLatestRound(PreparedStatement query) throws SQLException {
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
        return Optional.of(mapAggregationResult(rs));
    }

    private AggregationDto mapAggregationResult(ResultSet rs) throws SQLException {
        return AggregationDto.of(
            rs.getLong("id"),
            rs.getInt("lotto_round"),
            rs.getInt("cnt_first"),
            rs.getInt("cnt_second"),
            rs.getInt("cnt_third"),
            rs.getInt("cnt_fourth"),
            rs.getInt("cnt_fifth"),
            rs.getInt("cnt_none"),
            rs.getLong("prize_money_sum"),
            null, null,
            rs.getTimestamp("reg_date").toLocalDateTime()
        );
    }

    public int deleteById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(AggregationDaoSql.DELETE_BY_ID)) {
            query.setLong(1, id);
            return query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
