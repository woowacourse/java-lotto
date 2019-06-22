package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.UserLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoDao {
    private static LottoDao lottoDao;

    public static LottoDao getInstance() {
        if (Objects.isNull(lottoDao)) {
            lottoDao = new LottoDao();
        }
        return lottoDao;
    }

    public void addLotto(UserLotto userLotto, int round) {
        String query = "INSERT INTO userLotto (lottoNumbers, round) VALUES (?,?)";

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        for (Lotto lotto : userLotto.getUserLotto()) {
            List<Object> queryValues = new ArrayList<>();

            queryValues.add(lotto.toString());
            queryValues.add(round);
            jdbcTemplate.executeUpdate(query, queryValues);
        }
    }

    public int offerMaxRound() {
        String query = "SELECT MAX(round) FROM userLotto";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query);

        if (Objects.isNull(results.get(0).get("MAX(round)"))) {
            return 0;
        }

        Map<String, Object> result = results.get(0);
        return (int) result.get("MAX(round)");
    }

    public List<String> offerUserLottoNumber(int lottoRound) {
        String query = "SELECT lottoNumbers FROM userLotto WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(lottoRound);

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        if (Objects.isNull(results)) {
            return null;
        }

        return results.stream().map(map -> (String) map.get("lottoNumbers")).collect(Collectors.toList());
    }
}
