package lotto.domain;

import lotto.domain.lotto.LottoNumber;
import lotto.utils.ParseUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.lotto.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.domain.lotto.LottoNumber.MIN_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "", "+"})
    @DisplayName("로또 숫자 입력시 포맷 검증")
    void validateNumbers(String input) {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = LottoNumber.valueOf(ParseUtil.parseInt(input));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {55, -1, 0})
    @DisplayName("로또 범위 초과했을 경우")
    void validateNumbersRange(int input) {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = LottoNumber.valueOf(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("동일성 검사")
    void checkEqual() {
        LottoNumber lottoNumber1 = LottoNumber.valueOf(1);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(1);
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @Test
    @DisplayName("캐싱된 로또 숫자 인덱스들이 1~45인지 검증")
    void name() {
        Set<Integer> numbers = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1)
                .mapToObj(i -> i)
                .collect(Collectors.toSet());
        assertThat(LottoNumber.getKeys()).isEqualTo(numbers);
    }
}
