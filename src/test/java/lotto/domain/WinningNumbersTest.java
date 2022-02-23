package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("당첨 번호 개수 만큼 당첨 번호를 생성한다.")
    @Test
    void 당첨_번호_생성_확인() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 7;

        // when & then
        assertThatCode(() -> new WinningNumbers(winningNumbers, bonusBall))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoTicket 을 기반으로 당첨 정보를 반환한다.")
    @Test
    void 당첨_정보_확인_2등() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoTicket lottoTicket = new LottoTicket(() -> List.of(1, 2, 3, 4, 7, 8));

        // when
        Rank rank = winningNumbers.compareLottoTicket(lottoTicket);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("LottoTicket 을 기반으로 당첨 정보를 반환한다.")
    @Test
    void 당첨_정보_확인_3등() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoTicket lottoTicket = new LottoTicket(() -> List.of(1, 2, 3, 4, 5, 8));

        // when
        Rank rank = winningNumbers.compareLottoTicket(lottoTicket);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }
}