package com.religada.calculator.data

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.leftAssociative
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.skip
import com.github.h0tk3y.betterParse.combinators.times
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parser
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.parser.Parser
import kotlin.math.pow

class MathGrammar : Grammar<Double>() {
    val num by regexToken("\\d+(\\.\\d+)?")
    val lpar by literalToken("(")
    val rpar by literalToken(")")
    val mul by literalToken("x")
    val pow by literalToken("^")
    val div by literalToken("÷")
    val minus by literalToken("-")
    val plus by literalToken("+")
    val percent by literalToken("%")
    val ws by regexToken("\\s+", ignore = true)

    val number by num use { text.toDouble() }

    val negNumber by (-minus * number) map { -it }

    val term: Parser<Double> by negNumber or number or (skip(lpar) and parser(::rootParser) and skip(rpar))

    val powChain by leftAssociative(term, pow) { a, _, b -> a.pow(b) }

    val divMulChain by leftAssociative(powChain, div or mul use { type }) { a, op, b ->
        when (op) {
            div -> a / b
            mul -> a * b
            else -> error("Unexpected operator")
        }
    }

    val subSumChain by leftAssociative(divMulChain, plus or minus use { type }) { a, op, b ->
        when (op) {
            plus -> a + b
            minus -> a - b
            else -> error("Unexpected operator")
        }
    }

    val percentChain by leftAssociative(subSumChain, percent) { a, _, _ ->
        a / 100
    }

    override val rootParser: Parser<Double> by percentChain
}

class MathGrammar2 : Grammar<Double>() {
    val num by regexToken("-?\\d+(\\.\\d+)?")
    val lpar by literalToken("(")
    val rpar by literalToken(")")
    val mul by literalToken("x")
    val pow by literalToken("^")
    val div by literalToken("÷")
    val minus by literalToken("-")
    val plus by literalToken("+")
    val percent by literalToken("%")
    val ws by regexToken("\\s+", ignore = true)

    val number by num use { text.toDouble() }

    val term: Parser<Double> by number or
            (skip(minus) and parser(::term) map { -it }) or
            (skip(lpar) and parser(::rootParser) and skip(rpar))

    val powChain by leftAssociative(term, pow) { a, _, b -> a.pow(b) }

    val divMulChain by leftAssociative(powChain, div or mul use { type }) { a, op, b ->
        when (op) {
            div -> a / b
            mul -> a * b
            else -> error("Unexpected operator")
        }
    }

    val subSumChain by leftAssociative(divMulChain, plus or minus use { type }) { a, op, b ->
        when (op) {
            plus -> a + b
            minus -> a - b
            else -> error("Unexpected operator")
        }
    }

    val percentChain by leftAssociative(subSumChain, percent) { a, _, _ ->
        a / 100
    }

    override val rootParser: Parser<Double> by percentChain
}
