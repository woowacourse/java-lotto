package lotto.config;

import java.util.Scanner;

import lotto.client.ConsoleErrorClient;
import lotto.client.ConsoleInputClient;
import lotto.client.ConsoleOutputClient;
import lotto.client.ErrorClient;
import lotto.client.InputClient;
import lotto.client.OutputClient;

public class ClientConfig {

    public static InputClient getInputClient() {
        return new ConsoleInputClient(getScanner(), getOutputClient());
    }

    private static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static OutputClient getOutputClient() {
        return new ConsoleOutputClient();
    }

    public static ErrorClient getErrorClient() {
        return new ConsoleErrorClient();
    }
}
