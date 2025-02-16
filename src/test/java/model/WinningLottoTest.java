package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.numbers.LottoNumber;
import model.numbers.LottoNumbers;
import model.numbers.WinningLotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;

    @Test
    void 당첨_번호와_보너스_번호가_중복되면_예외를_발생시킨다() {
        // given
        LottoNumbers numbers = new LottoNumbers(fromIntegerListToLottoNumberList(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber duplicatedBonusNumber = new LottoNumber(numbers.getNumbers().getFirst());

        // when & then
        Assertions.assertThatThrownBy(
                        () -> new WinningLotto(numbers, duplicatedBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호와 보너스 번호는 중복되어서는 안됩니다.");
    }

    @Test
    void 당첨번호와_겹치는_숫자의_개수를_센다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(fromIntegerListToLottoNumberList(List.of(1, 2, 3, 4, 5, 6)));
        WinningLotto winningLotto = new WinningLotto(lottoNumbers, new LottoNumber(UPPER_BOUND));
        List<Integer> comparedNumbers = List.of(1, 2, 3, 4, 5, 7);

        // when
        int overlappedCount = winningLotto.countOverlappedNumbers(comparedNumbers);

        //then
        assertThat(overlappedCount).isEqualTo(5);
    }

    @Test
    void 보너스번호와_겹치는지_확인한다() {
        // given
        LottoNumber bonusNumber = new LottoNumber(UPPER_BOUND);
        WinningLotto winningLotto = new WinningLotto(
                new LottoNumbers(fromIntegerListToLottoNumberList(List.of(1, 2, 3, 4, 5, 6))),
                bonusNumber);
        List<Integer> comparedNumbers = List.of(1, 2, 3, 4, 5, UPPER_BOUND);

        // when
        boolean isBonusNumberOverlapped = winningLotto.isOverlappedBonusNumber(comparedNumbers);

        //then
        assertThat(isBonusNumberOverlapped).isTrue();
    }

    // TODO: 유효한 경우에 대한 테스트

    private List<LottoNumber> fromIntegerListToLottoNumberList(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
