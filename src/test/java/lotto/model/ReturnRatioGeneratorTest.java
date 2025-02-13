package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ReturnRatioGeneratorTest {
    @DisplayName("구매금액과 당첨 결과를 알려주면 수익률을 계산해준다.")
    @Test
    void 구매금액과_당첨_결과를_알려주면_수익률을_계산해준다() {
        int money = 14000;
        WinningResultResponses responses = new WinningResultResponses(List.of(new WinningResultResponse(3, 5000, false, 1)));
        assertThat(ReturnRatioGenerator.calculateReturnRatio(money, responses)).isEqualTo(0.35);
    }
}