import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private List<Integer> lottoNumbers;

    public Lotto() {}

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }


    public List<Integer> generateNumber() {

        List<Integer> numbers = IntStream.range(1, 46)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        lottoNumbers = numbers.subList(0, 6);

        return numbers.subList(0, 6);
    }

    public int checkWinning(List<Integer> winningNumbers, Integer bonusNumber) {

        int sameCount = 0;

        for (Integer winningNumber : winningNumbers) {

            if (lottoNumbers.contains(winningNumber)) {
                sameCount++;
            }

        }

        if (sameCount == 5 && lottoNumbers.contains(bonusNumber)) {
            return 2;
        }

        if (sameCount == 5) {
            return 3;
        }

        if (sameCount < 3) {
            return -1;
        }

        return 7 - sameCount;
    }

}

