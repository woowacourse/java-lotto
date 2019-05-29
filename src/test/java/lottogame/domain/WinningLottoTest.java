package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 입력받은_리스트에_중복이_있을_경우_예외처리_테스트() {
        List<Integer> winningLotto = Arrays.asList(1,2,3,4,6,6);
        assertThrows(InvalidLottoNumberException.class, () -> new WinningLotto(winningLotto));
    }

    @Test
    void 입력받은_숫자가_로또범위를_벗어났을_때_예외처리_테스트() {
        List<Integer> winningLotto = Arrays.asList(1,2,3,4,6,50);
        assertThrows(InvalidLottoNumberException.class, () -> new WinningLotto(winningLotto));
    }

    @Test
    void 입력받은_숫자가_6개가_아닌_경우_예외처리_테스트() {
        List<Integer> winningLotto = Arrays.asList(1,2,3,4,6);
        assertThrows(InvalidLottoNumberException.class, () -> new WinningLotto(winningLotto));
    }
}
