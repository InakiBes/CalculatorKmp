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
    private val num by regexToken(REGEX_NUMBER)
    private val lpar by literalToken(LEFT_PARENTHESIS)
    private val rpar by literalToken(RIGHT_PARENTHESIS)
    private val mul by literalToken(MULTIPLICATION)
    private val pow by literalToken(POWER)
    private val div by literalToken(DIVISION)
    private val minus by literalToken(MINUS)
    private val plus by literalToken(PLUS)
    private val percent by literalToken(DIVISION)
    private val ws by regexToken(WHITESPACE, ignore = true)

    private val number by num use { text.toDouble() }

    private val negNumber by (-minus * number) map { -it }

    private val term: Parser<Double> by negNumber or number or (skip(lpar) and parser(::rootParser) and skip(rpar))

    private val powChain by leftAssociative(term, pow) { a, _, b -> a.pow(b) }

    private val divMulChain by leftAssociative(powChain, div or mul use { type }) { a, op, b ->
        when (op) {
            div -> a / b
            mul -> a * b
            else -> error(UNEXPECTED_ERROR)
        }
    }

    private val subSumChain by leftAssociative(divMulChain, plus or minus use { type }) { a, op, b ->
        when (op) {
            plus -> a + b
            minus -> a - b
            else -> error(UNEXPECTED_ERROR)
        }
    }

    private val percentChain by leftAssociative(subSumChain, percent) { a, _, _ ->
        a / 100
    }

    override val rootParser: Parser<Double> by percentChain
}
