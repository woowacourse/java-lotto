package repository;

import domain.BonusNumber;

public class BonusNumberRepository {

    private BonusNumber bonusNumber;

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }

    public void saveBonusNumber(BonusNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

}
