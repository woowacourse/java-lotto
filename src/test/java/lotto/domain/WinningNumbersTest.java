package lotto.domain;

import static lotto.TestUtil.parseToList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningNumbersTest {
    @Test
    void 당첨_결과를_구한다() {
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1,2,3,4,5,7)), 6);
        assertThat(winningNumbers.getRank(lotto)).isEqualTo(Rank.SECOND);
    }

    @ParameterizedTest
    @CsvSource({
        "1:2:3:4:5:6, 1",
        "1:2:3:4:5:6, 6"
    })
    void 당첨_번호와_보너스_번호가_중복될_경우_예외를_반환한다(String numbers, int bonusNumber) {
        assertThatThrownBy(() -> new WinningNumbers(new Lotto(parseToList(numbers)), bonusNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
