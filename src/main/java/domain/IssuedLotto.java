package domain;

import utils.Random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class IssuedLotto {

    private static final int LOTTO_SIZE = 6;

    private final List<Lotto> issuedLotto = new ArrayList<>();

    public List<Lotto> getIssuedLotto() {
        return new ArrayList<>(issuedLotto);
    }

    public void generateLotto(Money money) {
        Count count = new Count(money.getCount() - issuedLotto.size());
        while (!count.isEnd()) {
            count = count.decrease();
            issuedLotto.add(issueOneAutoLotto());
        }
    }

    public void issueManualLotto(Lotto issueOneManualLotto) {
        issuedLotto.add(issueOneManualLotto);
    }

    private Lotto issueOneAutoLotto() {
        HashSet<LottoNumber> autoLottoNumbers = new HashSet<>();
        pickNumbers(autoLottoNumbers);
        return new Lotto(autoLottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList()));
    }

    private void pickNumbers(HashSet<LottoNumber> lotto) {
        while (lotto.size() < LOTTO_SIZE) {
            lotto.add(LottoNumber.getNumber(Random.lottoNumber()));
        }
    }
}
