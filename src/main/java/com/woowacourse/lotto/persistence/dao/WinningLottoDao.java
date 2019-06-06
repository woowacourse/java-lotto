package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.persistence.dto.WinningLottoDto;

import java.sql.*;
import java.util.Optional;

public class WinningLottoDao {
    private Connection conn;

    public WinningLottoDao(Connection conn) {
        this.conn = conn;
    }

    public long addWinningLotto(WinningLottoDto winningLotto) throws SQLException {
        PreparedStatement query = conn.prepareStatement(WinningLottoDaoSql.INSERT, Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, winningLotto.getWinningNumber0());
        query.setInt(2, winningLotto.getWinningNumber1());
        query.setInt(3, winningLotto.getWinningNumber2());
        query.setInt(4, winningLotto.getWinningNumber3());
        query.setInt(5, winningLotto.getWinningNumber4());
        query.setInt(6, winningLotto.getWinningNumber5());
        query.setInt(7, winningLotto.getWinningBonusNumber());

        if (query.executeUpdate() == 0) {
            throw new SQLException("No winning lotto is inserted");
        }

        try (ResultSet generated = query.getGeneratedKeys()) {
            generated.next();
            return generated.getLong(1);
        }
    }

    public Optional<WinningLottoDto> findById(long id) throws SQLException{
        PreparedStatement query = conn.prepareStatement(WinningLottoDaoSql.SELECT_BY_ID);
        query.setLong(1, id);
        try (ResultSet rs = query.executeQuery()) {
            return mapResult(rs);
        }
    }

    private Optional<WinningLottoDto> mapResult(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return Optional.empty();
        }
        WinningLottoDto found = new WinningLottoDto();
        found.setId(rs.getLong("id"));
        found.setWinningNumber0(rs.getInt("winning_number_0"));
        found.setWinningNumber1(rs.getInt("winning_number_1"));
        found.setWinningNumber2(rs.getInt("winning_number_2"));
        found.setWinningNumber3(rs.getInt("winning_number_3"));
        found.setWinningNumber4(rs.getInt("winning_number_4"));
        found.setWinningNumber5(rs.getInt("winning_number_5"));
        found.setWinningBonusNumber(rs.getInt("winning_number_bonus"));
        found.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
        return Optional.of(found);
    }

    public int deleteById(long id) throws SQLException{
        PreparedStatement query = conn.prepareStatement(WinningLottoDaoSql.DELETE_BY_ID);
        query.setLong(1, id);
        return query.executeUpdate();
    }

    private static class WinningLottoDaoSql {
        private static final String INSERT = "INSERT INTO winning_lotto(winning_number_0, winning_number_1, winning_number_2, winning_number_3, winning_number_4, winning_number_5, winning_number_bonus) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_BY_ID = "SELECT id, winning_number_0, winning_number_1, winning_number_2, winning_number_3, winning_number_4, winning_number_5, winning_number_bonus, reg_date " +
            "FROM winning_lotto WHERE id=?";
        private static final String DELETE_BY_ID = "DELETE FROM winning_lotto WHERE id=?";
    }
}
