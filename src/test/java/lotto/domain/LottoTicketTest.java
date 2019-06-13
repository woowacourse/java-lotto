package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketTest {
    @Test
    void 번호_갯수_오류() {
        List<Integer> lottoNumbers = Arrays.asList(
                1, 2, 3, 4, 5
        );
        assertThrows(IllegalArgumentException.class, () -> {
            LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        });
    }

    @Test
    void 같은번호_갯수() {
        List<Integer> lottoNumbersFirst = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );

        List<Integer> lottoNumbersSecond = Arrays.asList(
                1, 2, 3, 4, 5, 7
        );

        LottoTicket lottoTicket1 = new LottoTicket(lottoNumbersFirst);
        LottoTicket lottoTicket2 = new LottoTicket(lottoNumbersSecond);
        assertThat(lottoTicket1.getSameCount(lottoTicket2)).isEqualTo(5);
    }

    @Test
    void 중복된_번호_오류() {
        List<Integer> lottoNumbersFirst = Arrays.asList(
                1, 1, 3, 4, 5, 6
        );

        assertThrows(IllegalArgumentException.class, () -> {
            new LottoTicket(lottoNumbersFirst);
        });
    }

    @Test
    void to_string() {
        List<Integer> lottoNumbersFirst = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );
        System.out.println(new LottoTicket(lottoNumbersFirst));
    }
}