package lotto.domain;

import java.util.List;

public interface LottoCreator {
    public LottoTicket create();

    public LottoTicket create(List<Integer> numbers);
}
