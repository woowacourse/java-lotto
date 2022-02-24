package lotto.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ServiceConfig;
import lotto.domain.Money;

class MoneyServiceTest {

    @Test
    @DisplayName("금액을 저장할 수 있다.")
    public void insertMoney() {
        // given
        MoneyService moneyService = ServiceConfig.getMoneyService();
        // when
        Money money = new Money(1000);
        moneyService.insert(money);
        // then
        Assertions.assertThat(moneyService.inquire()).isEqualTo(new Money(1000));
    }
}