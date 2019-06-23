package lotto.persistence;

import java.util.*;

public class RoundDAOImpl implements RoundDAO {
    private static JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

    public static RoundDAOImpl getInstance() {
        return new RoundDAOImpl();
    }

    public void addRound(int prize, double interestRate) {
        String query = "INSERT INTO round (prize, interest_rate) VALUES(?, ?)";
        List<String> args = Arrays.asList(
                String.valueOf(prize),
                String.valueOf(interestRate)
        );
        jdbcTemplate.updateQuery(query, args);
    }

    public int getPrizeOfId(int id) {
        String query = "SELECT prize FROM round WHERE id=?";
        List<String> arg = new ArrayList<>(Collections.singletonList(String.valueOf(id)));
        Map<String, String> result = jdbcTemplate.selectQuery(query, arg).get(0);
        return Integer.valueOf(result.get("prize"));
    }

    public double getInterestRateOfId(int id) {
        String query = "SELECT interest_rate FROM round WHERE id=?";
        List<String> arg = new ArrayList<>(Collections.singletonList(String.valueOf(id)));
        Map<String, String> result = jdbcTemplate.selectQuery(query, arg).get(0);
        return Double.valueOf(result.get("interest_rate"));
    }

    public int getLatestRoundId() {
        String query = "SELECT MAX(id) AS ThisId FROM round";
        Map<String, String> result = jdbcTemplate.selectQuery(query, null).get(0);
        return Integer.valueOf(result.get("ThisId"));
    }

    public List<Integer> getAllIds() {
        String query = "SELECT id FROM round";
        List<Map<String, String>> result = jdbcTemplate.selectQuery(query, null);
        List<Integer> ids = new ArrayList<>();
        for (Map<String, String> map : result) {
            ids.add(Integer.valueOf(map.get("id")));
        }
        return ids;
    }

    public void removeRoundById(int roundId) {
        String query = "DELETE FROM round WHERE id=?";
        List<String> arg = new ArrayList<>(Collections.singletonList(String.valueOf(roundId)));
        jdbcTemplate.updateQuery(query, arg);
    }
}
