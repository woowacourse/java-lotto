package domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 당첨결과 테스트")
    @Test
    void 로또_당첨결과_테스트() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.getRank(new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7))).isEqualTo(Rank.FIRST);
        assertThat(lotto.getRank(new WinningLotto(List.of(1, 2, 3, 4, 5, 10), 6))).isEqualTo(Rank.SECOND);
        assertThat(lotto.getRank(new WinningLotto(List.of(1, 2, 3, 4, 5, 8), 9))).isEqualTo(Rank.THIRD);
        assertThat(lotto.getRank(new WinningLotto(List.of(1, 2, 3, 4, 7, 8), 9))).isEqualTo(Rank.FOURTH);
        assertThat(lotto.getRank(new WinningLotto(List.of(1, 2, 3, 7, 8, 9), 10))).isEqualTo(Rank.FIFTH);
        assertThat(lotto.getRank(new WinningLotto(List.of(1, 2, 9, 10, 11, 12), 13))).isEqualTo(Rank.NONE);
        assertThat(lotto.getRank(new WinningLotto(List.of(7, 8, 9, 10, 11, 12), 13))).isEqualTo(Rank.NONE);
    }

    @Test
    void 로또_생성_예외_테스트() {
        assertThatThrownBy(() -> {
            new Lotto(List.of(1,2,3,4,5,5));
        }).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {
            new Lotto(List.of(1,2,3,4,5));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
