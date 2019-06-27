package lotto;

import java.util.List;

public class PreviousLottoDTO {
    private List<Integer> lottoNumbers;

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public void addNumber(final int number) {
        lottoNumbers.add(number);
    }
}
