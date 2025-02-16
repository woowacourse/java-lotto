package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private static final String ERROR_IOException = "입출력 오류 발생: ";

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String read(String prompt) {
        System.out.println(prompt);
        return readLine();
    }

    private String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println(ERROR_IOException + e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println(ERROR_IOException + e.getMessage());
        }
    }
}
