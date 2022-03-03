package lotto.controller;

import lotto.config.ServiceConfig;
import lotto.domain.Money;
import lotto.service.MoneyService;
import lotto.utils.IntegerUtils;

public class MoneyController {

    private final MoneyService moneyService;

    private MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    private static class MoneyControllerHelper {
        private static final MoneyController INSTANCE = new MoneyController(
            ServiceConfig.getMoneyService()
        );
    }

    public static MoneyController getInstance() {
        return MoneyControllerHelper.INSTANCE;
    }

    public void insertMoney(String inputMoney) {
        Money money = toMoney(inputMoney);
        moneyService.insert(money);
    }

    private Money toMoney(String inputMoney) {
        return Money.from(IntegerUtils.parse(inputMoney));
    }
}
