package operators.reducing

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.time.LocalDate

class boolean {

    @Test
    fun all() {
        Observable.just(5, 3, 7, 11, 2, 14)
            .all { i -> i < 10 }
            .subscribe { s -> System.out.println("Received: "+s) }
    }

    @Test
    fun any() {
        Observable.just(
            "2016-01-01", "2016-05-02",
            "2016-09-12", "2016-04-03"
        )
            .map ( LocalDate::parse )
            .any { dt -> dt.getMonthValue() >= 6 }
            .subscribe { s -> System.out.println("Received: "+s) }
    }

    @Test
    fun isempty() {
        Observable.just("One", "Two", "Three")
            .filter { s -> s.contains("z") }
            .isEmpty()
            .subscribe { s -> System.out.println("Received1: "+s) }

        Observable.just("One", "Twoz", "Three")
            .filter { s -> s.contains("z") }
            .isEmpty()
            .subscribe { s -> System.out.println("Received2: "+s) }
    }

    @Test
    fun contains() {
        Observable.range(1, 10000)
            .contains(9563)
            .subscribe { s -> System.out.println("Received: " + s) }
    }

    @Test
    fun sequenceEqual() {
        val obs1 = Observable.just("One", "Two", "Three")
        val obs2 = Observable.just("One", "Two", "Three")
        val obs3 = Observable.just("Two", "One", "Three")
        val obs4 = Observable.just("One", "Two")

        Observable.sequenceEqual(obs1, obs2)
            .subscribe { s: Boolean -> println("Received: $s") }

        Observable.sequenceEqual(obs1, obs3)
            .subscribe { s: Boolean -> println("Received: $s") }

        Observable.sequenceEqual(obs1, obs4)
            .subscribe { s: Boolean -> println("Received: $s") }
    }

}