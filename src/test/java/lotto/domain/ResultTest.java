package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    void getEarningRate() {
        Result result = new Result(
                new WinningNumbers("1,2,3,4,5,6", "7"),
                Arrays.asList(new LottoTicket("1,2,3,4,5,6")),
                BigInteger.valueOf(1000)
        );

        assertThat(result.getEarningRate()).isEqualTo(BigInteger.valueOf(200_000_000));
    }
}