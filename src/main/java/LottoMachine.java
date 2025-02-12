import java.util.List;

public class LottoMachine {

    private final int ticket;

    private static final int LOTTO_PRICE = 1000;

    public LottoMachine(Price price) {
        this.ticket = price.getValue() / LOTTO_PRICE;
    }

    public int getTicket() {
        return ticket;
    }
}
