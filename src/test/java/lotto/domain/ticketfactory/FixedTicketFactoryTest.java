package lotto.domain.ticketfactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FixedTicketFactoryTest {

    @DisplayName("로또 번호를 생성했을 때, 서로 다른 6개 번호로 구성된 티켓이 반환되어야한다.")
    @Test
    void makeTicket() {
        Set<String> lottoNumbers = new HashSet<>(Arrays.asList("1","2","3","4","5","6"));
        LottoTicket lottoTicket = FixedTicketFactory.makeTicket(lottoNumbers);

        Set<Integer> actualLottoNumbers = new HashSet<>(Arrays.asList(1,2,3,4,5,6));
        assertThat(lottoTicket.getLottoNumbers()).isEqualTo(actualLottoNumbers);
    }
}
