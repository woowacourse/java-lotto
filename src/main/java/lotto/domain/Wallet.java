package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wallet {
    private final List<Lotto> lottoList = new ArrayList<>();

    public Wallet(int numberOfLotto) {
        Stream.generate(Lotto::generateLotto)
            .limit(numberOfLotto)
            .forEach(lottoList::add);
    }

    public List<MatchCount> getMatchCountList(Lotto winningLotto, int bonus) {
        return lottoList.stream()
            .map(lotto -> lotto.matchCount(winningLotto, bonus))
            .toList();
    }

    @Override
    public String toString() {
        return lottoList.stream()
            .map(Lotto::toString)
            .collect(Collectors.joining("\n"));
    }
}
