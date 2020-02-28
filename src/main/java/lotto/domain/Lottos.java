package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.util.NullValidator.validateNull;

public class Lottos {
    private final List<Lotto> elements;

    public Lottos(List<Lotto> elements) {
        validateNull(elements);
        this.elements = elements;
    }

    public static Lottos combine(Lottos lottos1, Lottos lottos2) {
        List<Lotto> totalElements = new ArrayList<>(lottos1.elements);
        totalElements.addAll(lottos2.elements);
        return new Lottos(totalElements);
    }

    public MatchResults toMatchResults(WinningLotto winningLotto) {
        List<MatchResult> matchResults = elements.stream()
                .filter(winningLotto::hasMatchResult)
                .map(winningLotto::createResult)
                .collect(Collectors.toList());
        return new MatchResults(matchResults);
    }

    public List<Lotto> get() {
        return Collections.unmodifiableList(elements);
    }
}
