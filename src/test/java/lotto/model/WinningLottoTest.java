package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.DuplicatedNumberException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {

    private static final Number BONUS = new Number(7);
    private static final Lotto WINNING_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private static final Lotto FIRST_RANK_LOTTO = new Lotto(
        List.of(1, 2, 3, 4, 5, 6));
    private static final Lotto SECOND_RANK_LOTTO = new Lotto(
        List.of(1, 2, 3, 4, 5, 7));
    private static final Lotto THIRD_RANK_LOTTO = new Lotto(
        List.of(1, 2, 3, 4, 5, 8));
    private static final Lotto FOURTH_RANK_LOTTO = new Lotto(
        List.of(1, 2, 3, 4, 10, 11));
    private static final Lotto FIFTH_RANK_LOTTO = new Lotto(
        List.of(1, 2, 3, 10, 11, 12));
    private static final Lotto NOTHING_RANK_LOTTO = new Lotto(
        List.of(1, 2, 9, 11, 12, 13));
    private static final Money FIRST_RANK_PRIZE = Rank.FIRST.getPrize();
    private static final Money SECOND_RANK_PRIZE = Rank.SECOND.getPrize();
    private static final Money THIRD_RANK_PRIZE = Rank.THIRD.getPrize();
    private static final Money FOURTH_RANK_PRIZE = Rank.FOURTH.getPrize();
    private static final Money FIFTH_RANK_PRIZE = Rank.FIFTH.getPrize();
    private static final Money THOUSAND_MONEY = new Money(1000);

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(WINNING_LOTTO, BONUS);
    }


    @Test
    @DisplayName("당첨번호와 보너스 번호 정상적으로 생성")
    void createValidWinningLottoNumbers() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonus = new Number(7);
        assertThatCode(() -> new WinningLotto(lotto, bonus)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호 중복 생성 예외 발생")
    void duplicatedWinningLottoNumbers() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonus = new Number(1);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
            .isInstanceOf(DuplicatedNumberException.class);
    }

    @Test
    @DisplayName("1등 판독 테스트")
    void firstPrize() {
        Statistic statistic = winningLotto.summarize(List.of(FIRST_RANK_LOTTO), THOUSAND_MONEY);
        assertThat(statistic.getProfitRate()).isEqualTo(FIRST_RANK_PRIZE.divide(THOUSAND_MONEY));
        assertThat(statistic.getCountByRank(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("2등 판독 테스트")
    void secondPrize() {
        Statistic result = winningLotto.summarize(List.of(SECOND_RANK_LOTTO), THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(SECOND_RANK_PRIZE.divide(THOUSAND_MONEY));
        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("3등 판독 테스트")
    void thirdPrize() {
        Statistic result = winningLotto.summarize(List.of(THIRD_RANK_LOTTO), THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(THIRD_RANK_PRIZE.divide(THOUSAND_MONEY));
        assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(1);
    }

    @Test
    @DisplayName("4등 판독 테스트")
    void fourthPrize() {
        Statistic result = winningLotto.summarize(List.of(FOURTH_RANK_LOTTO), THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(FOURTH_RANK_PRIZE.divide(THOUSAND_MONEY));
        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("5등 판독 테스트")
    void fifthPrize() {
        Statistic result = winningLotto.summarize(List.of(FIFTH_RANK_LOTTO), THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(FIFTH_RANK_PRIZE.divide(THOUSAND_MONEY));
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(1);
    }

    @ParameterizedTest(name = "꽝 판독 테스트 : 로또 번호 - {0}")
    @MethodSource("provideLottoNumbersList")
    @DisplayName("꽝 판독 테스트")
    void nothingPrize(Lotto lottoNumbers) {
        Statistic result = winningLotto.summarize(List.of(lottoNumbers), THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(new BigDecimal(0));
        assertThat(result.getCountByRank(Rank.NOTHING)).isEqualTo(1);
    }

    private static Stream<Arguments> provideLottoNumbersList() {
        return Stream.of(
            Arguments.of(new Lotto(List.of(1, 2, 8, 9, 10, 11))),
            Arguments.of(new Lotto(List.of(1, 2, 7, 9, 10, 11)))
        );
    }

    @Test
    @DisplayName("다양한 로또 순위 구하기")
    void summarize() {
        Statistic result = winningLotto.summarize(
            List.of(FIRST_RANK_LOTTO, FIRST_RANK_LOTTO,
                SECOND_RANK_LOTTO, THIRD_RANK_LOTTO,
                THIRD_RANK_LOTTO, NOTHING_RANK_LOTTO),
            new Money(5000)
        );

        Money expectedPrize = FIRST_RANK_PRIZE.plus(FIRST_RANK_PRIZE).plus(SECOND_RANK_PRIZE).plus(
            THIRD_RANK_PRIZE)
            .plus(THIRD_RANK_PRIZE);
        assertThat(result.getProfitRate()).isEqualTo(expectedPrize.divide(new Money(5000)));
        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(2);
        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(2);
        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(0);
        assertThat(result.getCountByRank(Rank.NOTHING)).isEqualTo(1);
    }
}
