package controller;

import domain.LottoFactory;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.Money;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void start() {
        final LottoFactory lottoFactory = new LottoFactory(getMoney());
        OutputView.printAutoLotto(lottoFactory.issueLotto());

        LottoNumbers winNumbers = getWinNumbers();

        LottoNumber bonusNumber = getBonusNumber(winNumbers);

//        lottoFactory.sadf( winNumbers, bonusNumber);

    }

    private LottoNumbers getWinNumbers() {
        try {
            return new LottoNumbers(InputView.getWinNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return getWinNumbers();
        }
    }

    private Money getMoney() {
        try {
            Money money = new Money(InputView.getMoney());
            return money;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return getMoney();
        }
    }

    private LottoNumber getBonusNumber(final LottoNumbers lottoNumbers) {
        try {
            LottoNumber bonusNumber = new LottoNumber(InputView.getBonusNumber());
            if (isBonusDuplicated(lottoNumbers, bonusNumber)) {
                throw new IllegalArgumentException("지난주 당첨번호와 보너스가 중복일 수 없습니다.");
            }
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return getBonusNumber(lottoNumbers);
        }
    }

    private boolean isBonusDuplicated(final LottoNumbers lottoNumbers, final LottoNumber bonusNumber) {
        return lottoNumbers.compareBonus(bonusNumber);
    }
}
