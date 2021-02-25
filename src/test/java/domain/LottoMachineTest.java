package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
    private Quantity quantity;

    @BeforeEach
    public void setUp() {
        quantity = new Quantity(new Money(12_000), 3);
    }

    @DisplayName("수동으로 로또 티켓을 구매한다.")
    @Test
    public void buyManualLottoTickets() {
        List<LottoTicket> lottoTickets = LottoMachine.buyManual(Arrays.asList(
                Arrays.asList(9, 12, 6, 2, 1, 8),
                Arrays.asList(19, 3, 5, 4, 7, 1),
                Arrays.asList(2, 3, 6, 8, 21, 4)
        ));

        assertThat(lottoTickets.size()).isEqualTo(3);
    }

    @DisplayName("자동으로 로또 티켓을 구매한다.")
    @Test
    public void buyAutoLottoTickets() {
        List<LottoTicket> lottoTickets = LottoMachine.buyAuto(quantity.auto());

        assertThat(lottoTickets.size()).isEqualTo(9);
    }
}
