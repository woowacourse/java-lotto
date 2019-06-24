package lotto.persistence;

import lotto.domain.Rank;

import java.util.*;

public class ResultDAOImpl implements ResultDAO {
    private static JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

    public static ResultDAOImpl getInstance() {
        return new ResultDAOImpl();
    }

    public void addResult(int roundId, Map<Rank, Integer> result) {
        String query = "INSERT INTO result (ro_id, miss, fifth, fourth, third, second, first) VALUES (?, ?, ?, ?, ?, ?, ?)";
        List<String> args = new ArrayList<>(Arrays.asList(
                String.valueOf(roundId),
                String.valueOf(result.get(Rank.MISS)),
                String.valueOf(result.get(Rank.FIFTH)),
                String.valueOf(result.get(Rank.FOURTH)),
                String.valueOf(result.get(Rank.THIRD)),
                String.valueOf(result.get(Rank.SECOND)),
                String.valueOf(result.get(Rank.FIRST))
        ));
        jdbcTemplate.updateQuery(query, args);
    }

    public Map<Rank, Integer> findResultByRoundId(int roundId) {
        String query = "SELECT * FROM result WHERE ro_id=?";
        List<String> arg = new ArrayList<>(Collections.singletonList(String.valueOf(roundId)));
        Map<String, String> result = jdbcTemplate.selectQuery(query, arg).get(0);

        Map<Rank, Integer> stat = new HashMap<>();
        stat.put(Rank.MISS, Integer.valueOf(result.get("miss")));
        stat.put(Rank.FIFTH, Integer.valueOf(result.get("fifth")));
        stat.put(Rank.FOURTH, Integer.valueOf(result.get("fourth")));
        stat.put(Rank.THIRD, Integer.valueOf(result.get("third")));
        stat.put(Rank.SECOND, Integer.valueOf(result.get("second")));
        stat.put(Rank.FIRST, Integer.valueOf(result.get("first")));
        return stat;
    }

    public void removeResult(int roundId) {
        String query = "DELETE FROM result WHERE ro_id=?";
        List<String> arg = new ArrayList<>(Collections.singletonList(String.valueOf(roundId)));
        jdbcTemplate.updateQuery(query, arg);
    }
}
