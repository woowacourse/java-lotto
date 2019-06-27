package lotto.dao;

import lotto.LottoJDBCTemplate;
import lotto.domain.creator.LottoCreator;
import lotto.domain.creator.ManualLottoCreator;
import lotto.domain.lotto.Lotto;
import lotto.domain.util.CustomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoDAOImpl implements LottoDAO {
    private static final LottoJDBCTemplate TEMPLATE = LottoJDBCTemplate.getInstance();

    private static class LottoDAOImplHolder {
        private static final LottoDAO instance = new LottoDAOImpl();
    }

    public static LottoDAO getInstance() {
        return LottoDAOImplHolder.instance;
    }

    @Override
    public List<Lotto> findByRound(int round) {
        String query = "SELECT * FROM lotto WHERE round = ?";
        List<Lotto> lottos = new ArrayList<>();

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        List<Map<String, Object>> resultList = TEMPLATE.executeQuery(query, queryValues);

        for (Map<String, Object> result : resultList) {
            LottoCreator creator = new ManualLottoCreator(CustomStringUtils.parseInts((String) result.get("numbers")));
            Lotto lotto = creator.createLotto();

            lotto.setAuto((boolean) result.get("is_auto"));
            lottos.add(lotto);
        }
        return lottos;
    }

    @Override
    public int addLotto(Lotto lotto, int round) {
        String query = "INSERT INTO lotto VALUES (?, ?, ?)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);
        queryValues.add(lotto.getNumbers().toString().replaceAll(("(\\[|])+"), ""));
        queryValues.add(lotto.isAuto());

        return TEMPLATE.executeUpdate(query, queryValues);
    }

    @Override
    public int deleteLotto(int round) {
        String query = "DELETE FROM lotto WHERE round=?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        return TEMPLATE.executeUpdate(query, queryValues);
    }
}
