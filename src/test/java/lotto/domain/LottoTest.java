package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoTest {

    @Test
    @DisplayName("로또 생성하기")
    void createLotto() {
        Set<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto).isEqualTo(new Lotto(lottoNumbers));
    }

    @Test
    @DisplayName("로또는 총 6개의 숫자로 이루어져야한다.")
    void lottoSize() {
        Set<LottoNumber> lottoNumbersSize7 = Stream.of(1, 2, 3, 4, 5, 6, 7)
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
        Set<LottoNumber> lottoNumbersSize5 = Stream.of(1, 2, 3, 4, 5)
                .map(LottoNumber::from)
                .collect(Collectors.toSet());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(lottoNumbersSize7));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(lottoNumbersSize5));
    }
}
