package domain.lottoresult;

import domain.lottoresult.ResultCount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultCountTest {
    @Test
    void 생성_테스트(){
        ResultCount resultCount = new ResultCount();

        Assertions.assertThat(resultCount).hasFieldOrPropertyWithValue("count", 0);
    }

    @Test
    void add시_증가_테스트() {
        ResultCount resultCount = new ResultCount();
        resultCount.increase();
        resultCount.increase();

        Assertions.assertThat(resultCount).hasFieldOrPropertyWithValue("count", 2);
    }

    @Test
    void equals_테스트() {
        ResultCount resultCount1 = new ResultCount();
        ResultCount resultCount2 = new ResultCount();

        Assertions.assertThat(resultCount1).isEqualTo(resultCount2);

        resultCount1.increase();
        resultCount2.increase();
        Assertions.assertThat(resultCount1).isEqualTo(resultCount2);
    }

    @Test
    void rank별_당첨금액_확인() {
        ResultCount resultCount = new ResultCount();
        resultCount.increase();
        Assertions.assertThat(resultCount.multiply(5_000)).isEqualTo(5_000);
    }
}
