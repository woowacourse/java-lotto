package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String getUserInput() {
        try {
            return br.readLine().trim();
        } catch (IOException ie) {
            getUserInput();
        }
        return null;
    }
}
