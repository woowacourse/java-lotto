package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {
    @DisplayName("로또 번호가 1에서 45 사이인지 검증 하는지")
    @Test
    void LottoNumber_numberOutOfBounds_throwError() {
        assertThatThrownBy(() -> new LottoNumber(49)).isInstanceOf(IllegalArgumentException.class);
    }

    public static List<LottoNumber> createCustomLottoNumbers(String customNumbers) {
        String[] numbers = customNumbers.split(", ");
        List<LottoNumber> winningNumbers = Arrays.asList(
                new LottoNumber(numbers[0]),
                new LottoNumber(numbers[1]),
                new LottoNumber(numbers[2]),
                new LottoNumber(numbers[3]),
                new LottoNumber(numbers[4]),
                new LottoNumber(numbers[5]));
        return winningNumbers;
    }
}
