package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.domain.generator.LottoAutoNumberGenerator;
import lotto.domain.generator.LottoManualNumberGenerator;
import lotto.utility.NumberListTranslator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    public static final List<Number> WINNING_NUMBERS =
        NumberListTranslator.translateIntToNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
    public static final Number BONUS_NUMBER = Number.from(7);

    @Test
    @DisplayName("구입한 로또 매수만큼 로또 생성")
    void createLottos() {
        int expectedPieceNumber = 14;
        Money expectedPurchaseMoney = new Money(Lotto.PRICE * expectedPieceNumber);
        Piece expectedLottoPiece = new Piece(expectedPurchaseMoney, expectedPieceNumber);
        LottoAutoNumberGenerator lottoAutoGenerator = new LottoAutoNumberGenerator();
        Lottos lottos = Lottos.generateLottos(lottoAutoGenerator, expectedLottoPiece);
        assertThat(lottos.getSize()).isEqualTo(expectedPieceNumber);
    }

    private static Stream<Arguments> provideLottosResult() {
        return Stream.of(
            Arguments.of("2, 4, 8, 9, 13, 25", LottoRank.NONE),
            Arguments.of("2, 4, 6, 8, 13, 25", LottoRank.FIFTH),
            Arguments.of("2, 4, 6, 1, 7, 3", LottoRank.SECOND),
            Arguments.of("1, 2, 3, 4, 5, 6", LottoRank.FIRST)
        );
    }

    @ParameterizedTest
    @DisplayName("당첨 통계 결과 수합")
    @MethodSource("provideLottosResult")
    void lottosResult(String exampleLotto, LottoRank exampleRank) {
        int expectedPieceNumber = 1;
        Money expectedMoney = new Money(Lotto.PRICE * expectedPieceNumber);
        Piece expectedPiece = new Piece(expectedMoney, expectedPieceNumber);
        List<List<Number>> manualLottoNumbers = manualLottoNumbers(exampleLotto);
        LottoManualNumberGenerator lottoManualGenerator =
            new LottoManualNumberGenerator(manualLottoNumbers);
        Lottos exampleLottos = Lottos.generateLottos(lottoManualGenerator, expectedPiece);
        LottoAnnouncement lottoAnnouncement = new LottoAnnouncement(WINNING_NUMBERS, BONUS_NUMBER);
        Map<LottoRank, Integer> exampleLottosResult =
            exampleLottos.getStatistics(lottoAnnouncement);
        assertThat(exampleLottosResult.get(exampleRank)).isEqualTo(expectedPieceNumber);
    }

    private List<List<Number>> manualLottoNumbers(String exampleLotto) {
        List<List<Number>> manualLottoNumbers = new ArrayList<>();
        List<Number> singleManualLottoNumbers = NumberListTranslator.parseToWinner(exampleLotto);
        manualLottoNumbers.add(singleManualLottoNumbers);
        return manualLottoNumbers;
    }
}
