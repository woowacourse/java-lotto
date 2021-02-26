package lotto.domain.ticketgenerator;

import lotto.domain.ticket.LottoTickets;
import lotto.domain.strategy.GenerateStrategy;

public class LottoGenerator {
    private GenerateStrategy generateStrategy;

    public void setGenerateStrategy(GenerateStrategy generateStrategy) {
        this.generateStrategy = generateStrategy;
    }

    public LottoTickets generateTickets() {
        return this.generateStrategy.generateTickets();
    }
}
