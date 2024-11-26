package racingcar.model;

import racingcar.utils.CarNameValidator;
import racingcar.utils.Engine;
import racingcar.utils.Validator;

public class Car {
    private final String carName;
    private final Engine engine;
    private int position;

    Validator validator = new CarNameValidator();

    public Car(String carName, Engine randomEngine) {
        validator.validation(carName);
        this.carName = carName;
        this.engine = randomEngine;
        this.position = 0;
    }

    public String getCarName() {
        return carName;
    }

    public void move() {
        if (canMove()) {
            position++;
        }
    }

    public int getPosition() {
        return position;
    }
    private boolean canMove() {
        return engine.isEngineRunning();
    }
}
