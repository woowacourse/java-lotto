package lotto.domain.lotto.generator;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.purchase_info.PurchaseInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {

    private static final Random random = new Random();

    @Override
    public List<LottoTicket> createLottoTickets(PurchaseInfo purchaseInfo) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int autoCount = purchaseInfo.getAutoCount();
        for (int i = 0; i < autoCount; i++) {
            lottoTickets.add(generateLottoTicketOfRandom());
        }
        return lottoTickets;
    }

    private LottoTicket generateLottoTicketOfRandom() {
        Set<LottoNumber> lottoNumbers = random.ints(1, 46)
                .distinct()
                .limit(LottoTicket.LOTTO_TICKET_SIZE)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        return LottoTicket.from(lottoNumbers);
    }
}
