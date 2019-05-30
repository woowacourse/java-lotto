package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoLotto {
    public static final int MAX_LOTTO_SIZE = 6;

    private final LottoTicket lottoTicket;

    public AutoLotto() {
        this.lottoTicket = createAutoNumbers();
    }

    public static LottoTicket createAutoNumbers() {
        Set<LottoNumber> numbers = new HashSet<>();
        while (numbers.size() < MAX_LOTTO_SIZE) {
            numbers.add(new LottoNumber());
        }
        return convertLottoNumbers(numbers);
    }

    private static LottoTicket convertLottoNumbers(Set<LottoNumber> numbers) {
        List<LottoNumber> convertLottoNumbers = new ArrayList<>();
        numbers.stream()
                .sorted()
                .forEach(number -> convertLottoNumbers.add(number));
        return new LottoTicket(convertLottoNumbers);
    }

    @Override
    public String toString() {
        return lottoTicket.toString();
    }
}
