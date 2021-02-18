package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumberFactory;

public class LottoTicket {
    public static final int SIZE_OF_LOTTO_NUMBERS = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<Integer> numbers) {
        validateLottoNumberCount(numbers);
        validateDuplicatedLottoNumbers(numbers);

        this.lottoNumbers = numbers.stream().map(LottoNumberFactory::getInstance)
            .sorted(Comparator.naturalOrder())
            .collect(Collectors.toList());
    }

    private void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException(
                String.format("로또 번호는 %d개여야 합니다. 현재 개수 : %d", SIZE_OF_LOTTO_NUMBERS, numbers.size())
            );
        }
    }

    private void validateDuplicatedLottoNumbers(List<Integer> numbers) {
        Set<Integer> duplicateCheck = new HashSet<>(numbers);
        if (numbers.size() != duplicateCheck.size()) {
            throw new IllegalArgumentException("로또 번호가 중복되었습니다.");
        }
    }

    public List<LottoNumber> list() {
        return new ArrayList<>(lottoNumbers);
    }

    //todo : 방안 1) 전달용 객체 생성 2) list getter 메소드 활용해서 outputView에서 동일한 작업
    public String printLottoTicket() {
        return Arrays.toString(lottoNumbers.toArray());
    }
}
