package lotto.domain.lotto;

import lotto.domain.lotto.generator.AutoLottoGenerator;
import lotto.domain.lotto.generator.LottoGenerator;
import lotto.domain.lotto.generator.ManualLottoGenerator;
import lotto.domain.purchase_info.PurchaseInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoFactory {

    private static final List<LottoGenerator> lottoGenerators =
            Arrays.asList(new AutoLottoGenerator(), new ManualLottoGenerator());

    private LottoFactory() {
    }

    public static LottoTickets publishLottoTickets(PurchaseInfo purchaseInfo) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (LottoGenerator lottoGenerator : lottoGenerators) {
            lottoTickets.addAll(lottoGenerator.createLottoTickets(purchaseInfo));
        }
        return LottoTickets.from(lottoTickets);
    }
}

