package domain.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottoMoneyTest {

    @Test
    void lottoMoneyTest() {
        int money = 1000;
        LottoMoney lottoMoney = new LottoMoney(money);
        assertThat(lottoMoney.getMoney()).isEqualTo(money);
    }

    @Test
    void createMoneyWithNegativeValue() {
        int money = -1;
        assertThatThrownBy(() -> new LottoMoney(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createMoneyWithChange() {
        int lottoMoneyWithChange = 1001;
        assertThatThrownBy(() -> new LottoMoney(lottoMoneyWithChange))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getLottoPurchaseCounts() {
        int tenLottoTicketMoney = 10000;
        LottoMoney lottoMoney = new LottoMoney(tenLottoTicketMoney);
        assertThat(lottoMoney.getLottoPurchaseCounts()).isEqualTo(10);
    }

    public static LottoMoney getLottoMoneyFixture() {
        return new LottoMoney(10000);
    }
}
