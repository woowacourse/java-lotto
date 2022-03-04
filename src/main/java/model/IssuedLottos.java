package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import model.generator.LottoGenerator;

public class IssuedLottos {
    private final Budget budget;
    private final List<Lotto> manualIssuedLotto;
    private final List<Lotto> autoIssuedLotto;

    public IssuedLottos(Budget budget,LottoGenerator generator, List<Lotto> manualIssuedLotto) {
        this.budget = budget;
        this.manualIssuedLotto = Collections.unmodifiableList(manualIssuedLotto);
        this.autoIssuedLotto = Collections.unmodifiableList(issueAffordableAutoLotto(generator));
    }

    private List<Lotto> issueAffordableAutoLotto(LottoGenerator generator) {
        int affordableLottoCount = getAffordableLottoCount();
        return IntStream.range(0, affordableLottoCount)
                .mapToObj(i -> generator.createLotto())
                .collect(Collectors.toList());
    }

    private int getAffordableLottoCount() {
        return budget.getMaxCountForLottoIssue() - manualIssuedLotto.size();
    }

    public LottoResult summary(WinningLottoNumbers winningLottoNumbers) {
        List<LottoRank> ranks = Stream.of(manualIssuedLotto, autoIssuedLotto)
                .flatMap(lottos -> lottos.stream())
                .map(winningLottoNumbers::getRankBy)
                .collect(Collectors.toList());
        return new LottoResult(budget, ranks);
    }

    public int getManualLottoCount() {
        return manualIssuedLotto.size();
    }

    public int getAutoLottoCount() {
        return autoIssuedLotto.size();
    }

    public List<Lotto> getManualIssuedLotto() {
        return Collections.unmodifiableList(manualIssuedLotto);
    }

    public List<Lotto> getAutoIssuedLotto() {
        return Collections.unmodifiableList(autoIssuedLotto);
    }
}
