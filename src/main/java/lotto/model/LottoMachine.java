package lotto.model;

import static lotto.LottoConstants.Number.LOTTO_NUMBER_COUNT;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MIN;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoMachine {

    private final NumberPicker numberPicker;

    public LottoMachine(NumberPicker numberPicker) {
        validateNumberPicker(numberPicker);
        this.numberPicker = numberPicker;
    }

    private void validateNumberPicker(NumberPicker numberPicker) {
        if (numberPicker == null) {
            throw new IllegalArgumentException("번호 생성기가 필요합니다.");
        }
    }

    public List<Lotto> issueLottoByCount(int count) {
        validateCount(count);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(issue());
        }
        return lottos;
    }

    private void validateCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("로또는 최소 1개 이상 발급할 수 있습니다.");
        }
    }

    private Lotto issue() {
        return new Lotto(generateUniqueNumbers());
    }

    private Set<Integer> generateUniqueNumbers() {
        return numberPicker.pickNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_COUNT);
    }
}
