package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LottosGeneratorTest {

    @Test
    void 로또티켓_중복_검사() {
        //given
        int lottosSize = 14;
        //when
        List<Lotto> lottoNumbersList = LottosGenerator.generate(lottosSize);
        Set<Lotto> lottoNumbersSet = new HashSet<>(lottoNumbersList);
        assertThat(lottoNumbersList.size()).isEqualTo(lottoNumbersSet.size());
    }

    @Test
    void 로또_갯수만큼_로또_생성() {
        //given
        int expectedLottosSize = 1;
        //when
        // List<Lotto> lottos = LottosGenerator.generate(expectedLottosSize,
        //     new ArrayList<>(Arrays.asList(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)))));
        // //then
        // assertThat(lottos.size()).isEqualTo(expectedLottosSize);
    }
}
