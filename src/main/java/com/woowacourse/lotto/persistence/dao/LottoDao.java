package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.persistence.dto.LottoDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LottoDao {
    private static LottoDao instance;

    private final DataSource dataSource;

    public static LottoDao getInstance(DataSource ds) {
        if (instance == null) {
            instance = new LottoDao(ds);
        }
        return instance;
    }

    private LottoDao(DataSource ds) {
        this.dataSource = ds;
    }

    public long addLotto(LottoDto lotto) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(LottoDaoSql.INSERT_LOTTO, Statement.RETURN_GENERATED_KEYS);) {
            query.setInt(1, lotto.getNumber0());
            query.setInt(2, lotto.getNumber1());
            query.setInt(3, lotto.getNumber2());
            query.setInt(4, lotto.getNumber3());
            query.setInt(5, lotto.getNumber4());
            query.setInt(6, lotto.getNumber5());
            query.setInt(7, lotto.getPrice());
            query.executeUpdate();
            return getGeneratedId(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getGeneratedId(PreparedStatement query) throws SQLException {
        try (ResultSet generated = query.getGeneratedKeys()) {
            generated.next();
            return generated.getLong(1);
        }
    }

    public Optional<LottoDto> findById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(LottoDaoSql.SELECT_LOTTO_BY_ID)) {
            query.setLong(1, id);
            return executeAndGetFoundLotto(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<LottoDto> executeAndGetFoundLotto(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.executeQuery()) {
            return checkAndMapResult(rs);
        }
    }

    public List<LottoDto> findByAggregationId(long aggregationId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(LottoDaoSql.SELECT_BY_AGGREGATION_ID)) {
            query.setLong(1, aggregationId);

            return executeAndGetFoundLottos(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<LottoDto> executeAndGetFoundLottos(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.executeQuery()) {
            return mapResults(rs);
        }
    }

    public int deleteById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(LottoDaoSql.DELETE_BY_ID)) {
            query.setLong(1, id);
            return query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<LottoDto> mapResults(ResultSet rs) throws SQLException {
        List<LottoDto> lottos = new ArrayList<>();
        while (rs.next()) {
            lottos.add(mapResult(rs));
        }

        return lottos;
    }

    private Optional<LottoDto> checkAndMapResult(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return Optional.empty();
        }
        return Optional.of(mapResult(rs));
    }

    private LottoDto mapResult(ResultSet rs) throws SQLException {
        return LottoDto.of(
            rs.getLong("id"),
            rs.getInt("number_0"),
            rs.getInt("number_1"),
            rs.getInt("number_2"),
            rs.getInt("number_3"),
            rs.getInt("number_4"),
            rs.getInt("number_5"),
            rs.getInt("price"),
            rs.getTimestamp("reg_date").toLocalDateTime()
        );
    }


    private static class LottoDaoSql {
        private static final String INSERT_LOTTO = "INSERT INTO lotto(number_0, number_1, number_2, number_3, number_4, number_5, price) VALUES(?, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_LOTTO_BY_ID = "SELECT id, number_0, number_1, number_2, number_3, number_4, number_5, price, reg_date FROM lotto WHERE id=?";
        private static final String SELECT_BY_AGGREGATION_ID = "SELECT l.id, l.number_0, l.number_1, l.number_2, l.number_3, l.number_4, l.number_5, l.price, l.reg_date FROM lotto as l\n" +
            "\tJOIN lotto_aggregated as lg\n" +
            "\tON lg.lotto_id = l.id\n" +
            "\tJOIN aggregation AS agg\n" +
            "\tON lg.aggregation_id = agg.id AND agg.id=?";
        private static final String DELETE_BY_ID = "DELETE FROM lotto WHERE id=?";
    }
}
