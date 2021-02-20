package lotto.domain;

import com.google.common.primitives.Ints;

import java.util.*;

public class Lotto {
    public static final int LOTTO_START_INDEX = 0;
    public static final int LOTTO_SIZE = 6;
    public static final String LOTTO_SIZE_ERROR = String.format("번호는 총 %d개 이어야 합니다.", LOTTO_SIZE);
    public static final String DUPLICATE_ERROR = "중복되는 번호가 있습니다.";

    protected final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoSize(lottoNumbers);
        validateDuplicates(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR);
        }
    }

    private void validateDuplicates(List<LottoNumber> numbers) {
        Set<LottoNumber> numberGroup = new HashSet<>(numbers);
        if (numberGroup.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    public List<LottoNumber> toList() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    //TODO
    // Lotto객체를 주입 받도록 리팩토링
    public int countMatchingNumbers(List<LottoNumber> numbers) {
        return (int) numbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return lottoNumbers.contains(new LottoNumber(bonusNumber));
    }

    public String getLottoSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(Ints.join(", ", lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .toArray()));
        sb.append("]");

        return sb.toString();
    }

    public int getLottoSize() {
        return lottoNumbers.size();
    }
}
