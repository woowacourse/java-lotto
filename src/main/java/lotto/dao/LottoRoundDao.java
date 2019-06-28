package lotto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoRoundDao {
    private static final String insertQuery = "INSERT INTO lotto_rounds VALUES (?)";
    private static final String selectQuery = "SELECT * FROM lotto_rounds WHERE round>0";
    private static final String selectMaxQuery = "SELECT MAX(round) FROM lotto_rounds";

    private static LottoRoundDao instance;

    private JdbcTemplate jdbcTemplate;

    private LottoRoundDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static LottoRoundDao getInstance(JdbcTemplate jdbcTemplate) {
        if (instance == null) {
            instance = new LottoRoundDao(jdbcTemplate);
        }

        if (!instance.jdbcTemplate.equals(jdbcTemplate)) {
            instance.jdbcTemplate = jdbcTemplate;
        }
        return instance;
    }

    public void insertRound(int round) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(round);

        jdbcTemplate.executeUpdate(insertQuery, parameters);
    }

    public List<Integer> selectLottoRounds() throws SQLException {
        ResultSet rs = jdbcTemplate.executeQuery(selectQuery, new ArrayList());

        List<Integer> lottoRounds = new ArrayList<>();
        while (rs.next()) {
            lottoRounds.add(rs.getInt("round"));
        }

        return lottoRounds;
    }

    public int selectMaxRound() throws SQLException {
        ResultSet rs = jdbcTemplate.executeQuery(selectMaxQuery, new ArrayList());

        return (rs.next()) ? rs.getInt("MAX(round)") : 0;
    }
}