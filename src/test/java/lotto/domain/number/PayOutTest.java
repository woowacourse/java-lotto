package lotto.domain.number;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PayOutTest {

    @Test
    void inputNumber() {
        PayOut payOut = new PayOut(10000);
        assertThat(payOut.equals(10000)).isTrue();

        payOut = new PayOut("10000");
        assertThat(payOut.equals(10000)).isTrue();
    }
}

