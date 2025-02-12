import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public class Main {
    public static final int LOTTO_PRICE = 1000;

    public static void main(String[] args){
        String price = inputPrice();
        int amount = getAmount(price);
    }

    public static String inputPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return sc.nextLine();
    }

    // todo : 예외처리
    public static int getAmount(String inputPrice) {
        return Integer.parseInt(inputPrice) / LOTTO_PRICE;
    }
}
