package operators

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import kotlin.random.Random

class error {
    @Test
    fun error() {
        Observable.just(5, 2, 4, 0, 3)
            .map({ i -> 10 / i })
            .subscribe({ i -> println("RECEIVED: $i") },
                { e -> println("RECEIVED ERROR: $e") })
    }

    @Test
    fun onErrorReturnItem() {
        Observable.just(5, 2, 4, 0, 3)
            .map { i: Int -> 10 / i }
            .onErrorReturnItem(-1)
            .subscribe(
                { i: Int -> println("RECEIVED: $i") }
            ) { e: Throwable -> println("RECEIVED ERROR: $e") }
    }

    @Test
    fun onErrorReturn() {
        Observable.just(5, 2, 4, 0, 3)
            .map { i: Int -> 10 / i }
            .onErrorReturn { e: Throwable? -> if (e is ArithmeticException) -1 else 0 }
            .subscribe(
                { i: Int -> println("RECEIVED: $i") }
            ) { e: Throwable -> println("RECEIVED ERROR: $e") }
    }

    @Test
    fun catchError() {
        Observable.just(5, 2, 4, 0, 3)
            .map { i ->
                try {
                    10 / i;
                } catch (e: ArithmeticException) {
                    -1;
                }
            }
            .subscribe({ i -> System.out.println("RECEIVED: " + i) },
                { e -> System.out.println("RECEIVED ERROR: " + e) })
    }

    @Test
    fun onErrorResumeWith() {
        Observable.just(5, 2, 4, 0, 3)
            .map { i: Int -> 10 / i }
            .onErrorResumeWith(Observable.just(-1).repeat(3))
            .subscribe(
                { i: Int -> println("RECEIVED: $i") }
            ) { e: Throwable -> println("RECEIVED ERROR: $e") }
    }

    @Test
    fun onErrorResumeWithEmpty() {
        Observable.just(5, 2, 4, 0, 3)
            .map { i: Int -> 10 / i }
            .onErrorResumeWith(Observable.empty())
            .subscribe(
                { i: Int -> println("RECEIVED: $i") }
            ) { e: Throwable -> println("RECEIVED ERROR: $e") }
    }

    @Test
    fun onErrorResumeNext() {
        Observable.just(5, 2, 4, 0, 3)
            .map { i: Int -> 10 / i }
            .onErrorResumeNext { e: Throwable? ->
                Observable.just(
                    -1
                ).repeat(3)
            }
            .subscribe(
                { i: Int -> println("RECEIVED: $i") }
            ) { e: Throwable -> println("RECEIVED ERROR: $e") }
    }

    @Test
    fun retry() {
        Observable.just(5, 2, 4, 0, 3)
            .map { i -> 10 / i }
            .retry(2)
            .subscribe({ i -> System.out.println("RECEIVED: " + i) },
                { e -> System.out.println("RECEIVED ERROR: " + e) });
    }

    @Test
    fun retryUntil() {
        Observable.just(5, 2, 4, 0, 3)
            .map { i -> 10 / i }
            .retryUntil { Random.nextInt(10) == 0 }
            .subscribe({ i -> System.out.println("RECEIVED: " + i) },
                { e -> System.out.println("RECEIVED ERROR: " + e) });
    }
}