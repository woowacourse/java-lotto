package lotto.dao;

import lotto.LottoJDBCTemplate;
import lotto.domain.LottosResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoResultDAOImpl implements LottoResultDAO {
    private static final LottoJDBCTemplate TEMPLATE = LottoJDBCTemplate.getInstance();

    private static class LottoResultDAOImplHolder {
        private static final LottoResultDAO instance = new LottoResultDAOImpl();
    }

    public static LottoResultDAO getInstance() {
        return LottoResultDAOImplHolder.instance;
    }

    @Override
    public long findByRound(int round) {
        String query = "SELECT * FROM lotto_result WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        List<Map<String, Object>> resultList = TEMPLATE.executeQuery(query, queryValues);

        return (long) resultList.get(0).get("winning_money");
    }

    @Override
    public int addLottoResult(LottosResult lottosResult, int round) {
        String query = "INSERT INTO lotto_result VALUES (?, ?, ?)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);
        queryValues.add(lottosResult.getWinningMoney());
        queryValues.add(Math.round(lottosResult.getROI() * 100));

        return TEMPLATE.executeUpdate(query, queryValues);
    }

    @Override
    public int deleteLottoResult(int round) {
        String query = "DELETE FROM lotto_result WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        return TEMPLATE.executeUpdate(query, queryValues);
    }
}
