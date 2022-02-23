import java.util.List;

public class LottoNumberValidator {
    public void validate(int lottoNumber) {
        checkEachValidRange(lottoNumber);
    }

    private void checkEachValidRange(int lottoNumber) {
        if (1 > lottoNumber || lottoNumber > 45) {
            throw new IllegalArgumentException("당첨 번호는 1 ~ 45사이의 숫자만 가능합니다.");
        }
    }

    public void validate(List<Integer> lottoNumbers) {
        checkValidRange(lottoNumbers);
        checkDuplicatedLottoNumber(lottoNumbers);
    }

    private void checkValidRange(List<Integer> lottoNumbers) {
        lottoNumbers.forEach(this::checkEachValidRange);
    }

    private void checkDuplicatedLottoNumber(List<Integer> numbers) {
        if (hasDuplicatedNumber(numbers)) {
            throw new IllegalArgumentException("중복된 당첨 번호는 허용하지 않습니다.");
        }
    }

    private boolean hasDuplicatedNumber(List<Integer> numbers) {
        return getDistinctSize(numbers) != numbers.size();
    }

    private long getDistinctSize(List<Integer> numbers) {
        return numbers.stream()
            .distinct()
            .count();
    }
}
