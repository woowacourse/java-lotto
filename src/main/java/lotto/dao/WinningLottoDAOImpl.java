package lotto.dao;

import lotto.LottoJDBCTemplate;
import lotto.domain.DBConnector;
import lotto.domain.lotto.WinningLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WinningLottoDAOImpl implements WinningLottoDAO {
    private static final String regexForDelteBracket = "(\\[|])+";
    private static final DBConnector CONNECTOR = DBConnector.getInstance();
    private static final LottoJDBCTemplate TEMPLATE = LottoJDBCTemplate.getInstance();


    private static class WinningLottoDAOImplHolder {
        private static final WinningLottoDAO instance = new WinningLottoDAOImpl();
    }

    public static WinningLottoDAO getInstance() {
        return WinningLottoDAOImplHolder.instance;
    }

    @Override
    public WinningLotto findByRound(int round) {
        String query = "SELECT * FROM winning_lotto WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        List<Map<String, Object>> resultList = TEMPLATE.executeQuery(query);

        Map<String, Object> result = resultList.get(0);

        return new WinningLotto((String) result.get("numbers"), (String) result.get("bonus_number"));
    }

    @Override
    public int addWinningLotto(WinningLotto winningLotto, int round) {
        String query = "INSERT INTO winning_lotto VALUES (?, ?, ?)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);
        queryValues.add(winningLotto.getLotto().getNumbers().toString().replaceAll((regexForDelteBracket), ""));
        queryValues.add(winningLotto.getBonusNumber().toString());

        return TEMPLATE.executeUpdate(query, queryValues);
    }

    @Override
    public int deleteWinningLotto(int round) {
        String query = "DELETE FROM winning_lotto WHERE round=?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        return TEMPLATE.executeUpdate(query, queryValues);
    }
}
