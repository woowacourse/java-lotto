package lotto.domain;

public class Result {
    private WinningInfo winningInfo;

    void calculate(WinningLotto winningLotto, Lotto userLotto) {
        int winningCount = 0;
        boolean hasBonus = false;
        for (LottoNumber userLottoNumber : userLotto.getLottoNumbers()) {
            for (LottoNumber winningLottoNumber : winningLotto.getLottoNumbers()) {
                if (userLottoNumber.getNumber() == winningLottoNumber.getNumber()) {
                    winningCount++;
                }
                if (userLottoNumber.getNumber() == winningLotto.getBonusNumber().getNumber()) {
                    hasBonus = true;
                }
            }
        }

        winningInfo = WinningInfo.valueOf(winningCount, hasBonus);
    }

    public WinningInfo getWinningInfo() {
        return winningInfo;
    }
}
