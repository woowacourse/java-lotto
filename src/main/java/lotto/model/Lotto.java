package lotto.model;

import static java.util.stream.Collectors.*;
import static lotto.ValidationUtils.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private static final String ERROR_NOT_MATCH_LOTTO_NUMBER_SIZE = "로또 번호 개수는 6개로 입력해주세요.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(Set<Integer> integers) {
        Set<Integer> lottoNumbers = Set.copyOf(integers);
        validateEmptyCollection(lottoNumbers);
        validateNumberOfLottoNumbers(lottoNumbers);
        this.lottoNumbers = convertIntegersToLottoNumbers(lottoNumbers);
    }

    private void validateNumberOfLottoNumbers(Set<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NOT_MATCH_LOTTO_NUMBER_SIZE);
        }
    }

    private List<LottoNumber> convertIntegersToLottoNumbers(Set<Integer> integers) {
        return List.copyOf(integers.stream()
            .map(LottoNumber::new)
            .collect(toList()));
    }

    Rank match(Lotto winningNumbers, LottoNumber bonusNumber) {
        return Rank.find(getMatchScore(winningNumbers), isMatchNumber(bonusNumber));
    }

    private int getMatchScore(Lotto winningNumbers) {
        return (int)lottoNumbers.stream()
            .filter(winningNumbers::isMatchNumber)
            .count();
    }

    private boolean isMatchNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    @Override
    public String toString() {
        return lottoNumbers.stream().sorted().collect(toList()).toString();
    }
}
