package lotto.view;

import java.util.Scanner;

public class KeyEnter implements Entering {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String enter() {
        return scanner.nextLine();
    }
}
