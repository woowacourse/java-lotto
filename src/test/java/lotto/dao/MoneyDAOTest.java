package lotto.dao;

import lotto.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyDAOTest {
    private MoneyDAO moneyDAO;

    @BeforeEach
    void setUp() {
        this.moneyDAO = MoneyDAOImpl.getInstance();
    }

    @Test
    void crdMoney() {
        LottoGameDAO lottoGameDAO = LottoGameDAOImpl.getInstance();
        lottoGameDAO.addLottoGame(0);

        Money money = new Money("3000");
        assertEquals(moneyDAO.addMoney(money, 0), 1);
        assertEquals(moneyDAO.findByRound(0), money);
        assertEquals(moneyDAO.deleteMoney(0), 1);

        lottoGameDAO.deleteLottoGame(0);
    }
}