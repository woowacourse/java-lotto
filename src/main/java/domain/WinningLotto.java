package domain;

import domain.enums.WinningCase;
import domain.formatter.WinningCalculateFormatter;
import exception.LottoException;
import java.util.List;
import java.util.Map;

public class WinningLotto {

  private final String INVALID_BONUS_NUMBER = "유효하지 않은 보너스번호입니다.";

  private final WinningNumber winningNumber;
  private final BonusNumber bonusNumber;

  public WinningLotto(WinningNumber winningNumber, BonusNumber bonusNumber) {
    validateDuplicationBonusNumber(winningNumber, bonusNumber);
    this.winningNumber = winningNumber;
    this.bonusNumber = bonusNumber;
  }

  private void validateDuplicationBonusNumber(WinningNumber winningNumber, BonusNumber bonusNumber) {
    if (bonusNumber == null || bonusNumber.isDuplicate(winningNumber)) {
      throw new LottoException(INVALID_BONUS_NUMBER);
    }
  }

  public Map<WinningCase, Integer> winningCalculate(List<Lotto> lottos) {
    Map<WinningCase,Integer> winningResult = WinningCase.toMap();
    for(Lotto lotto : lottos){
      int sameCount = lotto.compare(winningNumber, bonusNumber);
      boolean isBonus = lotto.compareBonusNumber(bonusNumber);
      WinningCase winningCase = WinningCase.getWinningCase(sameCount, isBonus);
      winningResult.put(winningCase,winningResult.getOrDefault(winningCase,0)+1);
    }
    return winningResult;
  }
}
