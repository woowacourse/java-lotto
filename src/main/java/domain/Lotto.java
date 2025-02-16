package domain;

import infrastructure.constants.Constants;
import domain.vo.LottoNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int SIZE = 6;
    private List<LottoNumber> numbers;

    public Lotto(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        numbers = lottoNumbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .toList();
    }

    public boolean isExist(LottoNumber number) {
        return numbers.contains(number);
    }

    public LottoPrize getLottoPrize(WinningLotto winningLotto) {
        final int matchedWinningCount = numbers.stream().filter(winningLotto::isMatchWinningNumber).toList().size();
        final boolean isMatchedBonus = numbers.stream().anyMatch(winningLotto::isMatchBonus);
        return LottoPrize.from(matchedWinningCount, isMatchedBonus);
    }

    public List<Integer> getNumbers() {
        return numbers.stream().map(LottoNumber::value).toList();
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException(String.format("로또는 %d개의 숫자로 구성돼야 합니다.", SIZE));
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또는 서로 다른 숫자로 구성돼야 합니다.");
        }
    }
}
