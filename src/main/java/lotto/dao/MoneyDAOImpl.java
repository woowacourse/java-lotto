package lotto.dao;

import lotto.LottoJDBCTemplate;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoneyDAOImpl implements MoneyDAO {
    private static final LottoJDBCTemplate TEMPLATE = LottoJDBCTemplate.getInstance();

    private static class MoneyDAOImplHolder {
        private static final MoneyDAO instance = new MoneyDAOImpl();
    }

    public static MoneyDAO getInstance() {
        return MoneyDAOImplHolder.instance;
    }

    @Override
    public Money findByRound(int round) {
        String query = "SELECT * FROM money WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        List<Map<String, Object>> resultList = TEMPLATE.executeQuery(query, queryValues);

        return new Money(Integer.toString((int) resultList.get(0).get("money")));
    }

    @Override
    public int addMoney(Money money, int round) {
        String query = "INSERT INTO money VALUES (?, ?)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);
        queryValues.add(money.getMoney());

        return TEMPLATE.executeUpdate(query, queryValues);
    }

    @Override
    public int deleteMoney(int round) {
        String query = "DELETE FROM money WHERE round = ?";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        return TEMPLATE.executeUpdate(query, queryValues);
    }
}
