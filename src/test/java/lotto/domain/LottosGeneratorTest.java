package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosGeneratorTest {

    @Test
    @DisplayName("수동으로 입력한 로또번호들로 복수의 로또 생성")
    void generateLottosManually() {
        //given
        Set<LottoNumber> lottoNumbers = new HashSet<>(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));

        assertThat(LottosGenerator.generateManually(Collections.singletonList(lottoNumbers))).isEqualTo(new Lottos(Collections.singletonList(new Lotto(lottoNumbers))));
    }

    @Test
    @DisplayName("자동으로 입력한 로또번호들로 복수의 로또 생성")
    void generateAutomatically() {
        int lottosSize = 2;
        Lottos lottos = LottosGenerator.generateAutomatically(lottosSize);
        assertThat(lottos.size()).isEqualTo(lottosSize);
    }
}
