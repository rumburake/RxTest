package operators.transforming

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

class sorted {

    @Test
    fun sorted() {
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
            .sorted()
            .subscribe(System.out::println);
    }

    @Test
    fun wrong() {
        Observable.interval(1, TimeUnit.MICROSECONDS)
            .map { "The new number is: $it" }
            .sorted()
            .subscribe { println(it) }
        Thread.sleep(20000)
    }

    @Test
    fun comparator() {
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
            .sorted(Comparator.reverseOrder())
            .subscribe(System.out::print);
    }

    @Test
    fun comparatorCustom() {
        Observable.just("alpha", "beta", "eta")
            .sorted(Comparator.comparingInt(String::length))
            .subscribe(::println)
    }
}