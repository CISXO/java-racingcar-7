package racingcar.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.utils.Engine;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarRacingTest {

    private CarRacing carRacing;
    private List<Car> cars;

    @BeforeEach
    void setUp() {
        Engine alwaysMovingEngine = () -> true;
        cars = List.of(
                new Car("CarA", alwaysMovingEngine),
                new Car("CarB", alwaysMovingEngine),
                new Car("CarC", alwaysMovingEngine)
        );

        carRacing = new CarRacing(cars);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 3",
            "5, 5",
            "10, 10"
    })
    @DisplayName("총 이동 횟수에 따라 모든 자동차가 올바르게 이동했는지 검증")
    void startRacing_shouldMoveAllCarsAccordingToTotalMoveCount(int totalMoveCount, int expectedMoves) {
        // when
        carRacing.startRacing(totalMoveCount);

        // then
        for (Car car : cars) {
            assertThat(car.getPosition()).isEqualTo(expectedMoves);
        }
    }

    @Test
    @DisplayName("레이싱 상태가 올바르게 문자열로 반환되는지 검증")
    void recordRaceStatus() {
        // given
        carRacing.startRacing(5);

        // when
        String raceStatus = carRacing.recordRaceStatus();

        // then
        for (Car car : cars) {
            assertThat(raceStatus).contains(car.getCarName());
            assertThat(raceStatus).contains("-".repeat(car.getPosition()));
        }
    }

    @Test
    @DisplayName("최대 위치에 있는 자동차들이 우승자로 반환되는지 검증")
    void getWinners_shouldReturnCarsWithMaxPosition() {
        // given
        carRacing.startRacing(5);

        // when
        List<String> winners = carRacing.getWinners();

        // then
        int maxPosition = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        for (String winner : winners) {
            Car car = cars.stream()
                    .filter(c -> c.getCarName().equals(winner))
                    .findFirst()
                    .orElseThrow();
            assertThat(car.getPosition()).isEqualTo(maxPosition);
        }
    }
}
