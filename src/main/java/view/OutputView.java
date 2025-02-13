package view;

import domain.Lottos;

public class OutputView {
    public static final String DISPLAY_BUY_COUNT = "%d개를 구매했습니다.\n";

    public void displayLottoNumbers(Lottos lottos) {
        System.out.printf(DISPLAY_BUY_COUNT, lottos.size());
        for (int idx = 0; idx < lottos.size(); idx++) {
            System.out.println(lottos.getLottoByIndex(idx).getNumbers());
        }
        displaySpacing();
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        displaySpacing();
    }

    public static void displaySpacing() {
        System.out.println();
    }
}