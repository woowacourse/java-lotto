package lotto.domain.ticketfactory;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FixedNumberTicketFactoryTest {

    @DisplayName("로또 번호를 생성했을 때, 서로 다른 6개 번호로 구성된 티켓이 반환되어야한다.")
    @Test
    void makeTicket() {
        Set<Integer> lottoNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Set<LottoNumber> actualLottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        LottoTicket lottoTicket = FixedNumberTicketFactory.makeTicket(actualLottoNumbers);

        assertThat(lottoTicket.getLottoNumbers()).isEqualTo(actualLottoNumbers);
    }
}
