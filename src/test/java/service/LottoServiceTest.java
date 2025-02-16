package service;

import domain.AnswerLotto;
import domain.Lotto;
import domain.Lottos;
import domain.enums.Prize;
import domain.numbergenerator.NumberGenerator;
import domain.numbergenerator.TestNumberGenerator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoServiceTest {
    LottoService lottoService;
    static final Lotto basicLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    @BeforeEach
    void initLottoService() {
        lottoService = new LottoService();
    }

    @DisplayName("getAnswerLotto() 메서드가 의도대로 동작하는지 테스트한다.")
    @Test
    void getAnswerLotto_테스트() {
        // given
        List<Integer> selectedNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        // when
        AnswerLotto answerLotto = lottoService.getAnswerLotto(selectedNumbers, bonusNumber);

        // then
        Assertions.assertThat(answerLotto).isInstanceOf(AnswerLotto.class);
    }

    static Stream<Arguments> provideCalculatePrizeTestArgs() {
        return Stream.of(
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 10, 11, 12, 13)), Prize.MISS),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 11, 12, 13)), Prize.FIFTH),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 4, 12, 13)), Prize.FOURTH),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 13)), Prize.THIRD),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 7)), Prize.SECOND),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 6)), Prize.FIRST)

        );
    }

    static Stream<Arguments> provideCalculateRateOfReturnTestArgs() {
        return Stream.of(
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 10, 11, 12, 13)), 0.0),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 11, 12, 13)), 5.0),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 4, 12, 13)), 50.0),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 13)), 150.0),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 7)), 30000.0),
                Arguments.arguments(new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 6)), 2000000.0)
        );
    }

    @DisplayName("calculatePrize() 메서드가 의도대로 동작하는지 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCalculatePrizeTestArgs")
    void calculatePrize_테스트(NumberGenerator numberGenerator, Prize expectedPrize) {
        // given
        AnswerLotto answerLotto = new AnswerLotto(basicLotto, 7);
        Lottos lottos = Lottos.of(numberGenerator, 1);

        // when
        lottoService.calculatePrize(answerLotto, lottos);

        // then
        Map<Prize, Integer> prizeResult = lottoService.getPrizeResult();
        Assertions.assertThat(prizeResult.get(expectedPrize)).isEqualTo(1);
    }

    @DisplayName("calculateRateOfReturn() 메서드가 의도대로 동작하는지 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCalculateRateOfReturnTestArgs")
    void calculateRateOfReturn_테스트(NumberGenerator numberGenerator, double expectedRateOfReturn) {
        // given
        AnswerLotto answerLotto = new AnswerLotto(basicLotto, 7);
        Lottos lottos = Lottos.of(numberGenerator, 1);
        lottoService.calculatePrize(answerLotto, lottos);

        // when
        double actualRateOfReturn = lottoService.calculateRateOfReturn();

        // then
        Assertions.assertThat(actualRateOfReturn).isEqualTo(expectedRateOfReturn);
    }
}