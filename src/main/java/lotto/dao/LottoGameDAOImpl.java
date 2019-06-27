package lotto.dao;

import lotto.LottoJDBCTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoGameDAOImpl implements LottoGameDAO {
    private static final LottoJDBCTemplate TEMPLATE = LottoJDBCTemplate.getInstance();

    private static class LottoGameDAOImplHolder {
        private static final LottoGameDAO instance = new LottoGameDAOImpl();
    }

    public static LottoGameDAO getInstance() {
        return LottoGameDAOImplHolder.instance;
    }

    @Override
    public int getLastRound() {
        String query = "SELECT * from lotto_game ORDER BY round DESC limit 1";

        List<Map<String, Object>> resultList = TEMPLATE.executeQuery(query);

        return (int) resultList.get(0).get("round");
    }

    @Override
    public int addLottoGame(int round) {
        String query = "INSERT INTO lotto_game VALUES (?)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        return TEMPLATE.executeUpdate(query, queryValues);
    }

    @Override
    public int deleteLottoGame(int round) {
        String query = "DELETE FROM lotto_game WHERE round=?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        return TEMPLATE.executeUpdate(query, queryValues);
    }
}
