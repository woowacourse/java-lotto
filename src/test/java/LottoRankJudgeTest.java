import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoRankJudgeTest {

    private LottoRankJudge lottoRankJudge;

    @BeforeEach
    void setUp() {
        lottoRankJudge = new LottoRankJudge(new LottoNumbers(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    @DisplayName("1등 판독 테스트")
    void firstPrize() {
        LottoNumbers lottoNumbers = new LottoNumbers(1, 2, 3, 4, 5, 6);
        LottoRank rank = lottoRankJudge.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("2등 판독 테스트")
    void secondPrize() {
        LottoNumbers lottoNumbers = new LottoNumbers(1, 2, 3, 4, 5, 7);
        LottoRank rank = lottoRankJudge.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("3등 판독 테스트")
    void thirdPrize() {
        LottoNumbers lottoNumbers = new LottoNumbers(1, 2, 3, 4, 5, 8);
        LottoRank rank = lottoRankJudge.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("4등 판독 테스트")
    void fourthPrize() {
        LottoNumbers lottoNumbers = new LottoNumbers(1, 2, 3, 4, 8, 9);
        LottoRank rank = lottoRankJudge.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    @DisplayName("5등 판독 테스트")
    void fifthPrize() {
        LottoNumbers lottoNumbers = new LottoNumbers(1, 2, 3, 8, 9, 10);
        LottoRank rank = lottoRankJudge.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
    }

    @ParameterizedTest(name = "꽝 판독 테스트 : 로또 번호 - {0}")
    @MethodSource("provideLottoNumbersList")
    @DisplayName("꽝 판독 테스트")
    void nothingPrize(LottoNumbers lottoNumbers) {
        LottoRank rank = lottoRankJudge.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.NOTHING);
    }

    private static Stream<Arguments> provideLottoNumbersList() {
        return Stream.of(
            Arguments.of(new LottoNumbers(1, 2, 8, 9, 10, 11)),
            Arguments.of(new LottoNumbers(1, 2, 7, 9, 10, 11))
        );
    }
}
