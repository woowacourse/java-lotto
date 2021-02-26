package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.Money;
import lotto.domain.lotto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoTicketGeneratorTest {

    @Test
    @DisplayName("수동 구매를 하고 남은 수량을 자동 구매 한다.")
    public void create_lotto_ticket_by_purchasing_auto_after_manual() {
        LottoTicketBuyingRequest lottoTicketBuyingRequest = new LottoTicketBuyingRequest(new Money(10000),
                new LottoAmount(5));

        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2), new LottoNumber(3), new LottoNumber(4)
                , new LottoNumber(5), new LottoNumber(14)
        );
        LottoLine lottoLine = new LottoLine(lottoNumbers);
        for (int i = 0; i < lottoTicketBuyingRequest.getManualLottoAmount(); i++) {
            lottoTicketBuyingRequest.submitManualLottoLine(lottoLine);
        }

        LottoTicket lottoTicket = LottoTicketGenerator.getInstance().createLottoTicket(lottoTicketBuyingRequest);
        assertThat(lottoTicket.getAutoLottoSize()).isEqualTo(5);
        assertThat(lottoTicket.getManualLottoSize()).isEqualTo(5);
    }

    @Test
    @DisplayName("수동 구매 개수가 0일 경우 전부 자동 구매를 한다.")
    public void create_lotto_ticket_when_manual_zero() {
        LottoTicketBuyingRequest lottoTicketBuyingRequest = new LottoTicketBuyingRequest(new Money(10000),
                new LottoAmount(0));

        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2), new LottoNumber(3), new LottoNumber(4)
                , new LottoNumber(5), new LottoNumber(14)
        );
        LottoLine lottoLine = new LottoLine(lottoNumbers);
        for (int i = 0; i < lottoTicketBuyingRequest.getManualLottoAmount(); i++) {
            lottoTicketBuyingRequest.submitManualLottoLine(lottoLine);
        }

        LottoTicket lottoTicket = LottoTicketGenerator.getInstance().createLottoTicket(lottoTicketBuyingRequest);
        assertThat(lottoTicket.getAutoLottoSize()).isEqualTo(10);
        assertThat(lottoTicket.getManualLottoSize()).isEqualTo(0);
    }

    @Test
    @DisplayName("전부 수동 구매를 할 경우 테스트")
    public void create_lotto_ticket_when_manual_full() {
        LottoTicketBuyingRequest lottoTicketBuyingRequest = new LottoTicketBuyingRequest(new Money(10000),
                new LottoAmount(10));

        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2), new LottoNumber(3), new LottoNumber(4)
                , new LottoNumber(5), new LottoNumber(14)
        );
        LottoLine lottoLine = new LottoLine(lottoNumbers);
        for (int i = 0; i < lottoTicketBuyingRequest.getManualLottoAmount(); i++) {
            lottoTicketBuyingRequest.submitManualLottoLine(lottoLine);
        }

        LottoTicket lottoTicket = LottoTicketGenerator.getInstance().createLottoTicket(lottoTicketBuyingRequest);
        assertThat(lottoTicket.getAutoLottoSize()).isEqualTo(0);
        assertThat(lottoTicket.getManualLottoSize()).isEqualTo(10);
    }
}

