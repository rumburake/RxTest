package operators.reducing

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

class count {

    @Test
    fun countTest() {
        Observable.just("Alpha", "Beta", "Gamma")
            .count()
            .subscribe { s: Long -> println("Received: $s") }
    }

    @Test
    fun countRolling() {
        Observable.just("Alpha", "Beta", "Gamma")
            .scan (0, { t, i -> t + 1 })
            .subscribe { s -> println("Received: $s") }
    }

    @Test
    fun countRollingInfinite() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
            .scan (0, { t, i -> t + 1 })
            .subscribe { s -> println("Received: $s") }
        Thread.sleep(2000)
    }



}