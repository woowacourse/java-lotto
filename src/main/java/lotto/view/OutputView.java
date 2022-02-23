package lotto.view;

public class OutputView {
    public void printAskMoneyInputMessage() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public void printErrorMessage(String e){
        System.out.println(e);
    }

    public void printMoneyAmount(int money) {
        System.out.println(money);
    }

    public void printLastWeekWinningMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printLastWeekBonusMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }
}
