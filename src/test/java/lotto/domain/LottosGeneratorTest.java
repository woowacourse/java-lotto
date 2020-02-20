package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LottosGeneratorTest {

    @Test
    void 로또_갯수만큼_로또_생성() {
        //given
        int expectedLottosSize = 1;
        //when
        List<Lotto> lottos = LottosGenerator.generate(expectedLottosSize,
            new ArrayList<>(Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6))));
        //then
        assertThat(lottos.size()).isEqualTo(expectedLottosSize);
    }
}
