package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LottoManager {
    private final RatingInfo ratingInfo = new RatingInfo();
    private List<Lotto> lottos = new ArrayList<>();
    private Supplier<List<Integer>> generator;

    public LottoManager(final Supplier<List<Integer>> generator) {
        this.generator = generator;
    }

    public void buyLotto(final int money) {
        Ticket ticket = new Ticket(new Money(money));
        lottos = generateLottoByTicket(ticket.getCount());
    }

    public void scratchLotto(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            int match = winningLotto.compareLottoNumber(lotto);
            boolean hasBonusBall = winningLotto.compareBonusBall(lotto);
            ratingInfo.update(Rating.getRating(match,hasBonusBall));
        }
    }

    public List<Lotto> generateLottoByTicket(final int ticketCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottos.add(new Lotto(generator.get()));
        }
        return lottos;
    }

}

