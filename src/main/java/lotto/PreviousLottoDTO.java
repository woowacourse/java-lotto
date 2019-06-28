package lotto;

import java.util.ArrayList;
import java.util.List;

public class PreviousLottoDTO {
    private List<Integer> lottoNumbers = new ArrayList<>();

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public void addNumber(final int number) {
        lottoNumbers.add(number);
    }
}
