package lotto.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMoneyTest {

    @Test
    @DisplayName("LottoMoney를 생성")
    void createLottoMoney() {
        int money = 1000;
        LottoMoney lottoMoney = new LottoMoney(money);
        assertThat(lottoMoney.getMoney()).isEqualTo(money);
    }

    @Test
    @DisplayName("LottoMoney는 음수를 받으면 예외 발생")
    void createMoneyWithNegativeValueThrowsException() {
        int money = -1;
        assertThatThrownBy(() -> new LottoMoney(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoMoney는 1000단위로만 생성 가능")
    void createMoneyWithChangeThrowsException() {
        int lottoMoneyWithChange = 1001;
        assertThatThrownBy(() -> new LottoMoney(lottoMoneyWithChange))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성된 LottoMoney는 몇장의 로또를 구매할지 반환")
    void getLottoPurchaseCounts() {
        int tenLottoTicketMoney = 10000;
        LottoMoney lottoMoney = new LottoMoney(tenLottoTicketMoney);
        assertThat(lottoMoney.getLottoPurchaseCounts()).isEqualTo(10);
    }
}
