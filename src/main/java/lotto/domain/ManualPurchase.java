package lotto.domain;

import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class ManualPurchase {
    private ManualPurchaseCount manualPurchaseCount;

    private List<LottoTicket> manualTickets = new ArrayList<>();

    public ManualPurchase(final ManualPurchaseCount manualPurchaseCount, final List<String> manualNumbers) {
        generateManualTicketList(manualNumbers);
        this.manualPurchaseCount = manualPurchaseCount;
    }

    private void generateManualTicketList(final List<String> manualNumbers) {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        for (String manualNumber : manualNumbers) {
            manualTickets.add(new LottoTicket(numberGenerator.generateNumbers(manualNumber)));
        }
    }

    public int getManualPurchaseCount() {
        return manualPurchaseCount.getPurchasedCount();
    }

    public List<LottoTicket> getManualTickets() {
        return manualTickets;
    }
}
