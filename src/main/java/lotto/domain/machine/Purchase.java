package lotto.domain.machine;

import java.util.List;

public class Purchase {
    private final int manualTicketQuantity;
    private final int autoTicketQuantity;
    private final List<ManualNumbers> multipleManualNumbers;

    private Purchase(int autoTicketQuantity, int manualTicketQuantity, List<ManualNumbers> multipleManualNumbers) {
        this.autoTicketQuantity = autoTicketQuantity;
        this.manualTicketQuantity = manualTicketQuantity;
        this.multipleManualNumbers = multipleManualNumbers;
    }

    static public Purchase of(int ticketQuantity, int manualTicketQuantity, List<ManualNumbers> multipleManualNumbers) {
        int autoTicketQuantity = manualTicketQuantity - ticketQuantity;
        return new Purchase(autoTicketQuantity, manualTicketQuantity, multipleManualNumbers);
    }

    public int getManualTicketQuantity() {
        return manualTicketQuantity;
    }

    public int getAutoTicketQuantity() {
        return autoTicketQuantity;
    }

    public List<ManualNumbers> getMultipleManualNumbers() {
        return multipleManualNumbers;
    }
}
