package lotto.domain.dao;

import lotto.domain.model.Money;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class MoneyDAOTest {
    private MoneyDAO moneyDao;

    @Before
    public void setUp() {
        moneyDao = new MoneyDAO();
    }

    @Test
    public void addMoney() {
        Money money = new Money(3000);
        try {
            moneyDao.addMoney(money, 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
