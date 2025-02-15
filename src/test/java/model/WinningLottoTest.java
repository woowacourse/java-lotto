package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;

    @Test
    void 당첨_번호는_범위를_벗어나면_예외를_발생시킨다() {
        // given
        List<Integer> invalidNumbers = List.of(0, 1, 2, 3, 4, 5);
        int validBonusNumber = UPPER_BOUND - 1;

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(new LottoNumbers(invalidNumbers), validBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(("[ERROR] 번호는 1부터 45 사이여야 합니다."));
    }

    @Test
    void 보너스_번호는_범위를_벗어나면_예외를_발생시킨다() {
        // given
        LottoNumbers validLottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        int invalidBonusNUmber = LOWER_BOUND - 1;

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(validLottoNumbers, invalidBonusNUmber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(("[ERROR] 번호는 1부터 45 사이여야 합니다."));
    }

    @Test
    void 당첨_번호와_보너스_번호가_중복되면_예외를_발생시킨다() {
        // given
        LottoNumbers numbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        int duplicatedBonusNumber = numbers.getNumbers().getFirst();

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(numbers, duplicatedBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호와 보너스 번호는 중복되어서는 안됩니다.");
    }

    @Test
    void 당첨번호와_겹치는_숫자의_개수를_센다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(lottoNumbers, UPPER_BOUND);
        List<Integer> comparedNumbers = List.of(1, 2, 3, 4, 5, 7);

        // when
        int overlappedCount = winningLotto.countOverlappedNumbers(comparedNumbers);

        //then
        assertThat(overlappedCount).isEqualTo(5);
    }

    @Test
    void 보너스번호와_겹치는지_확인한다() {
        // given
        int bonusNumber = UPPER_BOUND;
        WinningLotto winningLotto = new WinningLotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)), bonusNumber);
        List<Integer> comparedNumbers = List.of(1, 2, 3, 4, 5, bonusNumber);

        // when
        boolean isBonusNumberOverlapped = winningLotto.isOverlappedBonusNumber(comparedNumbers);

        //then
        assertThat(isBonusNumberOverlapped).isTrue();
    }
}
