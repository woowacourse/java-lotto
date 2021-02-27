package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.utility.NumberListTranslator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {

    public static final List<Number> WINNING_NUMBERS =
        NumberListTranslator.translateIntToNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
    public static final Number BONUS_NUMBER = Number.from(7);

    private static Stream<Arguments> provideLottoNumbersAndRank() {
        return Stream.of(
            Arguments.of(Arrays.asList(2, 4, 8, 9, 13, 25), "NONE"),
            Arguments.of(Arrays.asList(2, 4, 6, 8, 13, 25), "FIFTH"),
            Arguments.of(Arrays.asList(2, 4, 6, 1, 7, 3), "SECOND"),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), "FIRST")
        );
    }

    @ParameterizedTest
    @DisplayName("로또 매칭 확인")
    @MethodSource("provideLottoNumbersAndRank")
    void test(List<Integer> rawlottoNumbers, String lottoRank) {
        List<Number> lottoNumbers = NumberListTranslator.translateIntToNumber(rawlottoNumbers);
        Lotto lotto = new Lotto(lottoNumbers);
        LottoAnnouncement lottoAnnouncement = new LottoAnnouncement(WINNING_NUMBERS, BONUS_NUMBER);
        LottoRank rank = lotto.getLottoRank(lottoAnnouncement);
        assertThat(rank.name()).isEqualTo(lottoRank);
    }
}
