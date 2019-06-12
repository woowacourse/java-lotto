package lotto.dao;

import lotto.DataBase;
import lotto.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyDaoTest {
    private MoneyDao moneyDao;

    @BeforeEach
    void setUp() {
        moneyDao = new MoneyDao(new DataBase());
    }

    @Test
    void addMoney() throws Exception {
        moneyDao.addMoney(new Money(10000), 0);
        assertThat(moneyDao.findByTimes(0)).isEqualTo(new Money(10000));
        assertThat(moneyDao.deleteMoney(0)).isEqualTo(1);
    }
}