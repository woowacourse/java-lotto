package lotto.persistence;

import lotto.domain.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLottoDAOImpl implements WinningLottoDAO {
    private static JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

    public static WinningLottoDAOImpl getInstance() {
        return new WinningLottoDAOImpl();
    }

    public void addWinningLotto(int roundId, WinningLotto winningLotto) {
        String query = "INSERT INTO winningLotto (ro_id, no1, no2, no3, no4, no5, no6, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        List<String> args = winningLotto.getLotto().getLottoNumbers()
                .stream()
                .map(n -> String.valueOf(n.getNumber()))
                .collect(Collectors.toList());
        args.add(0, String.valueOf(roundId));
        args.add(String.valueOf(winningLotto.getBonus()));
        jdbcTemplate.updateQuery(query, args);
    }

    public WinningLotto findWinningLottoByRoundId(int roundId) {
        String query = "SELECT * FROM winningLotto WHERE ro_id=?";
        List<String> arg = new ArrayList<>(Collections.singletonList(String.valueOf(roundId)));
        Map<String, String> result = jdbcTemplate.selectQuery(query, arg).get(0);
        return new WinningLotto(Arrays.asList(
                Integer.valueOf(result.get("no1")),
                Integer.valueOf(result.get("no2")),
                Integer.valueOf(result.get("no3")),
                Integer.valueOf(result.get("no4")),
                Integer.valueOf(result.get("no5")),
                Integer.valueOf(result.get("no6"))
        ), Integer.valueOf(result.get("bonus")));
    }
}
