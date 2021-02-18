package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    // TODO:
    //  검증해야되나?

    private Lotto lotto;
    private List<Integer> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = Arrays.asList(1, 2, 3, 20, 21, 40);
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    void countNumberOfMatch() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        int matches = lotto.countMatchingNumbers(winningNumbers);

        assertThat(matches).isEqualTo(3);
    }

    @Test
    void bonusMatch() {
        int bonusNumber = 20;
        boolean match = lotto.hasBonusNumber(bonusNumber);

        assertTrue(match);
    }


}
