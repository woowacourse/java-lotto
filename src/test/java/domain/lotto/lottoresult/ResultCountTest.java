package domain.lotto.lottoresult;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultCountTest {
    @Test
    void 생성_테스트() {
        ResultCount resultCount = new ResultCount(0);
        Assertions.assertThat(resultCount).hasFieldOrPropertyWithValue("count", 0L);
    }

    @Test
    void sum_테스트() {
        ResultCount resultCount = new ResultCount(1);
        ResultCount resultCount1 = new ResultCount(2);
        ResultCount resultCount2 = ResultCount.sum(resultCount, resultCount1);
        Assertions.assertThat(resultCount2).hasFieldOrPropertyWithValue("count", 3L);
    }

    @Test
    void equals_테스트() {
        ResultCount resultCount1 = new ResultCount(1);
        ResultCount resultCount2 = new ResultCount(1);
        Assertions.assertThat(resultCount1).isEqualTo(resultCount2);
    }

    @Test
    void rank별_당첨금액_확인() {
        ResultCount resultCount = new ResultCount(1);
        Assertions.assertThat(resultCount.multiply(5_000)).isEqualTo(5_000);
    }
}
