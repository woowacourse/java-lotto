package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.withPrecision;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @Test
    @DisplayName(",를 기준으로 당첨번호를 입력받는다.")
    void getWinningNumber() {
        WinningNumbers winningNumbers = WinningNumbers.valueOf("1, 2, 3, 4, 5, 6", "7");
        LottoNumbers expected = LottoNumbers.valueOf("1,2,3,4,5,6");

        assertThat(expected).isEqualTo(winningNumbers.getLottoNumbers());
    }

    @Test
    @DisplayName("하나의 숫자가 아닌 경우 예외")
    void getBonusNumberFromStringInput() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> WinningNumbers.valueOf("1,2,3,4,5,6", "1, 3")
        ).withMessage("입력이 숫자가 아니거나 범위를 벗어났습니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복이 되는 경우 예외")
    void duplicateBonusNumberWithLottoNumbers() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> WinningNumbers.valueOf("1,2,3,4,5,6", "3")
        ).withMessage("보너스 번호는 로또 번호와 달라야 합니다.");
    }

    @Test
    @DisplayName("등수 계산을 반환")
    void getStatistics() {
        WinningNumbers winningNumbers = WinningNumbers.valueOf("1, 2, 3, 4, 5, 6", "7");
        Map<Rank, Long> expected = new HashMap<>();
        expected.put(Rank.FIRST, 1L);
        expected.put(Rank.SECOND, 1L);
        expected.put(Rank.THIRD, 1L);
        expected.put(Rank.FOURTH, 0L);
        expected.put(Rank.FIFTH, 0L);
        int expectedTotalWinnings =
            Rank.FIRST.getWinnings() + Rank.SECOND.getWinnings() + Rank.THIRD.getWinnings();

        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.valueOf("1,2,3,4,5,6"),
            LottoNumbers.valueOf("1,2,3,4,5,34"),
            LottoNumbers.valueOf("1,2,3,4,5,7")
        ));
        WinningStatistics result = new WinningStatistics(lottoTicket, winningNumbers);

        Map<Rank, Long> actual = result.unbox();

        assertThat(expected).isEqualTo(actual);
        assertThat(expectedTotalWinnings / 3000D)
            .isEqualTo(result.getYield(), withPrecision(2d));
    }
}