package operators

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Timed
import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit


class utility {
    @Test
    fun delay() {
        val f = DateTimeFormatter.ofPattern("MM:ss")
        println(LocalDateTime.now().format(f))
        Observable.just("Alpha", "Beta", "Gamma")
            .delay(3, TimeUnit.SECONDS)
            .subscribe({ s ->
                println(
                    LocalDateTime.now()
                        .format(f) + " Received: " + s
                )
            })
        Thread.sleep(5000)
    }
    @Test
    fun repeat() {
        Observable.just("Alpha", "Beta", "Gamma")
            .repeat(2)
            .subscribe { s: String -> println("Received: $s") }
    }
    @Test
    fun single() {
        Observable.just("One")
            .single("Four")
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
        Observable.empty<String>()
            .single("Empty")
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
        Observable.just("One", "Two")
            .single("Many")
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
    }
    @Test
    fun singleElement() {
        Observable.just("One")
            .singleElement()
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
        Observable.empty<String>()
            .singleElement()
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
        Observable.just("One", "Two")
            .singleElement()
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
    }
    @Test
    fun singleOnError() {
        Observable.just("One")
            .singleOrError()
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
        Observable.empty<String>()
            .singleOrError()
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
        Observable.just("One", "Two")
            .singleOrError()
            .subscribe(
                { i: String -> println("Received: $i") },
                { e: Throwable -> println("Received: $e") }
            )
    }

    @Test
    fun timestamp() {
        Observable.just("One", "Two", "Three")
            .timestamp(TimeUnit.SECONDS)
            .doOnNext { println("$it")}
            .subscribe { i: Timed<String> ->
                println(
                    "Received: ${i.value()}"
                )
            }
    }
    @Test
    fun timeInterval() {
        Observable.interval(1, TimeUnit.SECONDS)
            .doOnNext { i: Long -> println("Emitted: $i") }
            .take(3)
            .timeInterval(TimeUnit.SECONDS)
            .doOnNext { println("$it")}
            .subscribe { i: Timed<Long?> -> println("Received: ${i.value()}") }
        Thread.sleep(7500)
    }
}