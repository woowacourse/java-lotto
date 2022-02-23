package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.DuplicatedLottoNumbersException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoNumbersTest {

    private WinningLottoNumbers winningLottoNumbers;

    @BeforeEach
    void setUp() {
        winningLottoNumbers = new WinningLottoNumbers(LottoNumbers.withSixNumbers(1, 2, 3, 4, 5, 6),
            new LottoNumber(7));
    }


    @Test
    @DisplayName("당첨번호와 보너스 번호 정상적으로 생성")
    void createValidWinningLottoNumbers() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = new LottoNumber(7);
        assertThatCode(() -> new WinningLottoNumbers(lottoNumbers, bonusNumber))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호 중복 생성 예외 발생")
    void duplicatedWinningLottoNumbers() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = new LottoNumber(1);
        assertThatThrownBy(() -> new WinningLottoNumbers(lottoNumbers, bonusNumber))
            .isInstanceOf(DuplicatedLottoNumbersException.class);
    }

    @Test
    @DisplayName("1등 판독 테스트")
    void firstPrize() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 4, 5, 6);
        LottoRank rank = winningLottoNumbers.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("2등 판독 테스트")
    void secondPrize() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 4, 5, 7);
        LottoRank rank = winningLottoNumbers.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("3등 판독 테스트")
    void thirdPrize() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 4, 5, 8);
        LottoRank rank = winningLottoNumbers.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("4등 판독 테스트")
    void fourthPrize() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 4, 8, 9);
        LottoRank rank = winningLottoNumbers.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    @DisplayName("5등 판독 테스트")
    void fifthPrize() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 8, 9, 10);
        LottoRank rank = winningLottoNumbers.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
    }

    @ParameterizedTest(name = "꽝 판독 테스트 : 로또 번호 - {0}")
    @MethodSource("provideLottoNumbersList")
    @DisplayName("꽝 판독 테스트")
    void nothingPrize(LottoNumbers lottoNumbers) {
        LottoRank rank = winningLottoNumbers.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.NOTHING);
    }

    private static Stream<Arguments> provideLottoNumbersList() {
        return Stream.of(
            Arguments.of(LottoNumbers.withSixNumbers(1, 2, 8, 9, 10, 11)),
            Arguments.of(LottoNumbers.withSixNumbers(1, 2, 7, 9, 10, 11))
        );
    }
}
