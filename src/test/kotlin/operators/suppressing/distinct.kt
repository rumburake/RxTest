package operators.suppressing

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class distinct {

    @Test
    fun distinctSimple() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .map { it.length }
            .distinct()
            .subscribe({ i -> println("RECEIVED: $i") })
    }

    @Test
    fun distinctMap() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .distinct { it.length}
            .subscribe({ i -> println("RECEIVED: $i") })
    }

    @Test
    fun distinctUntilChanged() {
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
            .distinctUntilChanged()
            .subscribe { i: Int -> println("RECEIVED: $i") }
    }

    @Test
    fun distinctUntilChangedMap() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .distinctUntilChanged(String::length)
            .subscribe({ i -> println("RECEIVED: $i") })
    }


}