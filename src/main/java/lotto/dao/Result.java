package lotto.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Result implements ResultDao {
    private static Result result;

    public static Result getInstance() {
        if (Objects.isNull(result)) {
            result = new Result();
        }
        return result;
    }

    public void addResult(List<String> results, int lottoRound) {
        String query = "INSERT INTO results (result, round) VALUES (?,?)";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        for (String result : results) {
            List<Object> queryValues = new ArrayList<>();

            queryValues.add(result);
            queryValues.add(lottoRound);
            jdbcTemplate.executeUpdate(query, queryValues);
        }
    }

    public List<String> offerResults(int lottoRound) {
        String query = "SELECT result FROM results WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(lottoRound);

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        if (results.size() == 0) {
            return null;
        }

        return results.stream().map(map -> (String) map.get("result")).collect(Collectors.toList());
    }
}
