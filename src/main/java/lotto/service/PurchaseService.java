package lotto.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.config.RepositoryConfig;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.generator.NumberGenerator;
import lotto.repository.LottoRepository;
import lotto.repository.MoneyRepository;

public class PurchaseService {

    private final MoneyRepository moneyRepository;
    private final LottoRepository lottoRepository;

    public PurchaseService(MoneyRepository moneyRepository, LottoRepository lottoRepository) {
        this.moneyRepository = moneyRepository;
        this.lottoRepository = lottoRepository;
    }

    private static class PurchaseServiceHelper {
        private static final PurchaseService INSTANCE = new PurchaseService(
            RepositoryConfig.getMoneyRepository(),
            RepositoryConfig.getLottoRepository()
        );
    }

    public static PurchaseService getInstance() {
        return PurchaseServiceHelper.INSTANCE;
    }

    public List<LottoTicket> purchase(NumberGenerator generator, int ticketCount) {
        List<LottoTicket> tickets = purchaseLottoTickets(generator, ticketCount);
        saveTickets(tickets);
        return tickets;
    }

    private List<LottoTicket> purchaseLottoTickets(NumberGenerator generator, int ticketCount) {
        validatePurchasable(ticketCount);
        return IntStream.range(0, ticketCount)
            .mapToObj(i -> LottoTicket.createTicket(generator))
            .collect(Collectors.toList());
    }

    private void validatePurchasable(int ticketCount) {
        Money saved = moneyRepository.get();
        Money required = Money.from(LottoTicket.PRICE * ticketCount);
        if (!saved.isGreatThanOrEqualTo(required)) {
            throw new IllegalArgumentException(String.format(
                "보유한 %d원의 금액으로 %d개의 로또를 구매할 수 없습니다.", saved.getAmount(), ticketCount
            ));
        }
    }

    private void saveTickets(List<LottoTicket> tickets) {
        lottoRepository.save(tickets);
    }
}
