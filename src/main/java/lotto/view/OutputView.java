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
}
