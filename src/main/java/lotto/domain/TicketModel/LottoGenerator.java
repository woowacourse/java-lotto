package lotto.domain.TicketModel;


import java.util.*;

public class LottoGenerator implements TicketCreator {

    public LottoGenerator() {

    }

    @Override
    public Ticket create() {
        return new Lotto(new LottoNumbers());
    }

    @Override
    public Ticket create(List<Integer> numbers) {
        return new Lotto(new LottoNumbers(numbers));
    }
}
