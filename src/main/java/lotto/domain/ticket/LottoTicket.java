package lotto.domain.ticket;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumberFactory;
import lotto.domain.number.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
    public static final int SIZE_OF_LOTTO_NUMBERS = 6;

    private static final String NUMBER_SIZE_ERROR_MSG_FORMAT = "로또 번호는 %d개여야 합니다. 현재 개수 : %d";
    private static final String DUPLICATE_ERROR_MSG_FORMAT = "로또 번호가 중복되었습니다.";

    private final LottoNumbers lottoNumbers;

    public LottoTicket(List<Integer> numbers) {
        validateLottoNumberCount(numbers);
        validateDuplicatedLottoNumbers(numbers);

        this.lottoNumbers = numbers.stream().map(LottoNumberFactory::of)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoNumbers::new));
    }

    private void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException(
                    String.format(NUMBER_SIZE_ERROR_MSG_FORMAT, SIZE_OF_LOTTO_NUMBERS, numbers.size())
            );
        }
    }

    private void validateDuplicatedLottoNumbers(List<Integer> numbers) {
        Set<Integer> duplicateCheck = new HashSet<>(numbers);
        if (numbers.size() != duplicateCheck.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MSG_FORMAT);
        }
    }

    public List<LottoNumber> list() {
        return lottoNumbers.list();
    }
}
