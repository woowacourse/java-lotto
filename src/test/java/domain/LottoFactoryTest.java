package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoFactoryTest {

    @DisplayName("로또 생성")
    @Test
    void generateLotto() {
        //given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int lottoCount = 3;

        //when
        List<Lotto> lottos = LottoFactory.generates(lottoNumbers -> numbers, lottoCount);

        //then
        Lotto sample = new Lotto(numbers);
        assertAll(
                () -> assertThat(lottos).hasSize(lottoCount),
                () -> assertThat(lottos.get(0)).isEqualTo(sample),
                () -> assertThat(lottos.get(1)).isEqualTo(sample),
                () -> assertThat(lottos.get(2)).isEqualTo(sample)
        );
    }
}