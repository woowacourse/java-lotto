package lotto.model.lottostore;

import lotto.model.customer.CustomLottoNumbers;
import lotto.model.customer.PurchaseAmount;
import lotto.model.customer.PurchaseQuantity;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class LottoStore {
    public static LottoTickets makeLottoTickets(PurchaseAmount purchaseAmount, CustomLottoNumbers customLottoNumbers) {
        LottoTickets lottoTickets = toLottoTickets(customLottoNumbers);
        PurchaseQuantity purchaseQuantity = purchaseAmount.calculatePurchaseQuantity(Price.LOTTO_TICKET_PRICE, lottoTickets.size());

        lottoTickets.addAll(RandomLottoTicketsGenerator.generateLottoTickets(purchaseQuantity));
        return lottoTickets;
    }

    private static LottoTickets toLottoTickets(CustomLottoNumbers customLottoNumbers) {
        List<LottoTicket> lottoTickets = customLottoNumbers.stream()
                .map(numbers -> numbers.stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toCollection(TreeSet::new)))
                .map(LottoTicket::new)
                .collect(Collectors.toList());

        return new LottoTickets(lottoTickets);
    }
}