package lotto.domain.service;

import lotto.domain.dao.MoneyDAO;
import lotto.domain.dto.MoneyDTO;
import lotto.domain.model.Money;

import java.sql.SQLException;

public class MoneyService {
    public void addMoney(final String inputMoney, final String round) throws SQLException {
        MoneyDTO moneyDTO = new MoneyDTO();
        MoneyDAO moneyDAO = new MoneyDAO();

        Money money = new Money(Integer.parseInt(inputMoney));
        moneyDTO.setMoney(money.getMoney());
        moneyDTO.setAvailableCount(money.availablePurchaseCount());
        moneyDTO.setRound(Integer.parseInt(round));

        moneyDAO.addMoney(moneyDTO);
    }
}
