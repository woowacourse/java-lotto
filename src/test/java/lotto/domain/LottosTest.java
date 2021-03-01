package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @DisplayName("로또 티켓들 생성하기")
    @Test
    public void create() {
        Set<LottoNumber> lottoNumbers1 = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
        Set<LottoNumber> lottoNumbers2 = Stream.of(1, 2, 3, 4, 5, 7)
                .map(LottoNumber::from)
                .collect(Collectors.toSet());

        Lottos lottos = new Lottos(Arrays.asList
                (new Lotto(lottoNumbers1),
                        new Lotto(lottoNumbers2)));

        assertThat(lottos.toList())
                .contains(new Lotto(lottoNumbers1), new Lotto(lottoNumbers2));
    }
}
