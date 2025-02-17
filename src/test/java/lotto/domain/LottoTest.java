package lotto.domain;

import static lotto.TestUtil.parseToList;
import static lotto.constant.ErrorMessage.DUPLICATE_LOTTO_NUMBER;
import static lotto.constant.ErrorMessage.INVALID_LOTTO_NUMBER_COUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {
    @Test
    void 일치하는_숫자_갯수를_구한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        assertThat(lotto.findMatchCount(winningLotto)).isEqualTo(5);
    }

    @Test
    void 번호_포함_여부를_확인한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.containsNumber(new LottoNumber(3))).isTrue();
    }

    @Test
    void 로또_번호의_등수를_판정한다() {
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 6)))).isEqualTo(Rank.FIRST);
            softly.assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 7)))).isEqualTo(Rank.SECOND);
            softly.assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 8)))).isEqualTo(Rank.THIRD);
        });
    }

    @ParameterizedTest(name = "[{index}] 로또 번호 개수가 {0}개인 경우")
    @CsvSource({
        "5, 1:2:3:4:5",
        "7, 1:2:3:4:5:6:7"
    })
    void 로또_번호_개수가_6개가_아닐_경우_예외를_반환한다(String testName, String numbers) {
        assertThatThrownBy(() -> new Lotto(parseToList(numbers)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(INVALID_LOTTO_NUMBER_COUNT.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "1:2:3:4:5:5",
        "1:1:1:1:1:1"
    })
    void 로또_번호가_중복될_경우_예외를_반환한다(String numbers) {
        assertThatThrownBy(() -> new Lotto(parseToList(numbers)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(DUPLICATE_LOTTO_NUMBER.getMessage());
    }
}
