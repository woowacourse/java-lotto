package domain;

import static common.DisplayFormat.PARAMETERIZED_TEST_DISPLAY_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoRefereeTest {

    private static LottoReferee referee;

    @BeforeAll
    static void setUp() {
        List<LottoNumber> winningNumbers = Stream
                .of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.of(7);

        referee = new LottoReferee(winningNumbers, bonusNumber);
    }

    @Test
    void getLottoResult_firstOnSixMatchingNumbers() {
        Lotto lotto = createNewLotto(1, 2, 3, 4, 5, 6);

        assertThat(referee.getLottoResult(lotto)).isEqualTo(LottoResult.FIRST);
    }

    @Test
    void getLottoResult_secondOnFiveMatchingNumbersWithBonus() {
        Lotto lotto = createNewLotto(1, 2, 3, 4, 5, 7);

        assertThat(referee.getLottoResult(lotto)).isEqualTo(LottoResult.SECOND);
    }

    @Test
    void getLottoResult_thirdOnFiveMatchingNumbersWithoutBonus() {
        Lotto lotto = createNewLotto(1, 2, 3, 4, 5, 11);

        assertThat(referee.getLottoResult(lotto)).isEqualTo(LottoResult.THIRD);
    }

    @Test
    void getLottoResult_fourthOnFourMatchingNumbers() {
        Lotto lotto = createNewLotto(1, 2, 3, 4, 11, 12);

        assertThat(referee.getLottoResult(lotto)).isEqualTo(LottoResult.FOURTH);
    }

    @Test
    void getLottoResult_fifthOnThreeMatchingNumbers() {
        Lotto lotto = createNewLotto(1, 2, 3, 11, 12, 13);

        assertThat(referee.getLottoResult(lotto)).isEqualTo(LottoResult.FIFTH);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(strings = {"11,12,13,14,15,16", "1,11,12,13,14,15", "1,2,11,12,13,14"})
    void getLottoResult_nullOnLessThanThreeMatchingNumbers(String value) {
        int[] lottoNumbers = Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .mapToInt(i -> i)
                .toArray();

        Lotto lotto = createNewLotto(lottoNumbers);

        assertThat(referee.getLottoResult(lotto)).isEqualTo(null);
    }

    private Lotto createNewLotto(int... value) {
        List<LottoNumber> lottoNumbers = Arrays.stream(value)
                .boxed()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }
}
