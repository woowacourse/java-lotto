package lotto.domain.ticket.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @DisplayName("생성된 ball들이 범위내의 숫자인지")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void test1(int invalidNumber) {
        assertThatThrownBy(() -> LottoNumber.from(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("%d 로또 번호는 1부터 45까지 허용됩니다.", invalidNumber);
    }

    @DisplayName("볼 생성시 번호가 같은지 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void test2(int number) {
        assertThat(LottoNumber.from(number)).isEqualTo(LottoNumber.from(number));
    }

}
