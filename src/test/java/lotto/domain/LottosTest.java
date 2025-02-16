package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_생성_확인() {
        //given
        RandomNumber fixedRandomNumber = new FixedRandomNumber();

        //when:
        Lottos lottos = new Lottos(1, fixedRandomNumber);

        //then
        assertEquals(1, lottos.getLottos().size());
    }

    @Test
    void 로또_번호_확인() {
        //given
        RandomNumber fixedRandomNumber = new FixedRandomNumber();

        //when
        Lottos lottos = new Lottos(1, fixedRandomNumber);

        //then
        for (Lotto lotto : lottos.getLottos()) {
            List<Integer> numbers = lotto.getLottoNumbers()
                    .getLottoNumbers()
                            .stream()
                                    .map(LottoNumber::getNumber)
                                            .toList();

            assertEquals(List.of(1, 2, 3, 4, 5, 6), numbers);
        }
    }

    @Test
    void 당첨_결과_계산_테스트() {
        //given
        RandomNumber fixedRandomNumber = new FixedRandomNumber();
        Lottos lottos = new Lottos(1, fixedRandomNumber);
        Lotto winningLotto = new Lotto(new LottoNumbers(new FixedRandomNumber()));
        LottoNumber bonusNumber = new LottoNumber(7);

        //when
        Prizes prizes = lottos.calculatePrize(winningLotto, bonusNumber);

        //then
        Assertions.assertThat(prizes.getResults().containsKey(Rank.FIRST)).isTrue();
    }


}