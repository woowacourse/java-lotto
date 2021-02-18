package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40));
    }

    @Test
    void countNumberOfMatch() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        int matches = lotto.matchingCount(winningNumbers);

        assertThat(matches).isEqualTo(3);
    }

    @Test
    void bonusMatch() {
        int bonusNumber = 20;
        boolean match = lotto.isBonusMatch(bonusNumber);

        assertTrue(match);
    }


}
