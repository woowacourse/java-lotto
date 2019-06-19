package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyDAOTest {
    private MoneyDAO moneyDAO;

    @BeforeEach
    void setUp() {
        moneyDAO = new MoneyDAO(new DBConnector());
    }

    @Test
    void crdMoney() throws Exception {
        LottoGameDAO lottoGameDAO = new LottoGameDAO(new DBConnector());
        //lottoGameDAO.addLottoGame(0);

        Money money = new Money("3000");
        //assertEquals(moneyDAO.addMoney(money, 0), 1);
        //assertEquals(moneyDAO.findByRound(0), money);
        assertEquals(moneyDAO.deleteMoney(1), 1);

        lottoGameDAO.deleteLottoGame(1);
    }
}