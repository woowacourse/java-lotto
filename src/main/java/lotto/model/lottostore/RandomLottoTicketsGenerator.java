package lotto.model.lottostore;

import lotto.model.customer.PurchaseQuantity;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoNumberRepository;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoTicketsGenerator {
    private static final int COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final List<Integer> lottoNumbersAll = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());

    static LottoTickets generateLottoTickets(PurchaseQuantity purchaseQuantity) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; purchaseQuantity.isLessThanPurchaseQuantity(i); i++) {
            lottoTickets.add(generateRandomLottoTicket());
        }
        return LottoTickets.from(lottoTickets);
    }

    private static LottoTicket generateRandomLottoTicket() {
        Collections.shuffle(lottoNumbersAll);
        TreeSet<LottoNumber> lottoNumbers = lottoNumbersAll.stream()
                .limit(COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET)
                .map(LottoNumberRepository::fromNumber)
                .collect(Collectors.toCollection(TreeSet::new));

        return LottoTicket.from(lottoNumbers);
    }
}