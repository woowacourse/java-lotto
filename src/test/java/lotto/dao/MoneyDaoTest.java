package lotto.dao;

import lotto.DataBase;
import lotto.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyDaoTest {
    private MoneyDao moneyDao;

    @BeforeEach
    void setUp() {
        moneyDao = new MoneyDao(new DataBase());
    }

    @Test
    void addMoney() throws Exception {
        moneyDao.addMoney(new Money(10000), 1);
    }
}