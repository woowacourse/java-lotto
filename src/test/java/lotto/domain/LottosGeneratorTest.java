package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosGeneratorTest {

    @Test
    @DisplayName("로또티켓 중복 검사")
    void checkDuplicated() {
        //given
        int lottosSize = 14;
        //when
        List<Lotto> lottoNumbersList = LottosGenerator.generateAutomatically(lottosSize);
        Set<Lotto> lottoNumbersSet = new HashSet<>(lottoNumbersList);
        assertThat(lottoNumbersList.size()).isEqualTo(lottoNumbersSet.size());
    }

    @Test
    @DisplayName("수동으로 입력한 로또번호들로 복수의 로또 생성")
    void generateLottosManually() {
        //given
        Set<LottoNumber> lottoNumbers = new HashSet<>(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));

        assertThat(LottosGenerator.generateManually(Collections.singletonList(lottoNumbers))).isEqualTo(Collections.singletonList(new Lotto(lottoNumbers)));
    }
}
