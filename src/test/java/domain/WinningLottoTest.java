package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTest {

    public static final String PARAMETERIZED_TEST_DISPLAY_FORMAT =
            DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]";

    private static WinningLotto referee;

    @BeforeAll
    static void setUp() {
        List<LottoNumber> winningNumbers = Stream
                .of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.of(7);

        referee = new WinningLotto(winningNumbers, bonusNumber);
    }

    @Test
    void getLottoResult_firstOnSixMatchingNumbers() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 5, 6);

        assertThat(referee.getLottoResult(lottoTicket)).isEqualTo(LottoResult.FIRST);
    }

    @Test
    void getLottoResult_secondOnFiveMatchingNumbersWithBonus() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 5, 7);

        assertThat(referee.getLottoResult(lottoTicket)).isEqualTo(LottoResult.SECOND);
    }

    @Test
    void getLottoResult_thirdOnFiveMatchingNumbersWithoutBonus() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 5, 11);

        assertThat(referee.getLottoResult(lottoTicket)).isEqualTo(LottoResult.THIRD);
    }

    @Test
    void getLottoResult_fourthOnFourMatchingNumbers() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 11, 12);

        assertThat(referee.getLottoResult(lottoTicket)).isEqualTo(LottoResult.FOURTH);
    }

    @Test
    void getLottoResult_fifthOnThreeMatchingNumbers() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 11, 12, 13);

        assertThat(referee.getLottoResult(lottoTicket)).isEqualTo(LottoResult.FIFTH);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(strings = {"11,12,13,14,15,16", "1,11,12,13,14,15", "1,2,11,12,13,14"})
    void getLottoResult_nullOnLessThanThreeMatchingNumbers(String value) {
        int[] lottoNumbers = Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .mapToInt(i->i)
                .toArray();

        LottoTicket lottoTicket = createNewLotto(lottoNumbers);

        assertThat(referee.getLottoResult(lottoTicket)).isEqualTo(null);
    }

    private LottoTicket createNewLotto(int... value) {
        List<LottoNumber> lottoNumbers = Arrays.stream(value)
                .boxed()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return LottoTicket.createManualLotto(lottoNumbers);
    }
}
