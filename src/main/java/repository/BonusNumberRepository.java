package repository;

import domain.BonusNumber;

public class BonusNumberRepository {

    private BonusNumber bonusNumber;

    public void saveBonusNumber(BonusNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }

}
