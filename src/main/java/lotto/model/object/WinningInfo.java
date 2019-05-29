package lotto.model.object;

public class WinningInfo {
        private Lotto winningLotto;
        private BonusBall bonusBall;


        public WinningInfo(Lotto winningLotto, BonusBall bonusBall) {
                this.winningLotto = winningLotto;
                this.bonusBall = bonusBall;
        }

        public Lotto getWinningLotto() {
                return winningLotto;
        }

        public BonusBall getBonusBall() {
                return bonusBall;
        }
}
