package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HeuristicTest {

    @ParameterizedTest(name = "수익률: {0}, 기대값: {1}")
    @CsvSource({
            "1.5, 이익",
            "1.0, 본전",
            "0.5, 손해"
    })
    void 수익률에_따라_이익여부_판별한다(double ROI, String expected) {
        Assertions.assertThat(Heuristic.determine(ROI))
                .isEqualTo(expected);
    }
}