package lotto.domain.TicketModel;


import java.util.List;

public class LottoCreator implements TicketCreator {
    private static final int LOTTO_PRICE = 1000;

    public LottoCreator() {

    }

    @Override
    public Ticket create() {
        return new Lotto(new LottoNumbers());
    }

    @Override
    public Ticket create(List<Integer> numbers) {
        return new Lotto(new LottoNumbers(numbers));
    }

    @Override
    public int unitPrice() {
        return LOTTO_PRICE;
    }
}
