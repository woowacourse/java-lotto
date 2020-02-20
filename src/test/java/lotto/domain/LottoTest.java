package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 구매_금액을_생성할_로또들_개수로_변환() {
        //given
        Lotto lotto = new Lotto(new ArrayList<>());
        int money = 14000;
        //when
        int lottosSize = lotto.convertMoneyToLottosSize(money);
        //then
        assertThat(lottosSize).isEqualTo(14);
    }

    @Test
    void 당첨숫자와_일치하는_로또번호_갯수_반환() {
        //given
        List<Integer> lottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 40));
        int bonusBall = 45;
        Lotto lotto = new Lotto(lottoNumbers);
        //when
        int matchSize = lotto.match(winningNumbers, bonusBall);
        //then
        assertThat(matchSize).isEqualTo(5);
    }
}
