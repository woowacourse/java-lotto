package domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final String LOTTO_DUPLICATED_MESSAGE = "[ERROR] 로또 번호에 중복된 숫자가 들어가면 안됩니다.";
    private static final String LOTTO_SIZE_MESSAGE = "[ERROR] 로또의 숫자는 6개여야 합니다.";
    private static final int LOTTO_START = 0;
    private static final int LOTTO_END = 6;
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        Collections.sort(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validateSize(List<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_MESSAGE);
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException(LOTTO_DUPLICATED_MESSAGE);
        }
    }

    private boolean isDuplicated(List<LottoNumber> numbers) {
        return numbers.size() != numbers.stream()
                .distinct()
                .count();
    }

    public List<LottoNumber> getLotto() {
        return new ArrayList<>(numbers);
    }

    public static Lotto generateLottoNumber(int minNumber, int maxNumber) {
        List<LottoNumber> lottoRange = IntStream.rangeClosed(minNumber, maxNumber)
                .mapToObj(LottoNumber::generateLottoNumber)
                .collect(Collectors.toList());
        Collections.shuffle(lottoRange);
        List<LottoNumber> numbers = lottoRange.subList(LOTTO_START, LOTTO_END);
        return new Lotto(numbers);
    }

    public Rank match(WinningLotto winningNumber) {
        int matchCount = getMatchCount(winningNumber);
        boolean hasBonusBall = winningNumber.isBonusBallMatch(this);
        return Rank.valueOf(matchCount, hasBonusBall);
    }

    private int getMatchCount(WinningLotto winningNumber) {
        return (int) numbers.stream()
                .filter(winningNumber::contains)
                .count();
    }

    public boolean contains(LottoNumber bonusBall) {
        return numbers.contains(bonusBall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

}
