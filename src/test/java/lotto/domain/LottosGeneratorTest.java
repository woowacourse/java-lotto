package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottosGeneratorTest {

    @Test
    void 로또_갯수만큼_로또_생성() {
        //given
        int expectedLottosSize = 10;
        //when
        List<Lotto> lottos = LottosGenerator.generate(expectedLottosSize);
        //then
        assertThat(lottos.size()).isEqualTo(expectedLottosSize);
    }

}
