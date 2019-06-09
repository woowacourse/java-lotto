package lotto.domain.machine;

import java.util.List;

public class Purchase {
    private final int manualTicketQuantity;
    private final List<ManualNumbers> multipleManualNumbers;

    private Purchase(int manualTicketQuantity, List<ManualNumbers> multipleManualNumbers) {
        this.manualTicketQuantity = manualTicketQuantity;
        this.multipleManualNumbers = multipleManualNumbers;
    }

    static public Purchase of(int manualTicketQuantity, List<ManualNumbers> multipleManualNumbers) {
        return new Purchase(manualTicketQuantity, multipleManualNumbers);
    }
}
