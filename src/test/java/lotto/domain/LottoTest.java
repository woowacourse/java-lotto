package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    // TODO:
    //  검증해야되나?

    @Test
    void countNumberOfMatch() {
        List<Integer> lottoNumbers= Arrays.asList(1, 2, 3, 20, 21, 40);
        Lotto lotto = new Lotto(lottoNumbers);
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        int matches = lotto.countMatchingNumbers(winningNumbers);

        assertThat(matches).isEqualTo(3);
    }


}
