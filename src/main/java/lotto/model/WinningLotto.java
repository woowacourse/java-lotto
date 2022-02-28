package lotto.model;

import java.util.Map;
import java.util.stream.Collectors;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class WinningLotto {

    private static final String DUPLICATED_NUMBER_ERROR_MESSAGE = "[ERROR] 중복된 번호가 존재합니다.";

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(final LottoNumbers winningNumbers, final LottoNumber bonusNumber) throws RuntimeException {
        this.winningNumbers = winningNumbers;
        checkDuplicateNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public void checkDuplicateNumber(LottoNumber number) throws RuntimeException {
        if ((winningNumbers.getLottoNumbers().stream()
                .mapToInt(LottoNumber::getLottoNumber)
                .boxed()
                .collect(Collectors.toList()))
                .contains(number.getLottoNumber())) {
            throw new RuntimeException(DUPLICATED_NUMBER_ERROR_MESSAGE);
        }
    }

    public Map<Rank, Integer> checkRank(Lottos lottos) {
        return lottos.calculateRanks(winningNumbers, bonusNumber);
    }
}
