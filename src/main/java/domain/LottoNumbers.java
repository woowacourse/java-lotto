package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(final String inputNumbers) {
        List<String> splitNumbers = splitInput(inputNumbers);
        validateInput(splitNumbers);
        this.lottoNumbers = splitNumbers.stream()
                .map(number -> new LottoNumber(number))
                .collect(Collectors.toList());
    }

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    private List<String> splitInput(final String inputNumbers) {
        return Arrays.stream(inputNumbers.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void validateInput(final List<String> numbers) {
        long distinctCount = numbers.stream().distinct().count();
        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException("중복값을 입력할 수 없습니다.");
        }
    }

    public int size() {
        return this.lottoNumbers.size();
    }

    public int compare(final LottoNumbers winNumbers) {
        int count = 0;
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            if (winNumbers.compare(lottoNumber)) {
                count += 1;
            }
        }

        return count;
    }

    private boolean compare(final LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }

    public boolean compareBonus(final LottoNumber bonusNumber) {
        return this.lottoNumbers.contains(bonusNumber);
    }
}
