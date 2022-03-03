package lotto.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ControllerConfig;
import lotto.domain.Money;

class MoneyControllerTest {

    @Test
    @DisplayName("입력받은 문자열로 금액을 저장한다.")
    public void insertMoneyByInputString() {
        // given
        String inputMoney = "3000";

        // when
        MoneyController moneyController = ControllerConfig.getMoneyController();
        Money insertMoney = moneyController.insertMoney(inputMoney);

        // then
        Assertions.assertThat(insertMoney.getAmount()).isEqualTo(3000);
    }

}