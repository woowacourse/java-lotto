package lotto.domain;

import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 반환한_로또_숫자_개수_확인() {
        assertThat(LottoNumber.getLottoNumbers().size()).isEqualTo(6);
    }

    @Test
    void 로또_숫자_범위_내의_숫자들을_입력받은_경우() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertDoesNotThrow(() -> LottoNumber.getLottoNumbers(inputNumbers));
    }

    @Test
    void 로또_숫자_범위_초과_숫자를_입력받은_경우_예외_반환() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 46);
        assertThrows(InvalidLottoNumbersException.class, () -> LottoNumber.getLottoNumbers(inputNumbers));
    }

    @Test
    void 로또_숫자_범위_미만_숫자를_입력받은_경우_예외_반환() {
        List<Integer> inputNumbers = Arrays.asList(0, 2, 3, 4, 5, 6);
        assertThrows(InvalidLottoNumbersException.class, () -> LottoNumber.getLottoNumbers(inputNumbers));
    }
}
