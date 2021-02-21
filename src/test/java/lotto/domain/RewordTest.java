package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.InvalidLottoHitCountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RewordTest {

    private Reword reword;

    @BeforeEach
    void setUp() {
        reword = Reword.THIRD;
    }

    @DisplayName("맞춘 공의 개수 테스트")
    @Test
    void hitCountTest() {
        assertThat(reword.getHitCount()).isEqualTo(5);
    }

    @DisplayName("당첨 금액 테스트")
    @Test
    void winningMoneyTest() {
        assertThat(reword.getWinningMoney()).isEqualTo(1_500_000);
    }

    @DisplayName("등수 반환 테스트")
    @Test
    void valueOfTest() {
        assertThat(Reword.valueOf(6, false)).isEqualTo(Reword.FIRST);
        assertThat(Reword.valueOf(5, true)).isEqualTo(Reword.SECOND);
        assertThat(Reword.valueOf(5, false)).isEqualTo(Reword.THIRD);
    }

    @DisplayName("hitCount 에 따른 에러테스트")
    @Test
    void validate() {
        assertThatThrownBy(() -> Reword.valueOf(-1, false)).isInstanceOf(
            InvalidLottoHitCountException.class);
        assertThatThrownBy(() -> Reword.valueOf(7, false)).isInstanceOf(
            InvalidLottoHitCountException.class);
    }
}