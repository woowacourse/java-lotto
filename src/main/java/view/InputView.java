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
        System.out.println(Input.PRICE.getMessage());
        try {
            return sc.nextInt();
        }catch (InputMismatchException e){
            throw new IllegalArgumentException(INVALID_INTEGER.getMessage());
        }
    }

    public String inputWinningLotto() {
        System.out.println(Input.WINNING_LOTTO.getMessage());
        return sc.nextLine();
    }

    public String inputBonusLotto() {
        System.out.println(Input.BONUS.getMessage());
        return sc.nextLine();
    }
}
