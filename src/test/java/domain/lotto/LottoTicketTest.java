package domain.lotto;

import static domain.lotto.LottoNumberTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    void testLottoTicket() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(1));
        numbers.add(new LottoNumber(2));
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(6));

        LottoTicket lottoTicket = new LottoTicket(numbers);
        assertThat(lottoTicket);
    }

    @Test
    void createLottoTicketThrowException() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(1));
        numbers.add(new LottoNumber(2));
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(5));

        assertThatThrownBy(() -> new LottoTicket(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
