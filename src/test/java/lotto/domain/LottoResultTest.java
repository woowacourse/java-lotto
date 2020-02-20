package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class LottoResultTest {
    @DisplayName("맞힌갯수와 등수 매치")
    @ParameterizedTest
    @CsvSource(value = {"3,FIFTH", "6,FIRST", "5,THIRD"})
    void findTest(int count, LottoResult values) {
        LottoResult lottoResult = LottoResult.findRank(count);
        assertThat(lottoResult == values).isTrue();
    }

	@DisplayName("최종 수익 계산")
	@Test
	void calculateTotalReward() {
        LottoResult.FIFTH.TicketCountPlus();
        LottoResult.FOURTH.TicketCountPlus();
        assertThat(LottoResult.calculateTotalReward()).isEqualTo(55000);
    }
}
