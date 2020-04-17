package operators.suppressing

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class filter() {

        @Test
        fun filter() {

                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                        .filter { s -> s.length != 5 }
                        .subscribe(
                                { System.out.println("Got: $it")},
                                { System.out.println("Error")},
                                { System.out.println("Complete!")}
                        )
        }

}
