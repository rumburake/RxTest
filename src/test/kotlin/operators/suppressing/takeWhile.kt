package operators.suppressing

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class takeWhile {

    @Test
    fun skipWhile() {
        Observable.range(1, 100)
            .skipWhile { Random.nextInt() % 100 != 0 }
            .subscribe { i -> println("RECEIVED: $i") }
    }

    @Test
    fun takeWhile() {
        Observable.range(1, 100)
            .takeWhile { Random.nextInt() % 100 != 0 }
            .subscribe { i -> println("RECEIVED: $i") }
    }

    @Test
    fun takeUntil() {
         val subject = PublishSubject.create<Boolean>()
        Observable.interval(100, TimeUnit.MILLISECONDS)
             .takeUntil(subject)
            .subscribe { println("Got: $it")}
        Thread.sleep(1000)
        subject.onNext(false)
        Thread.sleep(1000)
    }

    @Test
    fun skipUntil() {
        val subject = PublishSubject.create<Boolean>()
        Observable.interval(100, TimeUnit.MILLISECONDS)
            .skipUntil(subject)
            .subscribe { println("Got: $it")}
        Thread.sleep(1000)
        subject.onNext(false)
        Thread.sleep(1000)
    }
}