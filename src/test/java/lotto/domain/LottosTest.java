package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_생성_확인() {
        //given
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        FixedRandomNumber fixedRandomNumber = new FixedRandomNumber(numbers);

        //when
        Lottos lottos = new Lottos(5, fixedRandomNumber);

        //then
        assertEquals(5, lottos.getLottos().size());
    }

    @Test
    void 당첨_결과() {
        //given
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        FixedRandomNumber fixedRandomNumber = new FixedRandomNumber(numbers);

        Lottos lottos = new Lottos(5, fixedRandomNumber);

        Lotto winningLotto = new Lotto(fixedRandomNumber);
        LottoNumber bonusNumber = new LottoNumber(7);

        //when
        Prizes prizes = lottos.calculatePrize(winningLotto, bonusNumber);
        long count = prizes.getResults().keySet().stream()
                .filter(rank -> rank == Rank.FIRST)
                        .count();
        //then
        assertEquals(1, count);
    }

}