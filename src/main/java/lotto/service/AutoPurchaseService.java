package lotto.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.config.RepositoryConfig;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.repository.LottoRepository;
import lotto.repository.MoneyRepository;

public class AutoPurchaseService {

    private final MoneyRepository moneyRepository;
    private final LottoRepository lottoRepository;

    public AutoPurchaseService(MoneyRepository moneyRepository, LottoRepository lottoRepository) {
        this.moneyRepository = moneyRepository;
        this.lottoRepository = lottoRepository;
    }

    private static class PurchaseServiceHelper {
        private static final AutoPurchaseService INSTANCE = new AutoPurchaseService(
            RepositoryConfig.getMoneyRepository(),
            RepositoryConfig.getLottoRepository()
        );
    }

    public static AutoPurchaseService getInstance() {
        return PurchaseServiceHelper.INSTANCE;
    }

    public List<LottoTicket> purchaseAll() {
        List<LottoTicket> tickets = purchaseLottoTickets();
        saveTickets(tickets);
        return tickets;
    }

    private List<LottoTicket> purchaseLottoTickets() {
        NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);
        int ticketCount = calculatePurchasableCount();
        return createTickets(generator, ticketCount);
    }

    private int calculatePurchasableCount() {
        Money money = moneyRepository.get();
        return money.divide(Money.from(LottoTicket.PRICE)).getAmount();
    }

    private List<LottoTicket> createTickets(NumberGenerator generator, int ticketCount) {
        return IntStream.range(0, ticketCount)
            .mapToObj(i -> LottoTicket.createTicket(generator))
            .collect(Collectors.toList());
    }

    private void saveTickets(List<LottoTicket> tickets) {
        lottoRepository.save(tickets);
    }
}
