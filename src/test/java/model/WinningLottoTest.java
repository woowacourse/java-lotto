package model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_SIZE = 6;

    @Test
    void 당첨_번호는_범위를_벗어나면_예외를_발생시킨다() {
        // given
        List<Integer> invalidNumbers = List.of(0, 1, 2, 3, 4, 5);

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(invalidNumbers, MAX_NUMBER - 1)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(("[ERROR] 번호는 1부터 45 사이여야 합니다."));
    }

    @Test
    void 보너스_번호는_범위를_벗어나면_예외를_발생시킨다() {
        // given
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);
        int invalidBonusNUmber = MIN_NUMBER - 1;

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(validNumbers, invalidBonusNUmber)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(("[ERROR] 번호는 1부터 45 사이여야 합니다."));
    }

    @Test
    void 당첨_번호는_정해진_개수가_아니라면_예외를_발생시킨다() {
        // given
        List<Integer> invalidNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        int validBonusNumber = MIN_NUMBER;

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(invalidNumbers, validBonusNumber)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(("[ERROR] 당첨 번호는 6개여야 합니다."));
        ;
    }

    @Test
    void 당첨_번호가_중복되면_예외를_발생시킨다() {
        // given
        List<Integer> invalidNumbers = List.of(1, 2, 3, 4, 5, 5);
        int validBonusNumber = MIN_NUMBER;

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(invalidNumbers, validBonusNumber)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 중복되어서는 안됩니다.");
    }

    @Test
    void 당첨_번호와_보너스_번호가_중복되면_예외를_발생시킨다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int duplicatedBonusNumber = numbers.getFirst();

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(numbers, duplicatedBonusNumber)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호와 보너스 번호는 중복되어서는 안됩니다.");
    }
}
