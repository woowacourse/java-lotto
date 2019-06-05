package com.woowacourse.lotto.domain.dao;

import com.woowacourse.lotto.domain.dto.AggregationDto;

import java.sql.*;
import java.util.Optional;

public class AggregationDao {
    private final Connection conn;

    public AggregationDao(Connection conn) {
        this.conn = conn;
    }

    public long addAggregation(AggregationDto aggregation) throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.INSERT, Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, aggregation.getCntFirst());
        query.setInt(2, aggregation.getCntSecond());
        query.setInt(3, aggregation.getCntThird());
        query.setInt(4, aggregation.getCntFourth());
        query.setInt(5, aggregation.getCntFifth());
        query.setInt(6, aggregation.getCntNone());
        query.setLong(7, aggregation.getPrizeMoneySum());

        if (query.executeUpdate() == 0) {
            throw new SQLException("No aggregation is inserted");
        }

        try (ResultSet generated = query.getGeneratedKeys()) {
            generated.next();
            return generated.getLong(1);
        }
    }

    public Optional<AggregationDto> findById(long id) throws SQLException {
        PreparedStatement query = conn.prepareStatement(AggregationDaoSql.SELECT_BY_ID);
        query.setLong(1, id);

        try(ResultSet rs = query.executeQuery()) {
            return mapResult(rs);
        }
    }

    private Optional<AggregationDto> mapResult(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return Optional.empty();
        }

        AggregationDto aggregation = new AggregationDto();
        aggregation.setId(rs.getLong("id"));
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
        private static final String INSERT = "INSERT INTO aggregation(cnt_first, cnt_second, cnt_third, cnt_fourth, cnt_fifth, cnt_none, prize_money_sum) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_BY_ID = "SELECT id, cnt_first, cnt_second, cnt_third, cnt_fourth, cnt_fifth, cnt_none, prize_money_sum, reg_date " +
            "FROM aggregation WHERE id=?";
        private static final String DELETE_BY_ID = "DELETE FROM aggregation WHERE id=?";
    }
}
