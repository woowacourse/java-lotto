package lotto.domain.dao;

import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;
import lotto.domain.model.WinningLotto;

import java.util.*;

public class WinningLottoDAOImpl implements WinningLottoDAO {

    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();
    private static final WinningLottoDAOImpl INSTANCE = new WinningLottoDAOImpl();
    private static final int FIRST_ROUND = 1;

    private WinningLottoDAOImpl() {
    }

    public static WinningLottoDAOImpl getInstance() {
        return INSTANCE;
    }

    public void addWinningLotto(WinningLottoDTO winningLottoDTO) {
        String query = "INSERT INTO winning_lotto " +
                "(first_num, second_num, third_num, forth_num, fifth_num, sixth_num, bonus_num, round) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        List<String> args = new ArrayList<>(Arrays.asList(
                String.valueOf(winningLottoDTO.getWinningLotto().getLottoNumbers().get(0).getNumber()),
                String.valueOf(winningLottoDTO.getWinningLotto().getLottoNumbers().get(1).getNumber()),
                String.valueOf(winningLottoDTO.getWinningLotto().getLottoNumbers().get(2).getNumber()),
                String.valueOf(winningLottoDTO.getWinningLotto().getLottoNumbers().get(3).getNumber()),
                String.valueOf(winningLottoDTO.getWinningLotto().getLottoNumbers().get(4).getNumber()),
                String.valueOf(winningLottoDTO.getWinningLotto().getLottoNumbers().get(5).getNumber()),
                String.valueOf(winningLottoDTO.getBonusNum().getNumber()),
                String.valueOf(winningLottoDTO.getRound())
        ));

        JDBC_TEMPLATE.updateQuery(query, args);
    }

    public int getLatestRound() {
        int latestRound = FIRST_ROUND;

        String query = "SELECT round FROM winning_lotto ORDER BY round DESC";
        List<Map<String, String>> results = JDBC_TEMPLATE.selectQuery(query, null);
        for (Map<String, String> result : results) {
            latestRound = Integer.valueOf(result.get("round"));
        }

        return latestRound;
    }

    public WinningLotto getWinningLotto(int round) {
        String query = "SELECT * FROM winning_lotto WHERE round = ?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(round)));
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, args);

        Lotto lotto = new Lotto(Arrays.asList(
                NumberSet.of(Integer.valueOf(result.get(0).get("first_num"))),
                NumberSet.of(Integer.valueOf(result.get(0).get("second_num"))),
                NumberSet.of(Integer.valueOf(result.get(0).get("third_num"))),
                NumberSet.of(Integer.valueOf(result.get(0).get("forth_num"))),
                NumberSet.of(Integer.valueOf(result.get(0).get("fifth_num"))),
                NumberSet.of(Integer.valueOf(result.get(0).get("sixth_num")))));

        Number bonusNumber = NumberSet.of(Integer.valueOf(result.get(0).get("bonus_num")));

        return new WinningLotto(lotto, bonusNumber);
    }

    public void deleteWinningLotto(final int round) {
        String query = "DELETE FROM winning_lotto WHERE round = ?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(round)));
        JDBC_TEMPLATE.updateQuery(query, args);
    }
}
