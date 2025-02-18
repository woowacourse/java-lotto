package view;

import exception.CommonExceptionType;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {

    private static final String DELIMITER = ", ";

    private final List<Integer> inputs;

    public static InputParser of(final String input) {
        List<String> splitInputs = List.of(input.split(DELIMITER));
        List<Integer> inputs = splitInputs.stream()
                .map(InputParser::getIntegerInput)
                .collect(Collectors.toList());
        return new InputParser(inputs);
    }

    private InputParser(List<Integer> inputs) {
        this.inputs = inputs;
    }

    private static int getIntegerInput(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(CommonExceptionType.INVALID_NUMBER_FORMAT.getMessage());
        }
    }

    public int getInput() {
        if (inputs.size() != 1) {
            throw new IllegalArgumentException(CommonExceptionType.INVALID_ARGUMENTS_SIZE.getMessage(1));
        }
        return inputs.get(0);
    }

    public List<Integer> getInputs() {
        return inputs;
    }
}
