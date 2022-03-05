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
}
