package operators.reducing.collection

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class tosortedlist {

    @Test
    fun simple() {
        Observable.just("Beta", "Gamma", "Alpha")
            .toSortedList()
            .subscribe({ s -> println("Received: $s") })
    }

    @Test
    fun custom() {
        Observable.just("Beta", "Gamma", "Alpha")
            .toSortedList(Comparator.reverseOrder())
            .subscribe({ s -> println("Received: $s") })
    }

    @Test
    fun custom2() {
        Observable.just("Beta", "Gammaa", "Alpha")
            .toSortedList { s1, s2 -> s2.length - s1.length}
            .subscribe({ s -> println("Received: $s") })
    }


}