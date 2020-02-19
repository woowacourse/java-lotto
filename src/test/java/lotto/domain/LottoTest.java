package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 구매_금액을_생성할_로또들_개수로_변환() {
        //given
        Lotto lotto = new Lotto();
        int money = 14000;
        //when
        int lottosSize = lotto.convertMoneyToLottosSize(money);
        //then
        assertThat(lottosSize).isEqualTo(14);
    }
}
