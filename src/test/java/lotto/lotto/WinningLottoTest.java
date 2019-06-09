package lotto.lotto;

import lotto.domain.Rank;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.WinningLotto;
import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 제대로된_숫자들을_입력받은_경우() {
        assertDoesNotThrow(() -> new WinningLotto("1, 2, 3, 4, 5, 6", "7"));
    }

    @Test
    void 빈_문자열을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto("", "1"));
    }

    @Test
    void 공백을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto(" ", "1"));
    }

    @Test
    void 숫자가_아닌_값을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto("1, 2, 3, 4, 5, a", "7"));
    }

    @Test
    void 보너스_숫자로_빈_문자열을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto("1, 2, 3, 4, 5, 6", ""));
    }

    @Test
    void 보너스_숫자로_공백을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto("1, 2, 3, 4, 5, 6", " "));
    }

    @Test
    void 보너스_숫자로_숫자가_아닌_값을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto("1, 2, 3, 4, 5, 6", "a"));
    }

    @Test
    void 보너스_숫자와_로또_숫자가_중복되는_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto("1, 2, 3, 4, 5, 6", "6"));
    }

    @Test
    void 랭킹이_잘_매치되는지_확인() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 8);
        Lotto lotto = new Lotto(LottoNumber.getLottoNumbers(inputNumbers));
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");

        assertThat(winningLotto.matchRank(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 보너스_번호_매칭여부_반영하여_랭킹이_잘_매치되는지_확인() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
        Lotto lotto = new Lotto(LottoNumber.getLottoNumbers(inputNumbers));
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");

        assertThat(winningLotto.matchRank(lotto)).isEqualTo(Rank.SECOND);
    }
}
