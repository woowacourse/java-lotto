import java.util.List;
import java.util.stream.Stream;

public class LottoNumbers {
    private final List<Integer> lottoNumbers;

    public LottoNumbers(int first, int second, int third, int fourth, int fifth, int six) {
        lottoNumbers = List.of(first, second, third, fourth, fifth, six);
    }

    public boolean contains(Integer bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int getMatchedNumberCountWith(LottoNumbers otherLottoNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(number -> otherLottoNumbers.contains(number))
                .count();
    }
}
