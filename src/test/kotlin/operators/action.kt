package operators

import io.reactivex.rxjava3.core.Notification
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Test
import java.util.concurrent.TimeUnit

class action {
    @Test
    fun doOnNext() {
        Observable.just("Alpha", "Beta", "Gamma")
            .doOnNext { s -> println("Processing: $s") }
            .map(String::length)
            .subscribe { i -> println("Received: $i") }
    }

    @Test
    fun doAfterNext() {
        Observable.just("Alpha", "Beta", "Gamma")
            .doAfterNext { s -> println("Processed: $s") }
            .map(String::length)
            .subscribe { i -> println("Received: $i") }
    }

    @Test
    fun doOnComplete() {
        Observable.just("Alpha", "Beta", "Gamma")
            .doOnComplete { println("Finished processing!") }
            .map(String::length)
            .subscribe { i -> println("Received: $i") }
    }

    @Test
    fun doOnError() {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
            .doOnError { e: Throwable? -> println("Source failed!") }
            .map { i: Int -> 10 / i }
            .doOnError { e: Throwable? -> println("Division failed!") }
            .subscribe(
                { i: Int -> println("RECEIVED: $i") }
            ) { e: Throwable -> println("RECEIVED ERROR: $e") }
    }

    @Test
    fun doOnEach() {
        Observable.just("One", "Two", "Three")
            .doOnEach { s: Notification<String> ->
                println(
                    "doOnEach: $s"
                )
            }
            .doOnEach { s ->
                System.out.println(
                    "doOnEach: " +
                            s.isOnNext() + ", " + s.isOnError() +
                            ", " + s.isOnComplete()
                )
            }
            .doOnEach { s ->
                System.out.println(
                    "doOnEach: " +
                            s.getError() + ", " + s.getValue()
                )
            }
            .subscribe { i: String -> println("Received: $i") }
    }

    @Test
    fun doOnSubscribeDoOnDispose() {
        val disposable = Observable.interval(100, TimeUnit.MILLISECONDS)
            .doOnSubscribe { d: Disposable? ->
                println(
                    "Subscribing!"
                )
            }
            .doOnDispose { println("Disposing!") }
            .subscribe { i -> println("RECEIVED: $i") }
        Thread.sleep(1000)
        disposable.dispose()
        Thread.sleep(1000)
    }

    @Test
    fun doOnSuccess() {
        Observable.just(5, 3, 7)
            .reduce { total: Int, next: Int -> total + next }
            .doOnSuccess { i: Int -> println("Emitting: $i") }
            .subscribe { i: Int -> println("Received: $i") }
    }

    @Test
    fun doFinally1() {
        Observable.just("One", "Two", "Three")
            .doFinally { println("doFinally!") }
            .doAfterTerminate { println("doAfterTerminate!") }
            .subscribe { i: String -> println("Received: $i") }
    }

    @Test
    fun doEverything() {
        val disp =
            Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe { d: Disposable? ->
                    println(
                        "Subscribing!"
                    )
                }
                .doOnDispose { println("Disposing!") }
                .doFinally { println("doFinally!") }
                .doAfterTerminate { println("doAfterTerminate!") }
                .subscribe { i: Long -> println("RECEIVED: $i") }

        Thread.sleep(3000)
        disp.dispose()
        Thread.sleep(3000)
    }
}