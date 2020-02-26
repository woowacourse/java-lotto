package lotto.domain;

import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class ManualPurchase {
    private ManualPurchaseCount manualPurchaseCount;

    private List<LottoTicket> manualTickets = new ArrayList<>();

    public ManualPurchase(ManualPurchaseCount manualPurchaseCount, List<String> manualNumbers) {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        generateManualTicketList(manualNumbers, numberGenerator);
        this.manualPurchaseCount = manualPurchaseCount;
    }

    private void generateManualTicketList(List<String> manualNumbers, NumberGenerator numberGenerator) {
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
