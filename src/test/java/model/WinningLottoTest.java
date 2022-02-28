package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.DuplicatedLottoNumbersException;
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

    private static final LottoNumber BONUS = new LottoNumber(7);
    private static final Lotto WINNING_LOTTO_NUMBERS = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
    private static final Lotto FIRST_PRIZE_LOTTO_NUMBERS = Lotto.of(
        List.of(1, 2, 3, 4, 5, 6));
    private static final Lotto SECOND_PRIZE_LOTTO_NUMBERS = Lotto.of(
        List.of(1, 2, 3, 4, 5, 7));
    private static final Lotto THIRD_PRIZE_LOTTO_NUMBERS = Lotto.of(
        List.of(1, 2, 3, 4, 5, 8));
    private static final Lotto FOURTH_PRIZE_LOTTO_NUMBERS = Lotto.of(
            List.of(1, 2, 3, 4, 10, 11));
    private static final Lotto FIFTH_PRIZE_LOTTO_NUMBERS = Lotto.of(
            List.of(1, 2, 3, 10, 11, 12));
    private static final Lotto NOTHING_PRIZE_LOTTO_NUMBERS = Lotto.of(
        List.of(1, 2, 9, 11, 12, 13));
    private static final Money FIRST_PRIZE = LottoRank.FIRST.multiplePrizeBy(1);
    private static final Money SECOND_PRIZE = LottoRank.SECOND.multiplePrizeBy(1);
    private static final Money THIRD_PRIZE = LottoRank.THIRD.multiplePrizeBy(1);
    private static final Money FOURTH_PRIZE = LottoRank.FOURTH.multiplePrizeBy(1);
    private static final Money FIFTH_PRIZE = LottoRank.FIFTH.multiplePrizeBy(1);

    private WinningLottoNumbers winningLottoNumbers;

    @BeforeEach
    void setUp() {
        winningLottoNumbers = new WinningLottoNumbers(WINNING_LOTTO_NUMBERS, BONUS);
    }


    @Test
    @DisplayName("당첨번호와 보너스 번호 정상적으로 생성")
    void createValidWinningLottoNumbers() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        assertThatCode(() -> new WinningLottoNumbers(lotto, bonusNumber))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호 중복 생성 예외 발생")
    void duplicatedWinningLottoNumbers() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(1);
        assertThatThrownBy(() -> new WinningLottoNumbers(lotto, bonusNumber))
            .isInstanceOf(DuplicatedLottoNumbersException.class);
    }

    @Test
    @DisplayName("1등 판독 테스트")
    void firstPrize() {
        LottoResult result = winningLottoNumbers.summarize(List.of(FIRST_PRIZE_LOTTO_NUMBERS), new Money(1000));
        assertThat(result.getProfitRate()).isEqualTo(FIRST_PRIZE.divide(new Money(1000)));
        assertThat(result.getCountByRank(LottoRank.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("2등 판독 테스트")
    void secondPrize() {
        LottoResult result = winningLottoNumbers.summarize(List.of(SECOND_PRIZE_LOTTO_NUMBERS), new Money(1000));
        assertThat(result.getProfitRate()).isEqualTo(SECOND_PRIZE.divide(new Money(1000)));
        assertThat(result.getCountByRank(LottoRank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("3등 판독 테스트")
    void thirdPrize() {
        LottoResult result = winningLottoNumbers.summarize(List.of(THIRD_PRIZE_LOTTO_NUMBERS), new Money(1000));
        assertThat(result.getProfitRate()).isEqualTo(THIRD_PRIZE.divide(new Money(1000)));
        assertThat(result.getCountByRank(LottoRank.THIRD)).isEqualTo(1);
    }

    @Test
    @DisplayName("4등 판독 테스트")
    void fourthPrize() {
        LottoResult result = winningLottoNumbers.summarize(List.of(FOURTH_PRIZE_LOTTO_NUMBERS), new Money(1000));
        assertThat(result.getProfitRate()).isEqualTo(FOURTH_PRIZE.divide(new Money(1000)));
        assertThat(result.getCountByRank(LottoRank.FOURTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("5등 판독 테스트")
    void fifthPrize() {
        LottoResult result = winningLottoNumbers.summarize(List.of(FIFTH_PRIZE_LOTTO_NUMBERS), new Money(1000));
        assertThat(result.getProfitRate()).isEqualTo(FIFTH_PRIZE.divide(new Money(1000)));
        assertThat(result.getCountByRank(LottoRank.FIFTH)).isEqualTo(1);
    }

    @ParameterizedTest(name = "꽝 판독 테스트 : 로또 번호 - {0}")
    @MethodSource("provideLottoNumbersList")
    @DisplayName("꽝 판독 테스트")
    void nothingPrize(Lotto lotto) {
        LottoResult result = winningLottoNumbers.summarize(List.of(lotto), new Money(1000));
        assertThat(result.getProfitRate()).isEqualTo(new BigDecimal(0));
        assertThat(result.getCountByRank(LottoRank.NOTHING)).isEqualTo(1);
    }

    private static Stream<Arguments> provideLottoNumbersList() {
        return Stream.of(
            Arguments.of(Lotto.of(List.of(1, 2, 8, 9, 10, 11))),
            Arguments.of(Lotto.of(List.of(1, 2, 7, 9, 10, 11)))
        );
    }

    @Test
    @DisplayName("다양한 로또 순위 구하기")
    void summarize() {
        LottoResult result = winningLottoNumbers.summarize(
            List.of(FIRST_PRIZE_LOTTO_NUMBERS, FIRST_PRIZE_LOTTO_NUMBERS,
                SECOND_PRIZE_LOTTO_NUMBERS, THIRD_PRIZE_LOTTO_NUMBERS, THIRD_PRIZE_LOTTO_NUMBERS,
                NOTHING_PRIZE_LOTTO_NUMBERS),
            new Money(5000)
        );

        Money expectedPrize = FIRST_PRIZE.add(FIRST_PRIZE).add(SECOND_PRIZE).add(THIRD_PRIZE)
            .add(THIRD_PRIZE);
        assertThat(result.getProfitRate()).isEqualTo(expectedPrize.divide(new Money(5000)));
        assertThat(result.getCountByRank(LottoRank.FIRST)).isEqualTo(2);
        assertThat(result.getCountByRank(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.getCountByRank(LottoRank.THIRD)).isEqualTo(2);
        assertThat(result.getCountByRank(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(result.getCountByRank(LottoRank.FIFTH)).isEqualTo(0);
        assertThat(result.getCountByRank(LottoRank.NOTHING)).isEqualTo(1);
    }
}
