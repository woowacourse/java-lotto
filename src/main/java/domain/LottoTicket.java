package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final int LOTTO_SIZE = 6;
    private static final String INVALID_SIZE_EXCEPTION_MESSAGE = "6개의 당첨 번호를 입력해야 합니다.";
    private static final String DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE = "당첨 번호는 중복될 수 없습니다.";

    private static final List<LottoNumber> allLottoNumbers = LottoNumber.getAllLottoNumbers();

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateNoDuplicates(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket createAutoLotto() {
        Collections.shuffle(allLottoNumbers);
        return new LottoTicket(allLottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted()
                .collect(Collectors.toList()));
    }

    public static LottoTicket createManualLotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        Collections.sort(lottoNumbers);
        return new LottoTicket(lottoNumbers);
    }

    private static void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_SIZE_EXCEPTION_MESSAGE);
        }
    }

    private void validateNoDuplicates(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> numbersSet = new HashSet<>(lottoNumbers);

        if (lottoNumbers.size() != numbersSet.size()) {
            throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }
}
