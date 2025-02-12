import java.util.List;

public class LottoController {

    private final LottoConsoleView lottoConsoleView;
    private final LottoStore lottoStore;
    private final LottoCustomerHistory lottoCustomerHistory;

    public LottoController(LottoConsoleView lottoConsoleView, LottoStore lottoStore,
            LottoCustomerHistory lottoCustomerHistory) {
        this.lottoConsoleView = lottoConsoleView;
        this.lottoStore = lottoStore;
        this.lottoCustomerHistory = lottoCustomerHistory;
    }

    public void run() {
        buy();
    }

    private void buy() {
        int purchaseAmount = lottoConsoleView.requestPurchaseAmount();
        List<LottoTicket> tickets = lottoStore.buy(purchaseAmount);
        lottoCustomerHistory.add(new LottoCustomer(tickets, purchaseAmount));

        lottoConsoleView.printPurchaseCount(tickets.size());
    }

}
