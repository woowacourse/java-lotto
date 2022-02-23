package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LottoFactory {

    final private List<LottoNumbers> lotto = new ArrayList<>();

    public Count calculateCount(final Money money) {
        return new Count(money.calculateCounts());
    }

    public LottoNumbers generateAutoLottoNumbers() {
        HashSet<LottoNumber> autoLottoNumbers = new HashSet<>();
        while (autoLottoNumbers.size() < 6) {
            autoLottoNumbers.add(new LottoNumber(ThreadLocalRandom.current().nextInt(45) + 1));
        }

        return new LottoNumbers(new ArrayList<>(autoLottoNumbers));
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
}
