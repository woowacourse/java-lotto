package lotto.domain.lotto.generator;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.purchase_info.PurchaseInfo;

import java.util.List;

public interface LottoGenerator {

    List<LottoTicket> createLottoTickets(PurchaseInfo purchaseInfo);

}
