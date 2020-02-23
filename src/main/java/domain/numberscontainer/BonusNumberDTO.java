package domain.numberscontainer;

public class BonusNumberDTO {
    private final LottoNumber bonusNumber;

    public BonusNumberDTO(LottoNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
