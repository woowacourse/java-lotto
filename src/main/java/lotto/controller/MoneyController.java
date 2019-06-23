package lotto.controller;

import lotto.dao.MoneyDAO;
import lotto.dao.MoneyDAOImpl;
import lotto.domain.Money;
import spark.Request;

public class MoneyController {
    private static MoneyDAO moneyDAO = MoneyDAOImpl.getInstance();

    public static void addMoney(Request req) {
        Money money = new Money(req.queryParams("money"));
        moneyDAO.addMoney(money, LottoGameController.getLastRound());
    }

    public static Money getMoney() {
        return moneyDAO.findByRound(LottoGameController.getLastRound());
    }
}
