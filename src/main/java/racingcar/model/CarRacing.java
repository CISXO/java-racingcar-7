package racingcar.model;

import java.util.List;

public class CarRacing {

    private final List<Car> cars;
    private int totalMove;

    public CarRacing(List<Car> cars) {
        this.cars = cars;
    }

    public void startRacing(int totalMoveCount) {
        this.totalMove = totalMoveCount;

        for (int i = 0; i < totalMove; i++) {
            moveCars();
        }
    }

    private void moveCars() {
        for (Car car : cars) {
            car.move();
        }
    }

    public String recordRaceStatus() {
        StringBuilder status = new StringBuilder();
        for (Car car : cars) {
            status.append(car.getCarName()).append(" : ").append("-".repeat(car.getPosition())).append("\n");
        }
        return status.toString();
    }

    public List<String> getWinners() {
        int maxPosition = cars.stream().mapToInt(Car::getPosition).max().orElse(0);
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getCarName)
                .toList();
    }
}
