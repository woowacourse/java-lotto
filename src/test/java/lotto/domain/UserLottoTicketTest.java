package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserLottoTicketTest {
    @Test
    void 번호_갯수_오류() {
        List<Integer> lottoNumbers = Arrays.asList(
                1,2,3,4,5
        );
        assertThrows(IllegalArgumentException.class, () -> {
            UserLottoTicket userLottoTicket = new UserLottoTicket(lottoNumbers);
        });
    }

    @Test
    void 같은번호_갯수() {
        List<Integer> lottoNumbersFirst = Arrays.asList(
                1,2,3,4,5,6
        );

        List<Integer> lottoNumbersSecond = Arrays.asList(
                1,2,3,4,5,7
        );

        UserLottoTicket userLottoTicket1 = new UserLottoTicket(lottoNumbersFirst);
        UserLottoTicket userLottoTicket2 = new UserLottoTicket(lottoNumbersSecond);
        assertThat(userLottoTicket1.getSameCount(userLottoTicket2)).isEqualTo(5);
    }
}