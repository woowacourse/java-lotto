package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoMoneyTest {
    @Test
    void 생성() {
        LottoMoney lottoMoney = new LottoMoney(15000);
        assertThat(lottoMoney.getCountOfTicket()).isEqualTo(15);
    }

    @Test
    void 최소값보다_작은입력() {
        assertThrows(IllegalArgumentException.class, () -> new LottoMoney(800));
    }

    @Test
    void 최대값보다_큰입력() {
        assertThrows(IllegalArgumentException.class, () -> new LottoMoney(1000000));
    }
}
