package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 입력받은_리스트에_중복이_있을_경우_예외처리_테스트() {
        List<Integer> winningLotto = Arrays.asList(1, 2, 3, 4, 6, 6);
        assertThrows(InvalidLottoNumberException.class, () -> new WinningLotto(winningLotto));
    }

    @Test
    void 당첨번호와_중복되는_보너스_숫자를_입력_시_예외처리_테스트() {
        List<Integer> winningLottos = Arrays.asList(1, 2, 3, 4, 5, 6);
        WinningLotto winningLotto = new WinningLotto(winningLottos);
        assertThrows(InvalidLottoNumberException.class, () -> winningLotto.addBonusNumber(6));
    }
}
