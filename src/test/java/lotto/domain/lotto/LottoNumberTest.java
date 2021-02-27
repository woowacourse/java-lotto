package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    void 생성_및_비교() {
        // given, when
        LottoNumber lottoNumber1 = LottoNumber.valueOf(1);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(1);

        // then
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @DisplayName("로또 번호는 1부터 45사이의 값이어야 한다.")
    @Test
    void 유효성_검사() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumber.valueOf(LottoNumber.MIN - 1));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumber.valueOf(LottoNumber.MAX + 1));
    }

    @DisplayName("로또 번호의 범위인 1부터 45의 값은 캐싱되어야한다.")
    @Test
    void 캐싱_테스트() {
        // given, when
        LottoNumber cachedNumber1_1 = LottoNumber.valueOf(1);
        LottoNumber cachedNumber1_2 = LottoNumber.valueOf(1);

        LottoNumber cachedNumber2_1 = LottoNumber.valueOf(45);
        LottoNumber cachedNumber2_2 = LottoNumber.valueOf(45);

        // then
        assertThat(cachedNumber1_1).isSameAs(cachedNumber1_2);
        assertThat(cachedNumber2_1).isSameAs(cachedNumber2_2);
    }
}
