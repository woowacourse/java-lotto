package lotto.domain;

public class WinningLotto {
    private final Lotto winningLottoLine;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLottoLine, LottoNumber bonusNumber) {
        validateNull(winningLottoLine, bonusNumber);
        validateDuplication(winningLottoLine, bonusNumber);
        this.winningLottoLine = winningLottoLine;
        this.bonusNumber = bonusNumber;
    }

    private static void validateNull(Lotto winningLottoLine, LottoNumber bonusNumber) {
        if (winningLottoLine == null || bonusNumber == null) {
            throw new RuntimeException("null값이 들어왔습니다.");
        }
    }

    private static void validateDuplication(Lotto winningLottoLine, LottoNumber bonusNumber) {
        if (winningLottoLine.contains(bonusNumber)) {
            throw new RuntimeException("당첨 번호와 중복되는 보너스 볼입니다.");
        }
    }

    public Lotto getWinningLottoLine() {
        return winningLottoLine;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
