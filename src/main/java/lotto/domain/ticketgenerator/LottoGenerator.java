package lotto.domain.ticketgenerator;

import lotto.domain.ticketgenerator.strategy.GenerateStrategy;
import lotto.domain.ticket.LottoTickets;

public class LottoGenerator {
    private GenerateStrategy generateStrategy;

    public void setGenerateStrategy(GenerateStrategy generateStrategy) {
        this.generateStrategy = generateStrategy;
    }

    public LottoTickets generateTickets() {
        return this.generateStrategy.generateTickets();
    }
}
