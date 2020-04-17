package operators.suppressing

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.TimeUnit

class take() {

    @Test
    fun take() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .take(3)
            .subscribe { foo -> println("Got: $foo!") }
    }

    @Test
    fun takeTime() {
        Observable.interval(1, TimeUnit.SECONDS)
            .take(3200, TimeUnit.MILLISECONDS)
            .subscribe { foo -> println("Got $foo")}
        Thread.sleep(5000)
    }

    @Test
    fun takeLast() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .takeLast(3)
            .subscribe { foo -> println("Got: $foo!") }
    }

    @Test
    fun takeLastTime() {
        Observable.interval(10, TimeUnit.MILLISECONDS)
            .takeLast(111, TimeUnit.MILLISECONDS)
            .subscribe { foo -> println("Got $foo")}
        Thread.sleep(300)
    }
}
