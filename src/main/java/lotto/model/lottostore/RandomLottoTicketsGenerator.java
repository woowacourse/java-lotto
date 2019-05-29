package lotto.model.lottostore;

import lotto.model.customer.PurchaseQuantity;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoTicketsGenerator {
    private static final int COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final List<LottoNumber> allLottoNumbers =
            IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                    .mapToObj(LottoNumber::new)
                    .collect(Collectors.toList());

    public static LottoTickets generateLottoTickets(PurchaseQuantity purchaseQuantity) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; purchaseQuantity.isLessThanPurchaseQuantity(i); i++) {
            lottoTickets.add(generateRandomLottoTicket());
        }
        return new LottoTickets(lottoTickets);
    }

    private static LottoTicket generateRandomLottoTicket() {
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> lottoNumbers = allLottoNumbers.stream()
                .limit(COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET)
                .collect(Collectors.toList());

        Collections.sort(lottoNumbers);
        return new LottoTicket(lottoNumbers);
    }
}