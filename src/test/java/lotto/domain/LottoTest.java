package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    private static final WinningLotto winningLotto = new WinningLotto(new Lotto("1,2,3,4,5,6"), 7);

    private static Stream<Arguments> provideLottoNumbersAndRank() {
        return Stream.of(
                Arguments.of("2,4,8,9,13,25", "NONE"),
                Arguments.of("2,4,7,9,13,25", "FIFTH"),
                Arguments.of("2,4,6,1,7,3", "SECOND"),
                Arguments.of("1,2,3,4,5,6", "FIRST")
        );
    }

    @ParameterizedTest
    @DisplayName("로또 매칭 확인")
    @MethodSource("provideLottoNumbersAndRank")
    void test(String lottoNumbers, String lottoRank) {
        Lotto lotto = new Lotto(lottoNumbers);
        LottoRank rank = winningLotto.getLottoResult(lotto);
        assertThat(rank.name()).isEqualTo(lottoRank);
    }
}
