package domain.lotto.lottoresult;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ResultCountTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("생성 테스트")
    void test1(int input) {
        Assertions.assertThatCode(() -> new ResultCount(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("sum 테스트")
    void test2() {
        ResultCount resultCount = new ResultCount(1);
        ResultCount resultCount1 = new ResultCount(2);
        ResultCount resultCount2 = ResultCount.sum(resultCount, resultCount1);
        Assertions.assertThat(resultCount2).hasFieldOrPropertyWithValue("count", 3L);
    }

    @Test
    @DisplayName("equals 테스트")
    void test3() {
        ResultCount resultCount1 = new ResultCount(1);
        ResultCount resultCount2 = new ResultCount(1);
        Assertions.assertThat(resultCount1).isEqualTo(resultCount2);
    }

    @Test
    @DisplayName("rank별 당첨금액 확인")
    void test4() {
        ResultCount resultCount = new ResultCount(1);
        Assertions.assertThat(resultCount.multiply(5_000)).isEqualTo(5_000);
    }
}
