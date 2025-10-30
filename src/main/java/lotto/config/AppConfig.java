package lotto.config;

import lotto.view.InputParser;
import lotto.view.InputView;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public InputView inputView() {
        return new InputView(inputParser());
    }

    private InputParser inputParser() {
        return new InputParser();
    }
}
