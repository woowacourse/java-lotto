package domain;

import controller.LottoController;
import utils.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static controller.LottoController.ERROR_MESSAGE;

public class IssuedLotto {

    private static final int LOTTO_SIZE = 6;

    private final List<Lotto> issuedLotto = new ArrayList<>();

    public List<Lotto> getIssuedLotto() {
        return new ArrayList<>(issuedLotto);
    }

    public List<Lotto> issue(Money money, Count manualCount) {
        issueManualLotto(manualCount);
        generateLotto(money.calculateCounts() - issuedLotto.size());
        return Collections.unmodifiableList(issuedLotto);
    }

    private void issueManualLotto(Count count) {
        issuedLotto.clear();
        while (!count.isEnd()) {
            issuedLotto.add(issueOneManualLotto());
            count = count.decrease();
        }
    }

    private Lotto issueOneManualLotto() {
        try {
            return new Lotto(LottoController.getManualLotto());
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return issueOneManualLotto();
        }
    }

    private void generateLotto(int number) {
        Count count = new Count(number);
        while (!count.isEnd()) {
            count = count.decrease();
            issuedLotto.add(issueOneAutoLotto());
        }
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
