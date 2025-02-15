package domain;

import java.util.List;


public class LottoTicket {
    public static int LOTTO_PRICE = 1000;

    private final LottoNumbers lottoNumbers;

    public LottoTicket(LottoNumbers lottoNumbers) {
        validateLottoSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoSize(LottoNumbers lottoNumbers) {
        if (lottoNumbers.getSize() != LottoNumbers.LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public int countMatchedNumbers(LottoNumbers winningNumbers) {
        return (int) lottoNumbers.getNumbers()
                .stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return lottoNumbers.getNumbers()
                .contains(bonusNumber);
    }

    public int getSize() {
        return lottoNumbers.getSize();
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.getNumbers();
    }
}
