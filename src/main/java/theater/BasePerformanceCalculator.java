package theater;

/** Task 4.2 needs a concrete subclass so the abstract class can be instantiated */
class BasePerformanceCalculator extends AbstractPerformanceCalculator {
    BasePerformanceCalculator(Performance performance, Play play) {
        super(performance, play);
    }
}
