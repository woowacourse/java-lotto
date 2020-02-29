package lotto.view;

public class OutputView {
    public static void printErrorMessage(String errorMessage){
        System.out.println(errorMessage);
    }

    public static void printChangeMoney(String changeMoney){
        System.out.printf("거스름돈은 %s원 입니다.\n",changeMoney);
    }
}
