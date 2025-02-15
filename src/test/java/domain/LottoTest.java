package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
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
        WinningLotto winningLotto = new WinningLotto(
                List.of(1, 2, 3, 4, 5, 6), 7
        );

        Matcher matcher = Matcher.count(winningLotto, lotto);

        assertThat(matcher.getWinningNumberCount()).isEqualTo(winningNumberCount);
        assertThat(matcher.isHasBonusNumber()).isEqualTo(hasBonusNumber);
    }

    public static Stream<Arguments> getLottoNumbersInputData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 10, 11, 12), 3, false),
                Arguments.of(List.of(1, 2, 3, 7, 10, 11), 3, true),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6, false)
        );
    }

    @Test
    void 로또_번호가_중복되면_예외를_던진다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }


}