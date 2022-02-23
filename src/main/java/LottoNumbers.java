import exception.DuplicatedLottoNumbersException;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers) {
        if (hasDuplicatedNumber(lottoNumbers)) {
            throw new DuplicatedLottoNumbersException();
        }
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private boolean hasDuplicatedNumber(List<Integer> numbers) {
        return getDistinctSize(numbers) != numbers.size();
    }

    private long getDistinctSize(List<Integer> numbers) {
        return numbers.stream()
            .distinct()
            .count();
    }

    public boolean contains(LottoNumber number) {
        for (LottoNumber lottoNumber : lottoNumbers) {
            if (lottoNumber.equals(number)) {
                return true;
            }
        }
        return false;
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
