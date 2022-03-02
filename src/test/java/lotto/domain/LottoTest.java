package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    private static final int LOTTO_NUMBERS_SIZE = 6;

    @Test
    @DisplayName("중복되거나 6개의 숫자가 아닌 경우 예외를 발생시킨다")
    void testLottoNumbers() {
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복되지 않은 " + LOTTO_NUMBERS_SIZE + "개의 숫자여야합니다.");
    }
}
