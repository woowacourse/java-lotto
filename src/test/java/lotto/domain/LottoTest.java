package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.LottoNumberTest.createCustomLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(createCustomLottoNumbers("1, 2, 3, 20, 21, 40"));
    }

    /*TODO:
     *  생성자 관련 테스트 추가 (예외 처리)*/

    @Test
    void countNumberOfMatch() {
        List<LottoNumber> winningNumbers = createCustomLottoNumbers("1, 2, 3, 4, 5, 6");

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
