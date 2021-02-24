package lotto.domain.machine;

import lotto.domain.Money;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualLottoMachineTest {
    private ManualLottoMachine manualLottoMachine = new ManualLottoMachine(new Money(1000));
    private List<LottoNumbers> lottoNumbersBundle;
    private int expectedSize;

    @BeforeEach
    void setUp() {
        List<List<Integer>> testNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(2, 3, 4, 5, 6, 7),
                Arrays.asList(2, 3, 4, 5, 6, 7),
                Arrays.asList(10, 11, 13, 14, 15, 16));
        expectedSize = testNumbers.size();

        lottoNumbersBundle = testNumbers.stream()
                .map(LottoNumbers::new)
                .collect(Collectors.toList());

    }

    @DisplayName("수동 로또가 생성되는지 확인")
    @Test
    void createTicketsByMoneyTest() {
        LottoTickets lottoTickets = manualLottoMachine.createTickets(expectedSize, lottoNumbersBundle);

        assertThat(lottoTickets.size()).isEqualTo(expectedSize);
        assertThat(lottoTickets.list().get(0)).isEqualTo(LottoTicket.createLottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @DisplayName("수동 로또 개수 예외처리 확인")
    @Test
    void 수동_로또_개수_예외처리() {
        assertThatThrownBy(() -> manualLottoMachine.createTickets(expectedSize - 1, lottoNumbersBundle)).
                isInstanceOf(IllegalArgumentException.class);
    }
}