package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    @Test
    void 주어진_가격으로_정확한_매수를_구한다() {
        Lottos lottos = new Lottos(3000);
        assertThat(lottos.getTicketCount()).isEqualTo(3);
    }

    @Test
    void 로또_티켓을_랜덤으로_생성한다() {
        Lottos lottos = new Lottos(3000);
        assertThat(lottos.getLottos().get(0).numbers.size()).isEqualTo(6);
    }

    @Test
    void 로또_번호의_등수를_판정한다() {
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1,2,3,4,5,6)), 7);
        assertThat(winningNumbers.getRank(new Lotto(List.of(1,2,3,4,5,6)))).isEqualTo(Rank.FIRST);
        assertThat(winningNumbers.getRank(new Lotto(List.of(1,2,3,4,5,7)))).isEqualTo(Rank.SECOND);
        assertThat(winningNumbers.getRank(new Lotto(List.of(1,2,3,4,5,8)))).isEqualTo(Rank.THIRD);
    }
}
