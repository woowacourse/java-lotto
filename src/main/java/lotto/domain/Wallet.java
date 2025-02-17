package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public List<Lotto> getLottoList() {
        return Collections.unmodifiableList(lottoList);
    }
}
