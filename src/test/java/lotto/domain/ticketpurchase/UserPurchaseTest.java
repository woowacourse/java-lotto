package lotto.domain.ticketpurchase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserPurchaseTest {
    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        lottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.of(1),
                LottoNumbers.of(2),
                LottoNumbers.of(3),
                LottoNumbers.of(4),
                LottoNumbers.of(5),
                LottoNumbers.of(6)
            )
        );
    }


}
