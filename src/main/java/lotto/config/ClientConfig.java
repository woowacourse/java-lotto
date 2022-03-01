package lotto.config;

import lotto.client.ConsoleErrorClient;
import lotto.client.ConsoleInputClient;
import lotto.client.ConsoleOutputClient;
import lotto.client.ErrorClient;
import lotto.client.InputClient;
import lotto.client.OutputClient;

public class ClientConfig {

    public static InputClient getInputClient() {
        return ConsoleInputClient.getInstance();
    }

    public static OutputClient getOutputClient() {
        return ConsoleOutputClient.getInstance();
    }

    public static ErrorClient getErrorClient() {
        return ConsoleErrorClient.getInstance();
    }
}
