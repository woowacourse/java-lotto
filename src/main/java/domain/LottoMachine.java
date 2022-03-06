package domain;

import domain.strategy.NumberGenerateStrategy;

public class LottoMachine {
    public static final int TICKET_PRICE = 1000;

    private final NumberGenerateStrategy numberGenerateStrategy;

    public LottoMachine(NumberGenerateStrategy numberGenerateStrategy) {
        this.numberGenerateStrategy = numberGenerateStrategy;
    }

    public LottoTickets purchaseTickets(LottoMoney purchaseMoney, LottoCount selfPurchaseCount,
                                        LottoTickets selfLottoTickets) {
        LottoMoney autoPurchaseMoney = purchaseMoney.purchaseSelfTicket(selfPurchaseCount.getValue());
        LottoTickets autoPurchaseTickets = LottoTickets.generateAutoTickets(autoPurchaseMoney, numberGenerateStrategy);
        return selfLottoTickets.concat(autoPurchaseTickets);
    }

    /*public LottoTickets purchaseSelfTickets(List<Set<Integer>> ticketNumbers) {
        return LottoTickets.fromTicketNumbers(ticketNumbers);
    }

    public LottoTickets purchaseAutoTickets(LottoMoney purchaseMoney) {
        return LottoTickets.generateAutoTickets(purchaseMoney, numberGenerateStrategy);
    }*/
}
