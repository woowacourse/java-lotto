package lotto.domain;

import lotto.utils.Validator;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private final List<Integer> lottoTicket;

    public LottoTicket(List<Integer> numbers) {
        Validator.validateLottoNumbers(numbers);
        Collections.shuffle(numbers);
        this.lottoTicket = numbers.subList(0, 6);
    }

    public List<Integer> lottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

}
