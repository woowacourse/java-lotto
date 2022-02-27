package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.model.exception.DuplicatedNumberException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinnerLottoTest {

    private static final LottoNumber BONUS = new LottoNumber(7);
    private static final Lotto WINNING_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
    private static final Lotto FIRST_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
    private static final Lotto SECOND_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 5, 7));
    private static final Lotto THIRD_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 5, 8));
    private static final Lotto FOURTH_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 10, 11));
    private static final Lotto FIFTH_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 10, 11, 12));
    private static final Lotto NOTHING_RANK_LOTTO = Lotto.create(List.of(1, 2, 9, 11, 12, 13));
    private static final Money FIRST_RANK_PRIZE = Rank.FIRST.getPrize();
    private static final Money SECOND_RANK_PRIZE = Rank.SECOND.getPrize();
    private static final Money THIRD_RANK_PRIZE = Rank.THIRD.getPrize();
    private static final Money FOURTH_RANK_PRIZE = Rank.FOURTH.getPrize();
    private static final Money FIFTH_RANK_PRIZE = Rank.FIFTH.getPrize();
    private static final Money THOUSAND_MONEY = new Money(1000);

    private WinnerLotto winnerLotto;

    @BeforeEach
    void setUp() {
        winnerLotto = new WinnerLotto(WINNING_LOTTO, BONUS);
    }


    @Test
    @DisplayName("당첨번호와 보너스 번호 정상적으로 생성")
    void createValidWinningLottoNumbers() {
        Lotto lotto = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = new LottoNumber(7);
        assertThatCode(() -> new WinnerLotto(lotto, bonus)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호 중복 생성 예외 발생")
    void duplicatedWinningLottoNumbers() {
        Lotto lotto = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = new LottoNumber(1);
        assertThatThrownBy(() -> new WinnerLotto(lotto, bonus))
            .isInstanceOf(DuplicatedNumberException.class);
    }

    @Test
    @DisplayName("1등 판독 테스트")
    void firstPrize() {
        Statistic statistic = winnerLotto.summarize(List.of(FIRST_RANK_LOTTO));
        BigDecimal profitRate = FIRST_RANK_PRIZE.divide(THOUSAND_MONEY);
        assertThat(statistic.getProfitRate()).isEqualTo(new ProfitRate(profitRate));
        assertThat(statistic.getCountByRank(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("2등 판독 테스트")
    void secondPrize() {
        Statistic result = winnerLotto.summarize(List.of(SECOND_RANK_LOTTO));
        BigDecimal profitRate = SECOND_RANK_PRIZE.divide(THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(new ProfitRate(profitRate));
        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("3등 판독 테스트")
    void thirdPrize() {
        Statistic result = winnerLotto.summarize(List.of(THIRD_RANK_LOTTO));
        BigDecimal profitRate = THIRD_RANK_PRIZE.divide(THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(new ProfitRate(profitRate));
        assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(1);
    }

    @Test
    @DisplayName("4등 판독 테스트")
    void fourthPrize() {
        Statistic result = winnerLotto.summarize(List.of(FOURTH_RANK_LOTTO));
        BigDecimal profitRate = FOURTH_RANK_PRIZE.divide(THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(new ProfitRate(profitRate));
        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("5등 판독 테스트")
    void fifthPrize() {
        Statistic result = winnerLotto.summarize(List.of(FIFTH_RANK_LOTTO));
        BigDecimal profitRate = FIFTH_RANK_PRIZE.divide(THOUSAND_MONEY);
        assertThat(result.getProfitRate()).isEqualTo(new ProfitRate(profitRate));
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(1);
    }

    @ParameterizedTest(name = "꽝 판독 테스트 : 로또 번호 - {0}")
    @MethodSource("provideLottoNumbersList")
    @DisplayName("꽝 판독 테스트")
    void nothingPrize(Lotto lottoNumbers) {
        Statistic result = winnerLotto.summarize(List.of(lottoNumbers));
        assertThat(result.getProfitRate().getDoubleValue())
            .isCloseTo(new ProfitRate(BigDecimal.ZERO).getDoubleValue(),
                Percentage.withPercentage(0.01));
        assertThat(result.getCountByRank(Rank.NOTHING)).isEqualTo(1);
    }

    private static Stream<Arguments> provideLottoNumbersList() {
        return Stream.of(
            Arguments.of(Lotto.create(List.of(1, 2, 8, 9, 10, 11))),
            Arguments.of(Lotto.create(List.of(1, 2, 7, 9, 10, 11)))
        );
    }

    @Test
    @DisplayName("다양한 로또 순위 구하기")
    void summarize() {
        Statistic result = winnerLotto.summarize(
            List.of(FIRST_RANK_LOTTO, FIRST_RANK_LOTTO,
                SECOND_RANK_LOTTO, THIRD_RANK_LOTTO,
                THIRD_RANK_LOTTO, NOTHING_RANK_LOTTO));

        Money expectedPrize = FIRST_RANK_PRIZE.plus(FIRST_RANK_PRIZE).plus(SECOND_RANK_PRIZE)
            .plus(THIRD_RANK_PRIZE).plus(THIRD_RANK_PRIZE);
        BigDecimal profitRate = expectedPrize.divide(new Money(6000));

        assertThat(result.getProfitRate()).isEqualTo(new ProfitRate(profitRate));
        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(2);
        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(2);
        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(0);
        assertThat(result.getCountByRank(Rank.NOTHING)).isEqualTo(1);
    }
}
