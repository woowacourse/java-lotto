package domain.numberscontainer;

public class BonusNumberDto {

    private final LottoNumber bonusNumber;

    public BonusNumberDto(int bonusNumber) {
        this.bonusNumber = LottoNumber.get(bonusNumber);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}