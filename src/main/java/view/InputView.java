package view;


import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public String inputPrice(){
        System.out.println("구매금액을 입력해주세요.");
        return scanner.nextLine();
    }

    public String inputWinningLotto(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public String inputBonus(){
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
