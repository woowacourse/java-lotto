package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.numbergenerator.NumberGenerator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    public static Stream<Arguments> getLottoNumbersInputData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 10, 11, 12), 3, false),
                Arguments.of(List.of(1, 2, 3, 7, 10, 11), 3, true),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6, false)
        );
    }

    @MethodSource("getLottoNumbersInputData")
    @ParameterizedTest
    void 당첨_번호와_비교하여_일치_결과를_반환한다(
            List<Integer> lottoNumbers, int winningNumberCount, boolean hasBonusNumber) {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        Lotto lotto = Lotto.issueByNumberGenerator(new FixedNumberGenerator(lottoNumbers));

        MatchDto matchDto = lotto.getMatchDto(winningNumbers, bonusNumber);

        assertThat(matchDto.winningNumberCount()).isEqualTo(winningNumberCount);
        assertThat(matchDto.hasBonusNumber()).isEqualTo(hasBonusNumber);
    }

    public static class FixedNumberGenerator implements NumberGenerator {
        private final List<Integer> numbers;
        private int index = 0;

        public FixedNumberGenerator(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public int generate() {
            int generatedNumber = numbers.get(index);
            index += 1;

            return generatedNumber;
        }
    }

}