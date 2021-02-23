package lotto.domain.ticketpurchase;

import java.util.Arrays;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.BeforeEach;


public class UserPurchaseTest {
    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        lottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.get(1),
                LottoNumbers.get(2),
                LottoNumbers.get(3),
                LottoNumbers.get(4),
                LottoNumbers.get(5),
                LottoNumbers.get(6)
            )
        );
    }


}
