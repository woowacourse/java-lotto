package lotterymachine.domain;

public class TicketCount {
    private int number;

    public TicketCount(int number) {
        this.number = number;
    }

    public boolean isExist() {
        return this.number > 0;
    }

    public void subtract() {
        this.number--;
    }
}
