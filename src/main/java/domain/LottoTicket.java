package domain;

import java.util.List;


public class LottoTicket {
    public static int LOTTO_PRICE = 1000;

    private final LottoNumbers lottoNumbers;

    public LottoTicket(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int countMatchedLottoNumbers(LottoNumbers winningLottoNumbers) {
        return (int) lottoNumbers.numbers()
                .stream()
                .filter(winningLottoNumbers.numbers()::contains)
                .count();
    }

    public boolean containsLottoNumber(int lottoNumber) {
        return lottoNumbers.numbers()
                .contains(lottoNumber);
    }

    public int getSize() {
        return lottoNumbers.getSize();
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.numbers();
    }
}
