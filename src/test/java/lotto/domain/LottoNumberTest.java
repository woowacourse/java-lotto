package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoNumberTest {

    @DisplayName("로또 번호 범위가 1~45를 벗어난 경우")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void cannotMake(int number) {
        assertThatCode(() -> {
            LottoNumber.from(number);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호 범위가 벗어났습니다.");
    }

    @DisplayName("로또 번호 범위가 1~45 일 경우")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 45})
    void makeLottoNumber(int number) {
        LottoNumber lottoNumber = LottoNumber.from(number);

        int value = lottoNumber.getNumber();

        assertThat(value).isEqualTo(number);
    }

    @DisplayName("로또 번호는 싱글톤 객체이다")
    @Test
    void isSingleton() {
        LottoNumber lottoNumber = LottoNumber.from(1);
        LottoNumber target = LottoNumber.from(1);

        assertThat(lottoNumber).isEqualTo(target);
    }
}
