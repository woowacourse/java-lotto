package model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class InvestmentOutcomeTest {

    @ParameterizedTest(name = "수익률: {0}, 기대값: {1}")
    @CsvSource({
            "1.5, 이익",
            "1.0, 본전",
            "0.5, 손해"
    })
    void 수익률에_따라_이익여부_판별한다(double returnOfInvestmentResult, String expected) {
        assertThat(InvestmentOutcome.determine(returnOfInvestmentResult))
                .isEqualTo(expected);
    }
}