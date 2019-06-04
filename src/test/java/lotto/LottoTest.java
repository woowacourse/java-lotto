package lotto;

import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 숫자를_6개보다_덜_입력받은_경우_예외_발생() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(InvalidLottoNumbersException.class, () -> new Lotto(LottoNumber.getLottoNumbers(inputNumbers)));
    }

    @Test
    void 숫자를_6개보다_더_입력받은_경우_예외_발생() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        assertThrows(InvalidLottoNumbersException.class, () -> new Lotto(LottoNumber.getLottoNumbers(inputNumbers)));
    }
}
