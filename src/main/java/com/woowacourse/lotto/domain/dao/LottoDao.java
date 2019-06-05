package com.woowacourse.lotto.domain.dao;

import com.woowacourse.lotto.domain.dto.LottoDto;

import java.sql.*;
import java.util.Optional;

public class LottoDao {
    private final Connection conn;
    private static final String[] id = {"id"};

    public LottoDao(Connection conn) {
        this.conn = conn;
    }

    public long addLotto(LottoDto lotto) throws SQLException {
        PreparedStatement query = conn.prepareStatement(LottoDaoSql.INSERT_LOTTO, Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, lotto.getNumber0());
        query.setInt(2, lotto.getNumber1());
        query.setInt(3, lotto.getNumber2());
        query.setInt(4, lotto.getNumber3());
        query.setInt(5, lotto.getNumber4());
        query.setInt(6, lotto.getNumber5());
        query.setInt(7, lotto.getPrice());
        if (query.executeUpdate() == 0) {
            throw new SQLException("No lotto is inserted");
        }
        try (ResultSet generated = query.getGeneratedKeys()) {
            generated.next();
            return generated.getLong(1);
        }
    }

    public Optional<LottoDto> findById(long id) throws SQLException {
        PreparedStatement query = conn.prepareStatement(LottoDaoSql.SELECT_LOTTO_BY_ID);
        query.setLong(1, id);
        try (ResultSet rs = query.executeQuery()) {
            return mapResult(rs);
        }
    }

    public int deleteById(long id) throws SQLException {
        PreparedStatement query = conn.prepareStatement(LottoDaoSql.DELETE_BY_ID);
        query.setLong(1, id);
        return query.executeUpdate();
    }

    private Optional<LottoDto> mapResult(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return Optional.empty();
        }
        LottoDto found = new LottoDto();
        found.setId(rs.getLong("id"));
        found.setNumber0(rs.getInt("number_0"));
        found.setNumber1(rs.getInt("number_1"));
        found.setNumber2(rs.getInt("number_2"));
        found.setNumber3(rs.getInt("number_3"));
        found.setNumber4(rs.getInt("number_4"));
        found.setNumber5(rs.getInt("number_5"));
        found.setPrice(rs.getInt("price"));
        found.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
        return Optional.of(found);
    }


    private static class LottoDaoSql {
        private static final String INSERT_LOTTO = "INSERT INTO lotto(number_0, number_1, number_2, number_3, number_4, number_5, price) VALUES(?, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_LOTTO_BY_ID = "SELECT id, number_0, number_1, number_2, number_3, number_4, number_5, price, reg_date FROM lotto WHERE id=?";
        private static final String DELETE_BY_ID = "DELETE FROM lotto WHERE id=?";
    }
}
