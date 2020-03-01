package lotto.domain.lotto.generator;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.purchase_info.PurchaseInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoLottoGenerator implements LottoGenerator {

    private static final Random random = new Random();

    @Override
    public List<LottoTicket> generateLottoTickets(PurchaseInfo purchaseInfo) {
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
                .limit(6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        return LottoTicket.from(lottoNumbers);
    }
}
