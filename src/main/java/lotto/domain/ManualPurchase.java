package lotto.domain;

import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class ManualPurchase {
    private int manualCount;

    private List<LottoTicket> manualTickets = new ArrayList<>();

    public ManualPurchase(int manualCount, List<String> manualNumbers) {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        generateManualTicketList(manualNumbers, numberGenerator);
        this.manualCount = manualCount;
    }

    private void generateManualTicketList(List<String> manualNumbers, NumberGenerator numberGenerator) {
        for (String manualNumber : manualNumbers) {
            manualTickets.add(new LottoTicket(numberGenerator.generateNumbers(manualNumber)));
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public List<LottoTicket> getManualTickets() {
        return manualTickets;
    }
}
