package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.view.InputView.convertStringsToInts;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    private Lotto lotto;

    public static Lotto createCustomLotto(String customNumbers) {
        int[] numbers = convertStringsToInts(customNumbers.split(", "));
        return new Lotto(Arrays.asList(
                new LottoNumber(numbers[0]),
                new LottoNumber(numbers[1]),
                new LottoNumber(numbers[2]),
                new LottoNumber(numbers[3]),
                new LottoNumber(numbers[4]),
                new LottoNumber(numbers[5]))
        );
    }

    @BeforeEach
    void setUp() {
        lotto = createCustomLotto("1, 2, 3, 20, 21, 40");
    }

    /*TODO:
     *  생성자 관련 테스트 추가 (예외 처리)*/

    @Test
    void countNumberOfMatch() {
        Lotto winningLotto = createCustomLotto("1, 2, 3, 4, 5, 6");

        int matches = lotto.countMatchingNumbers(winningLotto.toList());

        assertThat(matches).isEqualTo(3);
    }

    @Test
    void bonusMatch() {
        int bonusNumber = 20;
        boolean match = lotto.hasBonusNumber(bonusNumber);

        assertTrue(match);
    }
}
