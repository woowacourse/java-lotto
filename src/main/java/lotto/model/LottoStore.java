package lotto.model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoStore {
    private static final int LOTTO_TICKET_PRICE = 1000;

    public static LottoTickets makeLottoTickets(CustomLottoNumbers customLottoNumbers, PurchaseAmount purchaseAmount) {
        int purchaseQuantity = purchaseAmount.calculatePurchaseQuantity(LOTTO_TICKET_PRICE);

        LottoTickets lottoTickets = toLottoTickets(customLottoNumbers);
        lottoTickets.add(RandomLottoTicketsGenerator.generateLottoTickets(purchaseQuantity));
        return lottoTickets;
    }

    private static LottoTickets toLottoTickets(CustomLottoNumbers customLottoNumbers) {
        List<LottoTicket> lottoTickets = customLottoNumbers.stream()
                .map(numbers -> numbers.stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()))
                .map(LottoTicket::new)
                .collect(Collectors.toList());

        return new LottoTickets(lottoTickets);
    }
}