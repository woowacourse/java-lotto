package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.utils.RandomLottoNumbersGenerator;

public class LottoTest {
    @Test
    @DisplayName("로또 구매개수 입력이 14000일 때 자동으로 생성되는 티켓의 개수는 14개여야 합니다.")
    void lottoCreateValidTest() {
        Lotto lotto = new Lotto(14000, new RandomLottoNumbersGenerator());
        int ticketCount = lotto.getTicketCount();
        assertThat(ticketCount).isEqualTo(14);
    }

    @Test
    @DisplayName("투입금액으로 만들 수 있는 티켓의 개수보다 입력된 티켓의 개수가 더 크면 예외를 반환해야 합니다.")
    void validateTicketCountTest() {
        Lotto lotto = new Lotto(1000);
        int ticketCount = 2;
        assertThatThrownBy(() -> lotto.validateTicketCount(ticketCount))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
