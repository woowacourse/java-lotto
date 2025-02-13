package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReturnRatioGeneratorTest {

    @DisplayName("구매금액과 당첨 결과를 알려주면 수익률을 계산해준다.")
    @Test
    void calculateReturnRatio() {
        int money = 14000;
        WinningResultResponses responses = new WinningResultResponses(
                List.of(new WinningResultResponse(3, 5000, false, 1)));

        assertThat(ReturnRatioGenerator.calculateReturnRatio(money, responses)).isEqualTo(0.35);
    }

}
