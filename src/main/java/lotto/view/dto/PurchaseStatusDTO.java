package lotto.view.dto;

public class PurchaseStatusDTO {
    private int numberOfManualTickets;
    private int numberOfAutoTickets;

    public PurchaseStatusDTO(int numberOfManualTickets, int numberOfAutoTickets) {
        this.numberOfManualTickets = numberOfManualTickets;
        this.numberOfAutoTickets = numberOfAutoTickets;
    }

    public int getNumberOfManualTickets() {
        return numberOfManualTickets;
    }

    public int getNumberOfAutoTickets() {
        return numberOfAutoTickets;
    }
}
