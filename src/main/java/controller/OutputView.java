package controller;

import domain.Lotto;

import java.util.List;

public class OutputView {
    
    public void printInputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }
    
    public void printPurchase(int purchaseCount) {
        System.out.printf("%d개를 구매했습니다.\n", purchaseCount);
    }
    
    public void printLottos(List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append(lotto).append("\n");
        }
        System.out.print(sb);
    }
    
    public void printMatchLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }
}
