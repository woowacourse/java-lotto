import java.util.List;

public class BonusNumber {

    private final int number;

    public BonusNumber(int number) {
        this.number = number;
    }

    public boolean matchesWith(List<Integer> lottoNumbers) {
        return lottoNumbers.contains(number);
    }
}
