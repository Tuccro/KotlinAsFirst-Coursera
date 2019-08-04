@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var result = 0
    var tmp = n
    do {
        result++
        tmp /= 10
    } while (tmp > 0)
    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
//fun fib(n: Int): Int = when (n) {
//    1, 2 -> 1
//    else -> fib(n - 1) + fib(n - 2)
//}

fun fib(n: Int): Int {
    return if(n == 1 || n==2) 1
    else {
        var result = 0
        var fib1 = 1
        var fib2 = 1

        for (i in 3..n) {
            result = fib1 + fib2

            fib2 = fib1
            fib1 = result
        }
        return result
    }
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val max = max(m, n)
    for (i in max..m * n step 1) {
        if (i % m == 0 && i % n == 0) return i
    }
    return 0
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n step 1) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n/2 downTo 1 step 1) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (m % 2 == 0 && n % 2 == 0) return false
    for (i in 3..sqrt(min(m, n).toDouble()).toInt() step 2) {
        if (m % i == 0 && n % i == 0) return false
    }
    return max(m, n) % min(m, n) != 0
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in 1..sqrt(n.toDouble()).toInt()) {
        if (i * i in m..n) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var stepsCount = 0
    var result = x

    while (result != 1) {
        result = collatzStep(result)
        stepsCount++
    }

    return stepsCount
}

fun collatzStep(x: Int) = if (x % 2 == 0) x / 2 else 3 * x + 1

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    val xDividedOnPI = x / PI / 2

    val simplifiedX = if (xDividedOnPI % xDividedOnPI.toInt() == .0) x / xDividedOnPI else x
    var sin = simplifiedX

    for (i in 3..Int.MAX_VALUE step 2) {
        val result = simplifiedX.pow(i) / factorial(i)
        if (abs(result) < eps) break
        sin = if (((i - 1) / 2) % 2 == 1) sin - result else sin + result
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val xDividedOnPI = x / PI / 2

    val simplifiedX = if (xDividedOnPI % xDividedOnPI.toInt() == .0) x / xDividedOnPI else x
    var cos = 1.0

    for (i in 2..Int.MAX_VALUE step 2) {
        val result = simplifiedX.pow(i) / factorial(i)
        if (abs(result) < eps) break
        cos = if ((i / 2) % 2 == 1) cos - result else cos + result
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var result = 0
    var tmp = n

    while (tmp > 0) {
        result += tmp % 10
        tmp /= 10
        if (tmp > 0) result *= 10
    }

    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val numberOfDigits = getNumberOfDigits(n)

    if (numberOfDigits == 1) return true
    for (i in 0 until numberOfDigits / 2 step 1) {
        if (getNumbersNDigit(n, i + 1) != getNumbersNDigit(n, numberOfDigits - i))
            return false
    }
    return true
}

fun getNumbersNDigit(number: Int, n: Int): Int = if (n == 1) number % 10 else (number / 10.toDouble().pow(n - 1).toInt()) % 10

fun getNumberOfDigits(n: Int): Int {
    var number = n
    var numberOfDigits = 0
    while (number > 0) {
        numberOfDigits++
        number /= 10
    }
    return numberOfDigits
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val numberOfDigits = getNumberOfDigits(n)

    if (numberOfDigits == 1) return false

    val firstDigit = n % 10
    for (i in 2..numberOfDigits step 1) {
        if (getNumbersNDigit(n, i) != firstDigit) return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    if (n > 1) {
        var digitsCount = 0
        for (i in 1..Int.MAX_VALUE step 1) {
            val tmp = i * i
            val numberDigitsNumber = getNumberOfDigits(tmp)
            digitsCount += numberDigitsNumber
            if (digitsCount >= n) {
                val digitNumber = digitsCount - n + 1
                return getNumbersNDigit(tmp, digitNumber)
            }
        }
    }
    return 1
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    if (n > 1) {
        var digitsCount = 0
        for (i in 1..Int.MAX_VALUE step 1) {
            val tmp = fib(i)
            val numberDigitsNumber = getNumberOfDigits(tmp)
            digitsCount += numberDigitsNumber
            if (digitsCount >= n) {
                val digitNumber = digitsCount - n + 1
                return getNumbersNDigit(tmp, digitNumber)
            }
        }
    }
    return 1
}
