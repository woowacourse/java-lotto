package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @DisplayName("로또 티켓들 생성하기")
    @Test
    public void create() {
        LottoNumber lotto1 = new LottoNumber(1);
        LottoNumber lotto2 = new LottoNumber(2);
        LottoNumber lotto3 = new LottoNumber(3);
        LottoNumber lotto4 = new LottoNumber(4);
        LottoNumber lotto5 = new LottoNumber(5);
        LottoNumber lotto6 = new LottoNumber(6);
        LottoNumber lotto7 = new LottoNumber(7);

        Set<LottoNumber> lottoNumbers = new HashSet<>(
                Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6));
        Set<LottoNumber> lottoNumbers2 = new HashSet<>(
                Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto7));
        Lottos lottos = new Lottos(Arrays.asList
                (new Lotto(lottoNumbers),
                        new Lotto(lottoNumbers2)));

        assertThat(lottos.toList())
                .contains(new Lotto(lottoNumbers), new Lotto(lottoNumbers2));
    }
}
