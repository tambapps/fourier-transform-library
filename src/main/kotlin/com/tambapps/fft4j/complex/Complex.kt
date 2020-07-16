package com.tambapps.fft4j.complex

import com.tambapps.fft4j.util.DoubleTester.equal
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

/**
 * Representation of a complex number
 */
class Complex constructor(val real: Double, val imaginary: Double) {

    constructor(real: Double): this(real, 0.0)

    /**
     * Returns whether this number is pure imaginary (null real part)
     *
     * @return whether it is a pure imaginary number
     */
    val isPureImaginary: Boolean
        get() = equal(0.0, real)

    /**
     * Returns whether this number is pure real (null imaginary part)
     *
     * @return whether it is a real number
     */
    val isPureReal: Boolean
        get() = equal(0.0, imaginary)

    operator fun component1(): Double {
        return real
    }

    operator fun component2(): Double {
        return imaginary
    }

    /**
     * Computes scales this complex number with the given scalar
     *
     * @param scl the scalar
     * @return the scaled number
     */
    operator fun times(scl: Double): Complex {
        return Complex(scl * real, scl * imaginary)
    }

    /**
     * Computes scales this complex number with the given scalar
     *
     * @param scl the scalar
     * @return the scaled number
     */
    operator fun times(scl: Int): Complex {
        return Complex(scl * real, scl * imaginary)
    }

    /**
     * Returns the negate of this number
     *
     * @return the negate
     */
    operator fun unaryMinus(): Complex {
        return Complex(-real, -imaginary)
    }

    /**
     * Returns this number
     *
     * @return this
     */
    operator fun unaryPlus(): Complex {
        return this
    }

    /**
     * Returns the absolute value (or modulus) of this complex
     *
     * @return the modulus
     */
    fun abs(): Double {
        return hypot(real, imaginary)
    }

    /**
     * Util function to access complex fields as an array <br></br>
     * index 0 is the real part <br></br>
     * index 1 is the imaginary part
     *
     * @param i the index
     * @return the associated field number
     */
    fun get(i: Int): Double {
        if (i > 1 || i < 0) {
            throw IndexOutOfBoundsException("There is only two fields in a complex number")
        }
        return if (i == 0) real else imaginary
    }

    /**
     * Returns the addition between this number and the argument
     *
     * @param complex the argument
     * @return the sum of this number and the argument
     */
    operator fun plus(complex: Complex): Complex {
        return Complex(real + complex.real, imaginary + complex.imaginary)
    }

    /**
     * Returns the addition between this number and the argument
     *
     * @param d the argument
     * @return the sum of this number and the argument
     */
    operator fun plus(d: Double): Complex {
        return Complex(real + d, imaginary)
    }

    /**
     * Returns this number subtracted by the argument
     *
     * @param complex the argument
     * @return the subtraction between this number and the argument
     */
    operator fun minus(complex: Complex): Complex {
        return Complex(real - complex.real, imaginary - complex.imaginary)
    }

    /**
     * Returns this number subtracted by the argument
     *
     * @param d the argument
     * @return the subtraction between this number and the argument
     */
    operator fun minus(d: Double): Complex {
        return Complex(real - d, imaginary)
    }

    /**
     * Returns this number divided by the argument
     *
     * @param complex the argument
     * @return the division of this number by the argument
     */
    operator fun div(complex: Complex): Complex {
        val denom = complex.real * complex.real + complex.imaginary * complex.imaginary
        return Complex((real * complex.real + imaginary * complex.imaginary) / denom,
                (imaginary * complex.real - real * complex.imaginary) / denom)
    }

    /**
     * Returns this number divided by the argument
     *
     * @param d the argument
     * @return the division of this number by the argument
     */
    operator fun div(d: Double): Complex {
        require(!equal(d, 0.0)) { "Cannot divide by 0" }
        return Complex(real / d, imaginary / d)
    }

    /**
     * Returns the conjugate of this number
     *
     * @return the conjugate
     */
    fun conj(): Complex {
        return Complex(real, -imaginary)
    }

    /**
     * Returns the conjugate of this number
     *
     * @return the conjugate
     */
    operator fun not(): Complex {
        return conj()
    }

    /**
     * Returns the multiplication between this number and the argument
     *
     * @param complex the argument
     * @return the multiplication of this number and the argument
     */
    operator fun times(complex: Complex): Complex {
        val real = real * complex.real - imaginary * complex.imaginary
        val imaginary = this.real * complex.imaginary + imaginary * complex.real
        return Complex(real, imaginary)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        if (!equal(0.0, real)) {
            builder.append(real)
            if (!equal(0.0, imaginary)) {
                builder.append(" + i * ").append(imaginary)
            }
        } else {
            if (!equal(0.0, imaginary)) {
                builder.append("i * ").append(imaginary)
            } else {
                builder.append("0")
            }
        }
        return builder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val (real1, imaginary1) = other as Complex
        return equal(real1, real) &&
                equal(imaginary1, imaginary)
    }

    override fun hashCode(): Int {
        var result = real.hashCode()
        result = 31 * result + imaginary.hashCode()
        return result
    }

    companion object {
        val I = Complex(0.0, 1.0)
        val ZERO = Complex(0.0, 0.0)
        val ONE = Complex(1.0, 0.0)
        /**
         * Creates a complex number with the given values
         *
         * @param real      the real part
         * @param imaginary the imaginary part
         * @return the complex number
         */
        fun of(real: Double, imaginary: Double): Complex {
            return Complex(real, imaginary)
        }

        /**
         * Creates a pure real complex number
         *
         * @param real the real part
         * @return a pure real complex number
         */
        fun of(real: Double): Complex {
            return Complex(real, 0.0)
        }

        /**
         * Creates an exponential complex number where <br></br>
         * real part = cos(x)
         * imaginary part = sin(x)
         *
         * @param x the angle in radians
         * @return the exponential complex number
         */
        fun expI(x: Double): Complex {
            return of(cos(x), sin(x))
        }
    }

}