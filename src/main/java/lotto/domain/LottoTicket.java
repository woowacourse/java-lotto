package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket generateTicket(List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        int numberCounts = numbers.size();
        int distinctNumberCounts = distinctNumbers.size();
        if (numberCounts != 6 || numberCounts != distinctNumberCounts) {
            throw new IllegalArgumentException("로또 티켓은 중복되지 않은 6자리의 숫자로 구성되어야 합니다.");
        }
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
