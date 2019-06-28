package lotto.model.dto;

public class RoundInfoDTO {
        private int payment;
        private int manualPurchaseNumber;
        private String winningLotto;
        private int bonusBall;

        public RoundInfoDTO(int payment, int manualPurchaseNumber, String winningLotto, int bonusBall) {
                this.payment = payment;
                this.manualPurchaseNumber = manualPurchaseNumber;
                this.winningLotto = winningLotto;
                this.bonusBall = bonusBall;
        }

        public int getPayment() {
                return payment;
        }

        public void setPayment(int payment) {
                this.payment = payment;
        }

        public int getManualPurchaseNumber() {
                return manualPurchaseNumber;
        }

        public void setManualPurchaseNumber(int manualPurchaseNumber) {
                this.manualPurchaseNumber = manualPurchaseNumber;
        }

        public String getWinningLotto() {
                return winningLotto;
        }

        public void setWinningLotto(String winningLotto) {
                this.winningLotto = winningLotto;
        }

        public int getBonusBall() {
                return bonusBall;
        }

        public void setBonusBall(int bonusBall) {
                this.bonusBall = bonusBall;
        }
}
