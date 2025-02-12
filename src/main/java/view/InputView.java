package view;

import java.util.Scanner;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    public static int inputPrice(){
        System.out.println("구입금액을 입력해 주세요.");
        int price = Integer.parseInt(sc.nextLine());
        return price;
    }
}
