package lotto.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.repository.LottoRepository;

public class PurchaseService {

    private final NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);

    private final LottoRepository lottoRepository;

    public PurchaseService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public List<LottoTicket> purchaseAndPersist(Money money) {
        List<LottoTicket> tickets = purchase(money);
        persist(tickets);
        return tickets;
    }

    private List<LottoTicket> purchase(Money money) {
        int ticketCount = createPurchasableCount(money);
        return createTickets(generator, ticketCount);
    }

    private int createPurchasableCount(Money money) {
        return money.divide(Money.wons(LottoTicket.PRICE)).getAmount();
    }

    private List<LottoTicket> createTickets(NumberGenerator generator, int ticketCount) {
        return IntStream.range(0, ticketCount)
            .mapToObj(i -> LottoTicket.createTicket(generator))
            .collect(Collectors.toList());
    }

    private void persist(List<LottoTicket> tickets) {
        lottoRepository.save(tickets);
    }
}
