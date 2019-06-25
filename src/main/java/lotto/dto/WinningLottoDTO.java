package lotto.dto;

import lotto.domain.Lotto;

public class WinningLottoDTO {

    public static class Create {
        private Lotto winningLotto;
        private int bonusNumber;

        public Create(Lotto winningLotto, int bonusNumber) {
            this.winningLotto = winningLotto;
            this.bonusNumber = bonusNumber;
        }

        public Lotto getWinningLotto() {
            return winningLotto;
        }

        public int getBonusNumber() {
            return bonusNumber;
        }
    }
}
