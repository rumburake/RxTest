package operators.transforming

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class startWith {

    @Test
    fun startWith() {
        val menu = Observable.just("Coffee", "Tea", "Espresso", "Latte")

        //print menu
        menu.startWithItem("COFFEE SHOP MENU")
            .subscribe(System.out::println)
    }

    @Test
    fun startWithArray() {
        val menu = Observable.just("Coffee", "Tea", "Espresso", "Latte")

        //print menu
        val menu2 = menu.startWithArray("COFFEE SHOP MENU", "order something from here")

        val menu3 = menu2.startWithItem("welcome")

        menu3
            .subscribe(System.out::println)
    }

    @Test
    fun startWithIterable() {
        val menu = Observable.just("Coffee", "Tea", "Espresso", "Latte")

        //print menu
        menu.startWithIterable(listOf("a", "b", "c"))
            .subscribe(System.out::println)
    }



}