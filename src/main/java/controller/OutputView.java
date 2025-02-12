package controller;

public class OutputView {
    
    public void printInputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }
    
    public void printPurchase(int purchaseCount) {
        System.out.printf("%d개를 구매했습니다.\n", purchaseCount);
        
    }
}
