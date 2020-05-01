package operators.transforming

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

data class Fib(
    val index: Int,
    val prev: Long,
    val cur: Long
) {
    constructor(): this(0, 0, 1)

    fun next() = Fib(index + 1, cur, prev + cur)

    fun ratio() = if (prev != 0L) {
        cur.toDouble() / prev
    } else {
        0
    }
}


class scan {

    @Test
    fun fibonacci() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
            .scan ( Fib(), { fib, _ -> fib.next() })
            .subscribe { println("Got: $it - Ratio: ${it.ratio()}")}
        Thread.sleep(5000)
    }
}