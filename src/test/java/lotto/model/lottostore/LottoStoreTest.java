package lotto.model.lottostore;

import lotto.model.customer.PurchaseAmount;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoNumberRepository;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoStoreTest {
    PurchaseAmount purchaseAmount = PurchaseAmount.from(5000);
    List<LottoTicket> lottoTickets;
    List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumberRepository.fromNumber(1)
            , LottoNumberRepository.fromNumber(2)
            , LottoNumberRepository.fromNumber(3)
            , LottoNumberRepository.fromNumber(4)
            , LottoNumberRepository.fromNumber(5)
            , LottoNumberRepository.fromNumber(45));

    @BeforeEach
    void setup() {
        lottoTickets = new ArrayList<>();
    }

    @Test
    void _5000원_수동_0개일_때_로또개수_확인() {
        assertThat(LottoStore.buyLottoTickets(purchaseAmount, LottoTickets.from(lottoTickets)).size()).isEqualTo(5);
    }

    @Test
    void _5000원_수동_3개일_때_로또개수_확인() {
        for (int i = 0; i < 3; i++) {
            lottoTickets.add(LottoTicket.from(new TreeSet<>(lottoNumbers)));
        }

        assertThat(LottoStore.buyLottoTickets(purchaseAmount, LottoTickets.from(lottoTickets)).size()).isEqualTo(5);
    }

    @Test
    void _5000원_수동_5개일_때_로또개수_확인() {
        for (int i = 0; i < 5; i++) {
            System.out.println(new TreeSet<>(lottoNumbers));
            lottoTickets.add(LottoTicket.from(new TreeSet<>(lottoNumbers)));
        }

        assertThat(LottoStore.buyLottoTickets(purchaseAmount, LottoTickets.from(lottoTickets)).size()).isEqualTo(5);
    }

    @AfterEach
    void tearDown() {
        lottoTickets = null;
    }
}
