package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    private static final int NUMBER_UNDER_MINIMUM = 0;
    private static final int NUMBER_OVER_MAXIMUM = 46;

    @Test
    @DisplayName("LottoNumber를 생성하는 테스트")
    void createLottoNumber() {
        int expected = 3;
        LottoNumber lottoNumber = LottoNumber.from(expected);
        assertThat(lottoNumber).isEqualTo(lottoNumber);
    }

    @Test
    @DisplayName("같은 숫자로 생성한 LottoNumber는 같은 LottoNumber")
    void equals() {
        int expected = 4;
        LottoNumber lottoNumber = LottoNumber.from(expected);
        assertThat(lottoNumber).isNotEqualTo(null);
    }

    @ParameterizedTest
    @ValueSource(ints = {NUMBER_UNDER_MINIMUM, NUMBER_OVER_MAXIMUM})
    @DisplayName("LottoNumber가 가능한 숫자가 아니면 예외 발생")
    void createLottoNumberWithWrongNumberThrowsException(int number) {
        assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
