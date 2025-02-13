package view;

public class OutputView {
    public void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        displaySpacing();
    }

    public static void displaySpacing() {
        System.out.println();
    }
}