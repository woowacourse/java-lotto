package domain;

import java.util.List;

public class LottoTicket {
    public static int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateDuplicateNumber(lottoNumbers);
        validateLottoSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket createLottoTicket(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::new).toList();
        return new LottoTicket(lottoNumbers);
    }

    private void validateDuplicateNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != lottoNumbers.size()) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }

    private void validateLottoSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public int countMatchedLottoNumbers(LottoTicket winningLottoTicket) {
        return (int) lottoNumbers
                .stream()
                .filter(winningLottoTicket.getLottoNumbers()::contains)
                .count();
    }

    public boolean containsLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int getSize() {
        return lottoNumbers.size();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
