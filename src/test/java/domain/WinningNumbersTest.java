package domain;

import lotto.domain.WinningNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbersTest {
    List<Integer> inputNumbers;

    @BeforeEach
    void initiate() {
        inputNumbers = new ArrayList<>();
        inputNumbers.add(1);
        inputNumbers.add(2);
        inputNumbers.add(3);
        inputNumbers.add(4);
        inputNumbers.add(5);
        inputNumbers.add(6);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_입력_생성자_테스트() {
        Assertions.assertThat(new WinningNumbers(inputNumbers, 7))
                .isInstanceOf(WinningNumbers.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {0, 46})
    void 로또_숫자_범위_외의_값이_들어온_경우(int bonusBall) {
        Assertions.assertThatThrownBy(() -> new WinningNumbers(inputNumbers, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
