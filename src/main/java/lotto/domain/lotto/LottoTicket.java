package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.lotto.Price.LOTTO_PRICE;

public class LottoTicket {
    private static final String ENTER = "\n";

    private int numberOfLotto;
    private List<Lotto> lottos;

    public LottoTicket(String money) {
        this.numberOfLotto = new Price(money).getNumberOfLotto();
        this.lottos = new ArrayList<>();
        createAutoLottoNumbers();
    }

    public AutoGenerateLotto createAutoLottoNumbers() {
        return new AutoGenerateLotto(numberOfLotto, lottos);
    }

    public int getNumberOfLotto() {
        return numberOfLotto;
    }

    public List<Rank> matchLotto(WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();
        lottos.stream().forEach(lotto -> {
            ranks.add(Rank.valueOf(lotto.numberOfMatch(winningLotto.getWinningLotto())
                    , lotto.bonusOfMatch(winningLotto.getBonusBall())));
        });

        return ranks;
    }

    public int getPrice() {
        return numberOfLotto * LOTTO_PRICE;
    }

    @Override
    public String toString() {
        return lottos.stream().map(Lotto::toString).collect(Collectors.joining(ENTER));
    }

}
