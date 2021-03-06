package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class ResultTest {
    private final List<Rank> sampleWins = Arrays.asList(Rank.FIFTH);

    @Test
    @DisplayName("Result가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Result(sampleWins))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("수익률 계산 확인")
    void findEarningRate() {
        final Money sampleMoney = new Money("14000");
        final Result result = new Result(sampleWins);
        assertThat(result.findEarningRate(sampleMoney)).isEqualTo("0.36");
    }
}
