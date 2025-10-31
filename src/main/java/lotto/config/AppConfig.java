package lotto.config;

import lotto.domain.LottoMachine;
import lotto.domain.NumberGenerator;
import lotto.domain.RandomNumberGenerator;
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

    public LottoMachine lottoMachine() {
        return new LottoMachine(numberGenerator());
    }

    private InputParser inputParser() {
        return new InputParser();
    }

    private NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }
}
