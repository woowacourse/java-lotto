package domain;

import exception.LottoException;
import java.util.List;
import java.util.Map;

public class WinningLotto {

  private static final String INVALID_BONUS_NUMBER = "유효하지 않은 보너스번호입니다.";

  private final WinningNumber winningNumber;
  private final BonusNumber bonusNumber;

  public WinningLotto(WinningNumber winningNumber, BonusNumber bonusNumber) {
    validateDuplicationBonusNumber(winningNumber, bonusNumber);
    this.winningNumber = winningNumber;
    this.bonusNumber = bonusNumber;
  }

  public Map<WinningCase, Integer> winningCalculate(List<Lotto> lottos) {
    Map<WinningCase,Integer> winningResult = WinningCase.toMap();
    for(Lotto lotto : lottos){
      int sameCount = lotto.compare(winningNumber);
      boolean isBonus = lotto.compareBonusNumber(bonusNumber);
      WinningCase winningCase = WinningCase.getWinningCase(sameCount, isBonus);
      winningResult.put(winningCase,winningResult.get(winningCase)+1);
    }
    return winningResult;
  }

  private void validateDuplicationBonusNumber(WinningNumber winningNumber, BonusNumber bonusNumber) {
    if (bonusNumber == null || bonusNumber.isDuplicate(winningNumber)) {
      throw new LottoException(INVALID_BONUS_NUMBER);
    }
  }

}
