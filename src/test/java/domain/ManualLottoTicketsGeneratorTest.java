package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManualLottoTicketsGeneratorTest {
    @DisplayName("입력한 수동 로또 수 만큼 로또 티켓 생성하는지 테스트")
    @Test
    void generateManualLottoTicketsTest() {
        List<String> input = new ArrayList(Arrays.asList(
                "1, 2, 3, 4, 5, 6",
                "3, 4, 5, 6, 7, 8"
        ));
        ManualLottoTickets manualLottoTickets = new ManualLottoTickets(
                ManualLottoTicketsGenerator.generateManualLottoTickets(input));

        LottoTicket manualLottoTicket1 = new LottoTicket(
                new ArrayList(Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                ))
        );
        LottoTicket manualLottoTicket2 = new LottoTicket(
                new ArrayList(Arrays.asList(
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6),
                        new LottoNumber(7),
                        new LottoNumber(8)
                ))
        );
        Assertions.assertThat(manualLottoTickets.getTickets()).containsExactly(manualLottoTicket1,
                manualLottoTicket2);
    }
}
