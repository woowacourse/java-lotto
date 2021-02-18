package lotto.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import lotto.domain.lotto.Rank;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    void getEarningRate() {
        Map<Rank, Integer> resultMap = new HashMap<>();
        resultMap.put(Rank.FIRST_PLACE, 1);

        Result result = new Result(resultMap, BigInteger.valueOf(1000));

        assertThat(result.getEarningRate()).isEqualTo(BigInteger.valueOf(200_000_000));
    }
}