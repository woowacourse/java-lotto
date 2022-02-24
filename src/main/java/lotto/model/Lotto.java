package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_NOT_MATCH_WINNING_NUMBER_SIZE = "지난 주 당첨 번호 개수는 6개로 입력해주세요.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateNumberOfLottoNumbers(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = convertIntegersToLottoNumbers(lottoNumbers);
    }

    public static Lotto generate() {
        List<Integer> lottoNumbers = generateSequentialIntegers();
        Collections.shuffle(lottoNumbers);

        return new Lotto(lottoNumbers.subList(0, LOTTO_SIZE));
    }

    private List<LottoNumber> convertIntegersToLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public int match(WinningNumbers winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean matchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    private static List<Integer> generateSequentialIntegers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> toIntegers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }

    private void validateNumberOfLottoNumbers(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NOT_MATCH_WINNING_NUMBER_SIZE);
        }
    }
}
