package lotto.dao;

import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WinningLottoDao {
    private static WinningLottoDao winningLottoDao;

    public static WinningLottoDao getInstance() {
        if (Objects.isNull(winningLottoDao)) {
            winningLottoDao = new WinningLottoDao();
        }
        return winningLottoDao;
    }

    public void addWinningLotto(WinningLotto winningLotto, int lottoRound) {
        String query = "INSERT INTO winningLotto VALUES (?,?,?)";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();

        queryValues.add(lottoRound);
        queryValues.add(winningLotto.toString());
        queryValues.add(winningLotto.getBonus());
        jdbcTemplate.executeUpdate(query, queryValues);
    }

    public String offerWinningNumber(int lottoRound) {
        String query = "SELECT winningLottoNumber FROM winningLotto WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(lottoRound);

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        if (Objects.isNull(results)) {
            return null;
        }

        Map<String, Object> result = results.get(0);
        return (String) result.get("winningLottoNumber");
    }

    public int offerBonusNumber(int lottoRound) {
        String query = "SELECT bonus FROM winningLotto WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(lottoRound);

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        if (Objects.isNull(results)) {
            return 0;
        }

        Map<String, Object> result = results.get(0);
        return (int) result.get("bonus");
    }

}
