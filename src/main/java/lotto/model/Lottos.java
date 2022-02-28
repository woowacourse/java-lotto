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
    public static final String ERROR_COUNT_OVER = "[ERROR] 구매할 수 있는 수량을 초과했습니다";
    public static final String ERROR_TYPE = "[ERROR] 로또 구매 수량은 숫자로만 입력해주세요";

    private final List<Lotto> lottos;
    private final int autoCount;
    private int manualCount;

    public Lottos(int count, int manualCount) {
        this.lottos = new ArrayList<>();
        checkCount(count, manualCount);
        this.autoCount = count - manualCount;
        this.manualCount = manualCount;
    }

    private void checkCount(int count, int manualCount) {
        if (count <= manualCount) {
            throw new IllegalArgumentException(ERROR_COUNT_OVER);
        }
    }

    public static Lottos of(Money money, String manualCountInput) {
        try {
            return new Lottos(Lotto.countAvailableTickets(money), Integer.parseInt(manualCountInput));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public void purchaseAuto() {
        for (int i = 0; i < (autoCount - manualCount); i++) {
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
