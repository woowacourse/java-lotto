package lotterymachine.domain;

public class TicketCount {
    private int number;

    public TicketCount(int number) {
        this.number = number;
    }

    public boolean isExistCount() {
        return this.number > 0;
    }

    public void subtract() {
        this.number--;
    }
}
