package operators.suppressing

import org.junit.Test
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class skip() {

    @Test
    fun skip() {
        Observable.range(1, 100)
            .skip(90)
            .subscribe { println("Got: $it")}
    }

    @Test
    fun skipLast() {
        Observable.range(1, 100)
            .skipLast(90)
            .subscribe { println("Got: $it")}
    }

    @Test
    fun skipTime() {
        Observable.interval(10, TimeUnit.MILLISECONDS)
            .skip(111, TimeUnit.MILLISECONDS)
            .subscribe { println("Got: $it")}
        Thread.sleep(200)
    }

    @Test
    fun skipLastTime() {
        Observable.interval(10, TimeUnit.MILLISECONDS)
            .skipLast(111, TimeUnit.MILLISECONDS)
            .subscribe { println("Got: $it")}
        Thread.sleep(300)
    }
}
