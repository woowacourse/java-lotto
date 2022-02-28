package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {

    private static final int MIN_PURCHASE_COUNT = 1;

    public List<Lotto> generateLottoTicket(Money money) {
        List<Lotto> lottoTicket = new ArrayList<>();
        int purchaseCount = MIN_PURCHASE_COUNT;
        while (money.isPossibleToPurchase(purchaseCount)) {
            lottoTicket.add(generateLotto());
            ++purchaseCount;
        }
        return lottoTicket;
    }

    private Lotto generateLotto() {
        List<Number> numbers = new ArrayList<>(Number.values());
        Collections.shuffle(numbers);
        return new Lotto(new ArrayList<>(pickLottoNumbers(numbers)));
    }

    private List<Number> pickLottoNumbers(List<Number> numbers) {
        return numbers.subList(0, 6);
    }
}
