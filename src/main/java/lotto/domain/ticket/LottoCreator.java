package lotto.domain.ticket;


import java.util.List;

public class LottoCreator implements TicketCreator {
    private static final int LOTTO_PRICE = 1000;

    public LottoCreator() {

    }

    @Override
    public TicketNumber bonus(int bonus) {
        return new LottoNumber(bonus);
    }

    @Override
    public Ticket create(List<Integer> numbers) {
        return new Lotto(LottoNumberPool.manual(numbers));
    }

    @Override
    public Ticket create() {
        return new Lotto(new LottoNumbers(LottoNumberPool.random()));
    }

    @Override
    public int unitPrice() {
        return LOTTO_PRICE;
    }
}
