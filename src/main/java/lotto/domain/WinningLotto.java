package lotto.domain;

public class WinningLotto {
    private final PickedNumbers winningNumbers;
    private final BonusNumber bonusNumber;
    private static final int SECOND_OR_THIRD_COUNT = 5;

    public WinningLotto(PickedNumbers pickedNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = pickedNumbers;
        this.bonusNumber = bonusNumber;
    }

    public long findSameNumbersInPicked(PickedNumbers pickedNumbers) {
        return winningNumbers.findMatchCount(pickedNumbers);
    }

    public void addLottoResult(PickedNumbers pickedNumbers, LottoResult result){
        LottoRank lottoRank = findLottoRank(pickedNumbers);
        if(lottoRank!=null){
            result.addWinningLotto(lottoRank);
        }
    }

    public LottoRank findLottoRank(PickedNumbers pickedNumbers){
        long correctCount = findSameNumbersInPicked(pickedNumbers);
        boolean isBonused = false;
        if (isSecondOrThird(correctCount)) {
            isBonused = pickedNumbers.isContainNumber(bonusNumber.getBonusNumber());
        }
        return LottoRank.valueOf(correctCount, isBonused);
    }

    private boolean isSecondOrThird(long correctCount) {
        return correctCount == SECOND_OR_THIRD_COUNT;
    }
}

