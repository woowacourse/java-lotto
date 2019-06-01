package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lottoes {
    private static final String JOINING_DELIMITER = "\n";
    private final List<Lotto> lottoes;

    public Lottoes(final List<Lotto> lottoes) {
        this.lottoes = lottoes;
    }

    @Override
    public String toString() {
        return lottoes.stream()
                .map(l -> l.toString())
                .collect(Collectors.joining(JOINING_DELIMITER));
    }

    public List<Rank> getRanks(WinningLotto winningLotto) {
        return lottoes.stream()
                .map(l -> {
                 return Rank.valueOf(winningLotto.getMatchCount(l),winningLotto.isMatchBonus(l));
                })
                .collect(Collectors.toList());
    }

    public int getSize() {
        return lottoes.size();
    }
}
