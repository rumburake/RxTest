package operators.reducing.collection

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.CopyOnWriteArrayList


class tolist {
    @Test
    fun tolist() {
        Observable.just("Alpha", "Beta", "Gamma")
            .toList()
            .subscribe { s -> println("Received: $s") }
    }

    @Test
    fun tolistopt() {
        Observable.range(1, 1000)
            .toList(1000)
            .subscribe { s -> println("Received: $s") }
    }

    @Test
    fun tolistcustom() {
        Observable.just("Beta", "Gamma", "Alpha")
            .toList { CopyOnWriteArrayList<String>() }
            .subscribe { s: CopyOnWriteArrayList<String> ->
                println(
                    "Received: $s"
                )
            }
    }
}