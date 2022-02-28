package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.number.LottoNumbers;
import lotto.model.prize.MatchResult;

/*
 * 구입한 로또들을 담는 일급 컬렉션 Class
 */
public class Lottos {
    private List<Lotto> lottos;
    private int count;
    private int manualCount;

    public Lottos(int count, int manualCount) {
        this.lottos = new ArrayList<>();
        this.count = count;
        this.manualCount = manualCount;
    }

    public void purchaseAuto() {
        for (int i = 0; i < (count - manualCount); i++) {
            this.lottos.add(new Lotto(LottoNumbers.ofRandomNumbers()));
        }
    }

    public void purchaseManual(List<String> inputs) {
        if (isManualAvailable()) {
            this.lottos.add(new Lotto(LottoNumbers.from(inputs)));
            manualCount--;
        }
    }

    public List<MatchResult> match(WinningLotto winningLotto) {
        return this.lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList());
    }

    public boolean isManualAvailable() {
        return manualCount > 0;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(this.lottos);
    }

    public int getSize() {
        return this.lottos.size();
    }
}
