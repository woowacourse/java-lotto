package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallTest {
    private static final String FALSE_TYPE_SAMPLE = "a";
    private static final String FALSE_RANGE_SAMPLE = "46";
    private static final String FALSE_DUPLICATE_SAMPLE = "1";
    private static final String SAMPLE_BONUSBALL = "7";
    private Lotto winLotto;

    @BeforeEach
    void setUp() {
        final ArrayList<String> winningLotto = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        winLotto = new Lotto(winningLotto);
    }

    @Test
    @DisplayName("보너스볼이 숫자인지 확인")
    void validateBonusBallType() {
        assertThatThrownBy(() -> {
            new BonusBall(winLotto, FALSE_TYPE_SAMPLE);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoNumber.NUMBER_TYPE_ERROR);
    }

    @Test
    @DisplayName("보너스볼이 범위에 있는지 확인")
    void validateBonusBallRange() {
        assertThatThrownBy(() -> {
            new BonusBall(winLotto, FALSE_RANGE_SAMPLE);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoNumber.NUMBER_RANGE_ERROR);
    }

    @Test
    @DisplayName("보너스볼이 당첨번호와 중복인지 확인")
    void validateOverlappedBonusBall() {
        assertThatThrownBy(() -> {
            new BonusBall(winLotto, FALSE_DUPLICATE_SAMPLE);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusBall.SAME_NUMBER_ERROR);
    }

    @Test
    @DisplayName("보너스볼 맞는지 확인")
    void isBonusBall() {
        final BonusBall bonusBall = new BonusBall(winLotto, SAMPLE_BONUSBALL);
        final ArrayList<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "7"));
        final Lotto lotto = new Lotto(numbers);
        final boolean expected = bonusBall.hasBonusBall(lotto);
        assertThat(expected).isEqualTo(true);
    }
}
