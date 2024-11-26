package racingcar.controller;

import racingcar.model.Car;
import racingcar.model.CarRacing;
import racingcar.utils.CarRacingValidator;
import racingcar.utils.Engine;
import racingcar.utils.RandomEngine;
import racingcar.utils.Validator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class CarRacingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Validator validator = new CarRacingValidator();
    private CarRacing carRacing;

    public CarRacingController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        process(this::requestCarName);
        process(this::initializeCarRacing);
        process(this::startRacing);
        process(this::showWinners);
    }

    private void requestCarName() {
        outputView.printReadCarNameMessage();
        String readCarString = inputView.readInput();
        inputView.parserInputString(readCarString);
    }

    private void initializeCarRacing() {
        List<Car> cars = createCarList(inputView.getParserInputString());
        carRacing = new CarRacing(cars);
    }

    private void startRacing() {
        outputView.printReadTotalTryMessage();
        String readTotalMove = inputView.readInput();
        validator.validation(readTotalMove);

        int totalMoveCount = Integer.parseInt(readTotalMove);
        carRacing.startRacing(totalMoveCount);

        outputView.printRunResult();
        outputView.printMessage(carRacing.recordRaceStatus());
    }

    private void showWinners() {
        List<String> winners = carRacing.getWinners();
        outputView.printWinners(winners);
    }

    private List<Car> createCarList(List<String> carNameList) {
        Engine randomEngine = new RandomEngine();
        List<Car> cars = new ArrayList<>();

        for (String carName : carNameList) {
            cars.add(new Car(carName, randomEngine));
        }
        return cars;
    }

    private void process(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            outputView.exception(e);
            throw e;
        }
    }
}
