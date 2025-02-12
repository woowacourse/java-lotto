package View;

import java.util.Scanner;

public class InputView {

    public static int InputPrice() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int price = validateInteger(input);
        validatePrice(price);
        sc.close();
        return price;
    }

    private static void validatePrice(int price) {
        if (price % 1000 != 0) {
            throw new IllegalArgumentException("구매 가격은 1000원 단위로만 입력 가능합니다.");
        }
    }

    private static int validateInteger(String input) {
        try {
            return Integer.parseInt(input);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.", e);
        }
    }
}
