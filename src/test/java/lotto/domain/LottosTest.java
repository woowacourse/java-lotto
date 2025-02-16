package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottosTest {
    @Test
    void 당첨_결과를_구한다() {
        //given
        Lottos lottos = new Lottos(List.of(
            new Lotto(List.of(1,2,3,4,5,6)),
            new Lotto(List.of(7,8,9,10,11,12))
        ));
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1,2,3,4,5,6)), 7);

        //when
        Map<Rank, Integer> rankCount = lottos.getRankCount(winningNumbers);

        //then
        assertThat(rankCount.get(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 주어진_가격으로_정확한_매수를_구한다() {
        //given
        Lottos lottos = new Lottos(3000);

        //when & then
        assertThat(lottos.getTicketCount()).isEqualTo(3);
    }

    @Test
    void 구입_금액이_1000원으로_나누어떨어지지_않을_경우_예외를_반환한다() {
        //when & then
        assertThatThrownBy(() -> new Lottos(3))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lottos(3003))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
