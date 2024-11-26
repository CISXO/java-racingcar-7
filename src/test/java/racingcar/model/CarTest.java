package racingcar.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.utils.CarNameValidator;
import racingcar.utils.Engine;
import racingcar.utils.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarTest {

    private Car car;
    private Engine alwaysMovingEngine;

    @BeforeEach
    void setUp() {
        alwaysMovingEngine = () -> true;

        car = new Car("CarA", alwaysMovingEngine);
    }

    @Test
    @DisplayName("자동차 이름을 올바르게 반환하는지 테스트")
    void getCarName() {
        // when
        String carName = car.getCarName();

        // then
        assertThat(carName).isEqualTo("CarA");
    }

    @Test
    @DisplayName("엔진이 실행 중일 때 자동차가 이동하는지 테스트")
    void moveEngineRunningPosition() {
        // given
        int initialPosition = car.getPosition();

        // when
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(initialPosition + 1);
    }

    @Test
    @DisplayName("엔진이 실행 중이 아닐 때 자동차가 이동하지 않는지 테스트")
    void engineNotRunningPosition() {
        // given
        Engine neverRunningEngine = () -> false;
        Car nonMovingCar = new Car("CarB", neverRunningEngine);
        int initialPosition = nonMovingCar.getPosition();

        // when
        nonMovingCar.move();

        // then
        assertThat(nonMovingCar.getPosition()).isEqualTo(initialPosition);
    }

    @Test
    @DisplayName("자동차의 초기 위치가 0인지 테스트")
    void initialPositionZero() {
        // when
        int initialPosition = car.getPosition();

        // then
        assertThat(initialPosition).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차 이름이 유효하지 않을 때 예외를 발생시키는지 테스트")
    void invalidCarName_Exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car("", alwaysMovingEngine);
        });
    }

    @Test
    @DisplayName("자동차 이름의 길이를 초과했을 때 예외를 발생시키는지 테스트")
    void invalidCarNameLength_Exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car("abcdef", alwaysMovingEngine);
        });
    }

}
