package lotto.domain;

import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.UserInputNumberGenerator;
import lotto.domain.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class ManualPurchase {
    private int manualCount;

    private List<LottoTicket> manualTickets = new ArrayList<>();

    public ManualPurchase(int manualCount, List<String> manualNumbers, Payment payment) {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();

        Validator.validatePositiveNumber(manualCount);
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
