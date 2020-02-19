package lotto.domain;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.WinningNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbersTest {
    List<LottoNumber> inputNumbers;

    @BeforeEach
    void initiate() {
        inputNumbers = new ArrayList<>();
        inputNumbers.add(LottoNumber.of(1));
        inputNumbers.add(LottoNumber.of(2));
        inputNumbers.add(LottoNumber.of(3));
        inputNumbers.add(LottoNumber.of(4));
        inputNumbers.add(LottoNumber.of(5));
        inputNumbers.add(LottoNumber.of(6));
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_입력_생성자_테스트() {
        Assertions.assertThat(new WinningNumbers(inputNumbers, LottoNumber.of(7)))
                .isInstanceOf(WinningNumbers.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {0, 46})
    void 로또_숫자_범위_외의_값이_들어온_경우(int bonusBall) {
        Assertions.assertThatThrownBy(() -> new WinningNumbers(inputNumbers, LottoNumber.of(bonusBall)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 중복된_숫자가_들어온_경우() {
        Assertions.assertThatThrownBy(() -> new WinningNumbers(inputNumbers, LottoNumber.of(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
