package domain.lotto;

import lotto.domain.Money;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicketBuyingRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LottoTicketBuyingRequestTest {

    @Test
    @DisplayName("수동 구매로또의 개수는 총 로또 구매 개수를 초과해서는 안된다.")
    public void number_of_manual_lotto_numbers_must_not_exceed_total() {
        Money money = new Money("2000");
        List<LottoLine> manualLottoLineList = Arrays.asList(
                new LottoLine( Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2), new LottoNumber(3), new LottoNumber(4)
                , new LottoNumber(5), new LottoNumber(9))),

                new LottoLine( Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2), new LottoNumber(3), new LottoNumber(4)
                        , new LottoNumber(5), new LottoNumber(14))),

                new LottoLine( Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2), new LottoNumber(3), new LottoNumber(4)
                        , new LottoNumber(5), new LottoNumber(14)))
                );



        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    LottoTicketBuyingRequest lottoTicketBuyingRequest = new LottoTicketBuyingRequest(money, 3);
                });
    }
}
