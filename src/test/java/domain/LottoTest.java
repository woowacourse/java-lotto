package domain;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @MethodSource("getLottoNumbersInputData")
    @ParameterizedTest
    void 당첨_번호와_비교하여_일치_결과를_반환한다(
            List<Integer> lottoNumbers, int winningNumberCount, boolean hasBonusNumber
    ) {
        Lotto lotto = new Lotto(lottoNumbers);

        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

//        MatchCounter matchCounter = lotto.getMatchDto(winningLotto);
//
//        assertThat(matchCounter.winningNumberCount()).isEqualTo(winningNumberCount);
//        assertThat(matchCounter.hasBonusNumber()).isEqualTo(hasBonusNumber);
    }

    public static Stream<Arguments> getLottoNumbersInputData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 10, 11, 12), 3, false),
                Arguments.of(List.of(1, 2, 3, 7, 10, 11), 3, true),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6, false)
        );
    }

}