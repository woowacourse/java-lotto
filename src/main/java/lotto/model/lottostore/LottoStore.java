package lotto.model.lottostore;

import lotto.model.customer.PurchaseAmount;
import lotto.model.customer.PurchaseQuantity;
import lotto.model.lotto.LottoTickets;


public class LottoStore {
    public static LottoTickets buyLottoTickets(PurchaseAmount purchaseAmount, LottoTickets customLottoTickets) {
        LottoTickets lottoTickets = customLottoTickets.copy();
        PurchaseQuantity purchaseQuantity = purchaseAmount.calculatePurchaseQuantity(Price.LOTTO_TICKET_PRICE, customLottoTickets.size());

        lottoTickets.addAll(RandomLottoTicketsGenerator.generateLottoTickets(purchaseQuantity));
        return lottoTickets;
    }
}