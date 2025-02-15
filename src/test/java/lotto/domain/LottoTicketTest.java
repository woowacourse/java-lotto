package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {

    private final WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
    private final BonusNumber bonusNumber = new BonusNumber(7);

    @Test
    @DisplayName("보유한 Lotto의 매칭 결과를 도출한다.")
    void deriveMatchResults_CorrectResults() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 19, 11, 12))
        );
        LottoTicket lottoTicket = new LottoTicket(lottos);

        List<MatchResultDto> results = lottoTicket.deriveMatchResults(winningNumber, bonusNumber);

        assertThat(results).hasSize(2);
        assertThat(results.getFirst().getMatchCount()).isEqualTo(6);
        assertThat(results.getFirst().isContainsBonusNumber()).isFalse();
    }

    @Test
    @DisplayName("LottoTicket이 빈 리스트를 가진 경우, 빈 결과 리스트를 반환한다.")
    void deriveMatchResults_ReturnsEmptyList() {
        LottoTicket lottoTicket = new LottoTicket(Collections.emptyList());

        List<MatchResultDto> results = lottoTicket.deriveMatchResults(winningNumber, bonusNumber);

        assertThat(results).isEmpty();
    }


}
