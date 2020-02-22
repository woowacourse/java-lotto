package domain.lottonumber;

public class LottoNumbersDto {
    private static final int DUMMY_LOTTO_NUMBER = -1;

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public LottoNumbersDto(LottoNumbers lottoNumbers) {
        this(lottoNumbers, LottoNumber.valueOf(DUMMY_LOTTO_NUMBER));
    }

    public LottoNumbersDto(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        validateDuplicatedBonusNumber(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateDuplicatedBonusNumber(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }
}