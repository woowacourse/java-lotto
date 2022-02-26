package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    void 로또_구매_개수_판별() {
        LottoMachine lotto = LottoMachine.from(14000, new RandomLottoNumbersGenerator());
        assertThat(lotto.getTicketCount()).isEqualTo(14);
    }
}
