package view;

import static global.exception.ExceptionMessage.INVALID_INTEGER;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private final Scanner sc;

    public InputView() {
        sc = new Scanner(System.in);
    }

    public int inputPrice() {
        System.out.println(InputMessage.PRICE.getMessage());
        try {
            int price = sc.nextInt();
            sc.nextLine();
            return price;
        }catch (InputMismatchException e){
            throw new IllegalArgumentException(INVALID_INTEGER.getMessage());
        }
    }

    public String inputWinningLotto() {
        System.out.println(InputMessage.WINNING_LOTTO.getMessage());
        return sc.nextLine();
    }

    public String inputBonusLotto() {
        System.out.println(InputMessage.BONUS.getMessage());
        return sc.nextLine();
    }
}
