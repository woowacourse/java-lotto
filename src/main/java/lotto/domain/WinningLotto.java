package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Supplier<Stream<Rank>> ranks = matchRanks(lottos);
        Map<Rank, Integer> winningRanks = calculateWinningRanks(ranks);
        return new WinningRanks(winningRanks);
    }

    private Map<Rank, Integer> calculateWinningRanks(Supplier<Stream<Rank>> ranks) {
        Map<Rank, Integer> winningRanks = new HashMap<>();

        List<Integer> winningTicketSizes = ranks.get()
            .map(rank -> winningRanks.getOrDefault(rank, DEFAULT_WINNING_TICKET_SIZE))
            .collect(Collectors.toList());

        for (int winningTicketSize : winningTicketSizes) {
            ranks.get()
                .forEach(rank -> winningRanks.put(rank, winningTicketSize + WINNING_AMOUNT));
        }
        return winningRanks;
    }

    private Supplier<Stream<Rank>> matchRanks(Lottos lottos) {
        return () -> lottos.getLottos()
            .stream()
            .map(this::countMatchNumber)
            .map(matchNumber -> Rank.valueOf(matchNumber, matchBonusNumber(bonusNumber)));
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
