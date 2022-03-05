package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import exception.DuplicatedLottoNumbersException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {

    private static final LottoNumber BONUS = LottoNumber.of(7);
    private static final Lotto WINNING_LOTTO_NUMBERS = Lotto.of((List.of(1, 2, 3, 4, 5, 6)));
    private static final Lotto FIRST_PRIZE_LOTTO_NUMBERS = Lotto.of((List.of(1, 2, 3, 4, 5, 6)));
    private static final Lotto SECOND_PRIZE_LOTTO_NUMBERS = Lotto.of((List.of(1, 2, 3, 4, 5, 7)));
    private static final Lotto THIRD_PRIZE_LOTTO_NUMBERS = Lotto.of((List.of(1, 2, 3, 4, 5, 8)));
    private static final Lotto FOURTH_PRIZE_LOTTO_NUMBERS = Lotto.of((List.of(1, 2, 3, 4, 10, 11)));
    private static final Lotto FIFTH_PRIZE_LOTTO_NUMBERS = Lotto.of((List.of(1, 2, 3, 10, 11, 12)));

    private WinningLottoNumbers winningLottoNumbers;

    private static Stream<Arguments> provideLottoAndPrizeAndRank() {
        return Stream.of(
                Arguments.of(FIRST_PRIZE_LOTTO_NUMBERS,  LottoRank.FIRST),
                Arguments.of(SECOND_PRIZE_LOTTO_NUMBERS, LottoRank.SECOND),
                Arguments.of(THIRD_PRIZE_LOTTO_NUMBERS,  LottoRank.THIRD),
                Arguments.of(FOURTH_PRIZE_LOTTO_NUMBERS, LottoRank.FOURTH),
                Arguments.of(FIFTH_PRIZE_LOTTO_NUMBERS,  LottoRank.FIFTH)
        );
    }

    private static Stream<Arguments> provideLottoNumbersList() {
        return Stream.of(
                Arguments.of(Lotto.of((List.of(1, 2, 8, 9, 10, 11)))),
                Arguments.of(Lotto.of((List.of(1, 7, 8, 9, 10, 11)))),
                Arguments.of(Lotto.of(List.of(7,8,9,10,11,12)))
        );
    }

    @BeforeEach
    void setUp() {
        winningLottoNumbers = new WinningLottoNumbers(WINNING_LOTTO_NUMBERS, BONUS);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호 정상적으로 생성")
    void createValidWinningLottoNumbers() {
        Lotto lotto = Lotto.of((List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonusNumber = LottoNumber.of(7);
        assertThatCode(() -> new WinningLottoNumbers(lotto, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호 중복 생성 예외 발생")
    void duplicatedWinningLottoNumbers() {
        Lotto lotto = Lotto.of((List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonusNumber = LottoNumber.of(1);
        assertThatThrownBy(() -> new WinningLottoNumbers(lotto, bonusNumber))
                .isInstanceOf(DuplicatedLottoNumbersException.class);
    }

    @ParameterizedTest(name = "{2} 판독")
    @MethodSource("provideLottoAndPrizeAndRank")
    @DisplayName("당첨 판독 테스트")
    void prizeCountTest(Lotto lotto, LottoRank rank) {
        LottoRank actualRank = winningLottoNumbers.getRankBy(lotto);
        assertThat(actualRank).isEqualTo(rank);
    }

    @ParameterizedTest(name = "꽝 판독 테스트 : 로또 번호 - {0}")
    @MethodSource("provideLottoNumbersList")
    @DisplayName("꽝 판독 테스트")
    void nothingPrize(Lotto lotto) {
        LottoRank rank = winningLottoNumbers.getRankBy(lotto);
        assertThat(rank).isEqualTo(LottoRank.NOTHING);
    }
}
