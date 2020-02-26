package domain;

public class TicketSize {
    private int ticketSize;

    public TicketSize(int ticketSize) {
        this.ticketSize = ticketSize;
    }

    private static int getRandomTicketSize(int totalTicketSize, int manualTicketSize) {
        return totalTicketSize - manualTicketSize;
    }

}
