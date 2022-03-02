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
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        LottoGenerator lottoMachine = new FixedLottoGenerator(numbers);

        assertThatThrownBy(() -> new Lotto(lottoMachine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복되지 않은 " + LOTTO_NUMBERS_SIZE + "개의 숫자여야합니다.");
    }
}
