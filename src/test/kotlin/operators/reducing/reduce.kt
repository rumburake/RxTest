package operators.reducing

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class reduce {
    @Test
    fun reduce() {
        Observable.just(5, 3, 7)
            .reduce({ total, i -> total + i })
            .subscribe { s -> println("Received: $s") }
    }

    @Test
    fun reduce_str() {
        Observable.just(5, 3, 7)
            .reduce("",
                { total: String, i: Int -> total + (if (total == "") "" else ",") + i }
            )
            .subscribe { s: String -> println("Received: $s") }
    }
}