package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.persistence.dto.WinningLottoDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class WinningLottoDao {
    private static WinningLottoDao instance;

    private DataSource dataSource;

    public static WinningLottoDao getInstance(DataSource ds) {
        if (instance == null) {
            instance = new WinningLottoDao(ds);
        }
        return instance;
    }

    private WinningLottoDao(DataSource ds) {
        this.dataSource = ds;
    }

    public long addWinningLotto(WinningLottoDto winningLotto) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return executeAddWinningLottoQuery(winningLotto, conn);
        }
    }

    private long executeAddWinningLottoQuery(WinningLottoDto winningLotto, Connection conn) throws SQLException {
        try (PreparedStatement query = conn.prepareStatement(WinningLottoDaoSql.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            query.setInt(1, winningLotto.getWinningNumber0());
            query.setInt(2, winningLotto.getWinningNumber1());
            query.setInt(3, winningLotto.getWinningNumber2());
            query.setInt(4, winningLotto.getWinningNumber3());
            query.setInt(5, winningLotto.getWinningNumber4());
            query.setInt(6, winningLotto.getWinningNumber5());
            query.setInt(7, winningLotto.getWinningBonusNumber());
            query.executeUpdate();
            return getGeneratedId(query);
        }
    }

    private long getGeneratedId(PreparedStatement query) throws SQLException {
        try (ResultSet generated = query.getGeneratedKeys()) {
            generated.next();
            return generated.getLong(1);
        }
    }

    public Optional<WinningLottoDto> findById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement query = conn.prepareStatement(WinningLottoDaoSql.SELECT_BY_ID);
            query.setLong(1, id);
            return executeAndGetFoundWinningLottoResult(query);
        }
    }

    private Optional<WinningLottoDto> executeAndGetFoundWinningLottoResult(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.executeQuery()) {
            return checkAndMapResult(rs);
        }
    }

    private Optional<WinningLottoDto> checkAndMapResult(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return Optional.empty();
        }
        return Optional.of(mapResult(rs));
    }

    public Optional<WinningLottoDto> findByAggregationId(long aggregationId) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement query = conn.prepareStatement(WinningLottoDaoSql.SELECT_BY_AGGREGATION_ID);
            query.setLong(1, aggregationId);

            return executeAndGetFoundWinningLottoResult(query);
        }
    }

    private WinningLottoDto mapResult(ResultSet rs) throws SQLException {
        return WinningLottoDto.of(
            rs.getLong("id"),
            rs.getInt("winning_number_0"),
            rs.getInt("winning_number_1"),
            rs.getInt("winning_number_2"),
            rs.getInt("winning_number_3"),
            rs.getInt("winning_number_4"),
            rs.getInt("winning_number_5"),
            rs.getInt("winning_number_bonus"),
            rs.getTimestamp("reg_date").toLocalDateTime()
        );
    }

    public int deleteById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement query = conn.prepareStatement(WinningLottoDaoSql.DELETE_BY_ID);
            query.setLong(1, id);
            return query.executeUpdate();
        }
    }

    private static class WinningLottoDaoSql {
        private static final String INSERT = "INSERT INTO winning_lotto(winning_number_0, winning_number_1, winning_number_2, winning_number_3, winning_number_4, winning_number_5, winning_number_bonus) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_BY_ID = "SELECT id, winning_number_0, winning_number_1, winning_number_2, winning_number_3, winning_number_4, winning_number_5, winning_number_bonus, reg_date " +
            "FROM winning_lotto WHERE id=?";
        private static final String SELECT_BY_AGGREGATION_ID = "SELECT w.id, w.winning_number_0, w.winning_number_1, w.winning_number_2, w.winning_number_3, w.winning_number_4, w.winning_number_5, w.winning_number_bonus, w.reg_date FROM winning_lotto AS w\n" +
            "\tJOIN winning_lotto_aggregated AS lg\n" +
            "\tON lg.winning_lotto_id = w.id\n" +
            "\tJOIN aggregation AS agg\n" +
            "\tON lg.aggregation_id = agg.id AND agg.id = ?";
        private static final String DELETE_BY_ID = "DELETE FROM winning_lotto WHERE id=?";
    }
}
