import java.util.List;
import java.util.stream.Stream;

public class LottoNumbers {

    private final List<Integer> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
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
        int fifth, int six) {
        return new LottoNumbers(List.of(first, second, third, fourth, fifth, six));
    }
}
