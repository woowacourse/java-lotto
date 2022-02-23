package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class LottoFactory {

    final private List<LottoNumbers> lotto = new ArrayList<>();
    final private List<Integer> winCounts = new ArrayList<>();
    private final Money money;
    private final LottoNumbers winNumbers;
    private final LottoNumber bonusNumber;

    public LottoFactory(final Money money, final LottoNumbers winNumbers, final LottoNumber bonusNumber) {
        this.money = money;
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Count calculateCount(final Money money) {
        return new Count(money.calculateCounts());
    }

    public LottoNumbers generateAutoLottoNumbers() {
        HashSet<LottoNumber> autoLottoNumbers = new HashSet<>();
        while (autoLottoNumbers.size() < 6) {
            autoLottoNumbers.add(new LottoNumber(ThreadLocalRandom.current().nextInt(45) + 1));
        }

        return new LottoNumbers(autoLottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList()));
    }

    public void issueLotto(Count count) {
        lotto.clear();
        while (!count.isEnd()) {
            count = count.decrease();
            lotto.add(generateAutoLottoNumbers());
        }
    }

    public List<LottoNumbers> getLottoTickets() {
        return Collections.unmodifiableList(lotto);
    }

    public void compare() {
        for (LottoNumbers autoLottoNumbers : lotto) {
            int winCount = autoLottoNumbers.compare(this.winNumbers);
            if (winCount == 5 && autoLottoNumbers.compareBonus(bonusNumber)) {
                winCount += 2; // todo: 포장하기
            }
            winCounts.add(winCount);
        }
    }
}
