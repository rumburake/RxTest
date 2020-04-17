package operators.transforming

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

data class Fib(
    val index: Int,
    val prev: Long,
    val cur: Long
) {
    constructor(): this(0, 1, 0)

    fun next() = Fib(index + 1, cur, prev + cur)
}


class scan {

    @Test
    fun fibonacci() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
            .scan ( Fib(), { fib, _ -> fib.next() })
            .subscribe { println("Got: $it")}
        Thread.sleep(30000)
    }
}