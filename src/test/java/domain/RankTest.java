package domain;

import domain.money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    void Rank별_당첨_금액을_제대로_알려주는지_테스트() {
        Money winningMoney = Rank.FIFTH.getWinningMoney();
        assertThat(winningMoney.getAmount()).isEqualTo(5000);
    }

    @Test
    void Rank의_일치하는_로또숫자_개수를_제대로_되돌려주는지_테스트() {
        Rank rank = Rank.FIFTH;
        assertThat(rank.getNumberOfMatching()).isEqualTo(3);
    }

    @Test
    void 일치하는_숫자에_맞는_rank를_되돌려주는지_테스트() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
    }
}
