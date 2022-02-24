package lotto.config;

import lotto.client.ConsoleErrorClient;
import lotto.client.ConsoleInputClient;
import lotto.client.ConsoleOutputClient;
import lotto.client.ErrorClient;
import lotto.client.InputClient;
import lotto.client.OutputClient;

public class ClientConfig {

    public static InputClient getInputClient() {
        return new ConsoleInputClient(getOutputClient());
    }

    public static OutputClient getOutputClient() {
        return new ConsoleOutputClient();
    }

    public static ErrorClient getErrorClient() {
        return new ConsoleErrorClient();
    }
}
