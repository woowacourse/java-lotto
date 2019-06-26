package lotto.dao;

import lottogame.domain.Rank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LottoResultDao {
    private static LottoResultDao lottoResultDAO;

    private LottoResultDao() {
    }

    public static LottoResultDao getInstance() {
        if (lottoResultDAO == null) {
            lottoResultDAO = new LottoResultDao();
        }
        return lottoResultDAO;
    }

    public int findPresentRound() {
        SelectJdbcTemplate<Integer> template = new SelectJdbcTemplate<Integer>() {
            @Override
            public Integer getResult(ResultSet resultSet) throws SQLException {
                int round = 0;
                while (resultSet.next()) {
                    round = resultSet.getInt("round");
                }
                return round;
            }
        };
        String query = "select MAX(round) as round from lottoresult";
        try {
            return template.executeQuery(query, new ArrayList<>());
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public void updateLottoResult(int round, Map<Rank, Integer> result) {
        UpdateJdbcTemplate template = new UpdateJdbcTemplate();
        PreparedStatement pstmt = null;
        String query = "update lottoresult set \n" +
                "    first_matcher = ?," +
                "    second_matcher = ?," +
                "    third_matcher = ?," +
                "    fourth_matcher = ?," +
                "    fifth_matcher = ? " +
                "    where round = ?";
        List<Integer> parameters = Arrays.asList(result.get(Rank.FIRST),
                result.get(Rank.SECOND),
                result.get(Rank.THIRD),
                result.get(Rank.FOURTH),
                result.get(Rank.FIFTH),
                round);
        try {
            template.updateQuery(query,parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewLottoRound() {
        UpdateJdbcTemplate template = new UpdateJdbcTemplate();
        String query = "insert into lottoresult value();";

        try {
            template.updateQuery(query,new ArrayList<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> findAllRound(int round) {
        SelectJdbcTemplate<List<Integer>> template = new SelectJdbcTemplate<List<Integer>>() {
            @Override
            public List<Integer> getResult(ResultSet resultSet) throws SQLException {
                List<Integer> rounds = new ArrayList<>();
                while (resultSet.next()) {
                    rounds.add(resultSet.getInt(1));
                }
                return rounds;
            }
        };
        String query = "select round from lottoresult where round!=?";
        List<Integer> parameters = new ArrayList<>();
        parameters.add(round);
        try {
            return template.executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public Map<Rank, Integer> findLottoResultByRound(int round) {
        SelectJdbcTemplate<Map<Rank, Integer>> template = new SelectJdbcTemplate<Map<Rank, Integer>>() {
            @Override
            public Map<Rank, Integer> getResult(ResultSet resultSet) throws SQLException {
                Map<Rank, Integer> result = new HashMap<>();
                while (resultSet.next()) {
                    result.put(Rank.FIRST, resultSet.getInt(2));
                    result.put(Rank.SECOND, resultSet.getInt(3));
                    result.put(Rank.THIRD, resultSet.getInt(4));
                    result.put(Rank.FOURTH, resultSet.getInt(5));
                    result.put(Rank.FIFTH, resultSet.getInt(6));
                }
                return result;
            }
        };
        String query = "select * from lottoresult where round = ?";
        List<Integer> parameters = Arrays.asList(round);
        try {
            return template.executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
