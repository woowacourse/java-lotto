package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class LottoConverterTest {
    @Test
    void 구매_금액을_생성할_로또들_개수로_변환() {
        //given
        Lotto lotto = new Lotto(new HashSet<>());
        int money = 14000;
        //when
        int lottosSize = LottoConverter.convertMoneyToLottosSize(money);
        //then
        assertThat(lottosSize).isEqualTo(14);
    }
}
