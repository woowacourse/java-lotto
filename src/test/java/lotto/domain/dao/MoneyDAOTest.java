package lotto.domain.dao;

import lotto.domain.dto.MoneyDTO;
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
        MoneyDTO moneyDTO = new MoneyDTO();
        moneyDTO.setRound(2);
        moneyDTO.setMoney(3000);
        moneyDTO.setAvailableCount(3);
        try {
            moneyDao.addMoney(moneyDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
