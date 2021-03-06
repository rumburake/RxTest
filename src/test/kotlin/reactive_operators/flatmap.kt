package reactive_operators

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import org.junit.Test
import java.util.concurrent.TimeUnit

class flatmap {

    @Test
    fun flatmap_letters() {
        Observable.just<String>("one", "two", "three")
            .flatMap { Observable.fromIterable(it.split("")) }
            .subscribe { print("#$it#")}
    }

    @Test
    fun flatmap_parse() {
        Observable.just("1 time I found 2 fish in 3 lakes with 4 eyes.", "99 bottles and 15 crew")
            .flatMap { Observable.fromIterable(it.split(" ")) }
            .filter { it.matches(Regex("[0-9]+"))}
            .map (Integer::valueOf)
            .subscribe { print("$it ")}
    }

    @Test
    fun flatmap_interval() {
        Observable.just(1, 2, 3, 4)
            .flatMap { nominal ->
                if (nominal != null) {
                    Observable.interval(nominal.toLong(), TimeUnit.SECONDS)
                        .map { "$nominal sec interval: ${(it + 1) * nominal} sec passed" }
                } else {
                    Observable.empty()
                }
            }
            .subscribe { println("$it") }
        Thread.sleep(11000)
    }

    @Test
    fun interval() {
        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { println("$it") }
        Thread.sleep(2500)
    }

    @Test
    fun flatmap_combiner() {
        Observable.just("one", "two", "three")
            .flatMap(
                { str -> Observable.fromIterable(str.split("")) },
                { s, r -> s + " - " + r }
            )
            .subscribe { println("$it") }
    }

    @Test
    fun flatmap_combiner_iterable() {
        Observable.just("one", "two", "three")
            .flatMapIterable(
                { str -> str.split("") },
                { s, r -> s + " - " + r }
            )
            .subscribe { println("$it") }
    }

    @Test
    fun flatmap_single() {
        Observable.just("one", "two", "three")
            .flatMapSingle {str -> Single.just(str.length) }
            .subscribe { println("$it") }
    }

    @Test
    fun flatmap_maybe() {
        Observable.just("one", "two", "three")
            .flatMapMaybe {str -> if (str.length > 3) {
                Maybe. just(str.length)
            } else {
                Maybe.empty()
            } }
            .subscribe { println("$it") }
    }

}