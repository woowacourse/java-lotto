package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketTest {
    @Test
    @DisplayName("숫자를 가지고 있는지 판단")
    void isHasNumber() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        Ticket ticket = new Ticket(lottoNumbers);
        assertThat(ticket.contains(new LottoNumber(1))).isTrue();
    }
}
