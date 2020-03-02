package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WinningLotto {
    private static final int DEFAULT_WINNING_TICKET_SIZE = 0;
    private static final int WINNING_AMOUNT = 1;

    private Lotto lottoNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(Set<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = new Lotto(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    WinningRanks match(Lottos lottos) {
        Map<Rank, Integer> winningRanks = new HashMap<>();
        for (Lotto lotto : lottos.getLottos()) {
            int matchNumber = countMatchNumber(lotto);
            Rank rank = Rank.valueOf(matchNumber, matchBonusNumber(bonusNumber));
            Integer winningTicketSize = winningRanks.get(rank);
            winningTicketSize = initTicketSize(winningTicketSize);

            winningRanks.put(rank, winningTicketSize + WINNING_AMOUNT);
        }
        return new WinningRanks(winningRanks);
    }

    private Integer initTicketSize(Integer winningTicketSize) {
        if (winningTicketSize == null) {
            winningTicketSize = DEFAULT_WINNING_TICKET_SIZE;
        }
        return winningTicketSize;
    }

    private int countMatchNumber(Lotto lotto) {
        return (int)lottoNumbers.getLottoNumbers().stream()
                    .filter(lotto::contains)
                    .count();
    }

    private boolean matchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}
