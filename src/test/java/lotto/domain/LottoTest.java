package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.exception.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또를 생성하여 LottoNumber 리스트로 반환")
    void getLottoToInteger() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(45, 1, 10, 4, 20, 30));
        Assertions.assertThat(Lotto.generateLottoByManual(numbers).getLottoToInteger())
                .containsExactly(1, 4, 10, 20, 30, 45);
    }

    @Test
    @DisplayName("숫자가 6개보다 적은 경우 예외 발생")
    void checkSmallSize() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Assertions.assertThatThrownBy(() -> Lotto.generateLottoByManual(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 6개보다 많은 경우 예외 발생")
    void checkLargeSize() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Assertions.assertThatThrownBy(() -> Lotto.generateLottoByManual(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 중복되는 경우 예외 발생")
    void checkDuplication() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 2, 5));
        Assertions.assertThatThrownBy(() -> Lotto.generateLottoByManual(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("당첨번호와 비교하여 알맞은 당첨 등수 반환")
    void getRank() {
        WinningNumbers winningNumbersInstance
                = new WinningNumbers(new ArrayList<>(Arrays.asList(1,2,3,4,5,45)), 6);
        Lotto lotto = Lotto.generateLottoByManual(new ArrayList<>(Arrays.asList(1,2,3,4,5,6)));
        Assertions.assertThat(lotto.getRank(winningNumbersInstance)).isEqualTo(Rank.RANK_2);
    }
}
