package lotto.domain.creator;

import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManualLottoCreatorTest {
    @Test
    void 로또_숫자_범위_내의_숫자들을_입력받은_경우() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoCreator creator = new ManualLottoCreator(inputNumbers);
        assertDoesNotThrow(() -> creator.createLotto());
    }

    @Test
    void 로또_숫자_범위_초과_숫자를_입력받은_경우_예외_반환() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 46);
        LottoCreator creator = new ManualLottoCreator(inputNumbers);
        assertThrows(InvalidLottoNumbersException.class, () -> creator.createLotto());
    }

    @Test
    void 로또_숫자_범위_미만_숫자를_입력받은_경우_예외_반환() {
        List<Integer> inputNumbers = Arrays.asList(0, 2, 3, 4, 5, 6);
        LottoCreator creator = new ManualLottoCreator(inputNumbers);
        assertThrows(InvalidLottoNumbersException.class, () -> creator.createLotto());
    }
}
