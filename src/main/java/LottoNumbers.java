import exception.DuplicatedLottoNumbersException;
import java.util.List;

public class LottoNumbers {

    private final List<Integer> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public void validate(List<Integer> numbers) {
        if (hasDuplicatedNumber(numbers)) {
            throw new DuplicatedLottoNumbersException();
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

    public boolean contains(Integer bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int getMatchedNumberCountWith(LottoNumbers otherLottoNumbers) {
        return (int) this.lottoNumbers.stream()
            .filter(number -> otherLottoNumbers.contains(number))
            .count();
    }

    public static LottoNumbers withSixNumbers(int first, int second, int third, int fourth,
        int fifth, int sixth) {
        return new LottoNumbers(List.of(first, second, third, fourth, fifth, sixth));
    }
}
