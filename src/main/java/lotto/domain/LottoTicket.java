package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.utils.CustomException;

public class LottoTicket {
    public static final int PRICE = 1000;
    public static final int SIZE_OF_LOTTO_NUMBERS = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(String lottoNumbersValue) {
        this(covertToLottoNumbers(lottoNumbersValue));
    }

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> numbers = new ArrayList<>(lottoNumbers);

        validateSize(numbers);
        validateDuplication(numbers);

        this.lottoNumbers = numbers;
    }

    private void validateDuplication(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> numbers = new HashSet<>(lottoNumbers);
        if (numbers.size() != lottoNumbers.size()) {
            throw new CustomException("로또 숫자에 중복된 값이 있습니다.");
        }
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new CustomException("로또 티켓의 사이즈는 " + SIZE_OF_LOTTO_NUMBERS + "개 입니다.");
        }
    }

    private static List<LottoNumber> covertToLottoNumbers(String s) {
        return Arrays.stream(s.split(","))
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    public boolean contains(LottoNumber bonusBall) {
        return lottoNumbers.contains(bonusBall);
    }

    public List<LottoNumber> getUnmodifiableList() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

}
