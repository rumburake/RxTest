package operators.transforming

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class mapa {

    @Test
    fun map() {
        val dtf = DateTimeFormatter.ofPattern("M/d/yyyy");

        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
            .map { s -> LocalDate.parse(s, dtf) }
            .subscribe { i -> println("RECEIVED: " + i) }
    }
}