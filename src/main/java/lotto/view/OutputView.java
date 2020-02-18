package lotto.view;

public class OutputView {

    public static void printErrorMessage(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static void printStartGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottePieces(int lottoPieces) {
        System.out.println(lottoPieces + "개를 구매했습니다.");
    }

    public static void printChangeMoney(int changeMoney){
        System.out.println("거스름돈은 " + changeMoney + "원 입니다.");
    }
}
