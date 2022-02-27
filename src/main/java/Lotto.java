import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_START = 0;
    private static final int LOTTO_END = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto generateNumber() {
        List<LottoNumber> lottoRange = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoRange);
        List<LottoNumber> numbers = lottoRange.subList(LOTTO_START, LOTTO_END);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public int match(WinningNumber winningNumber) {
        return (int) numbers.stream().filter(winningNumber::contains).count();
    }

    public boolean hasBonusBall(LottoNumber bonusBall) {
        return numbers.contains(bonusBall);
    }

    @Override
    public String toString() {
        String lotto = numbers.stream().map(number -> number.toString())
            .collect(Collectors.joining(", "));
        return "[" + lotto + "]";
    }
}
