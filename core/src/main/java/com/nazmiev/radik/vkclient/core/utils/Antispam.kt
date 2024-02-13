package com.nazmiev.radik.vkclient.core.utils

import java.util.Random
import java.util.regex.Pattern

//TODO переписать этот legacy класс
class Antispam(private val messageText: String, private val useLatinReplace: Boolean = false) {
    private val dictionary = hashMapOf(
        "А" to "A",
        "В" to "B",
        "Е" to "E",
        "К" to "K",
        "М" to "M",
        "Н" to "H",
        "О" to "O",
        "Р" to "P",
        "С" to "C",
        "Т" to "T",
        "Х" to "X",
        "а" to "a",
        "е" to "e",
        "о" to "o",
        "р" to "p",
        "с" to "c",
        "у" to "y",
        "х" to "x"
    )
    private val reverseDictionary = HashMap<String, String>()
    private val elements: List<String> = listOf(
        "А", "В", "Е", "К", "М", "Н", "О", "Р", "С",
        "Т", "Х", "а", "е", "о", "р", "с", "у", "х"
    )
    private val reverseElements: List<String> = listOf(
        "A", "B", "E", "K", "M", "H", "O", "P", "C",
        "T", "X", "a", "e", "o", "p", "c", "y", "x"
    )

    fun start(): String {
        val text = messageText
        var result = ""
        if (text != "") {
            result = getVariants(text)
            if (useLatinReplace) {
                val rand = Random()
                val randomSymbolsCount = rand.nextInt(dictionary.size + 1)
                for (i in 0 until randomSymbolsCount) {
                    val randomSymbol = elements[rand.nextInt(dictionary.size)]
                    val charIndexes = findAllIndexesOfChar(randomSymbol)
                    if (charIndexes.isNotEmpty()) {
                        val charIndex = charIndexes[rand.nextInt(charIndexes.size)]
                        val charForReplace = dictionary[randomSymbol]
                        try {
                            result = replaceChar(result, charForReplace!![0], charIndex)
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }
                    }
                }
            }
        }
        return result
    }

    private fun findAllIndexesOfChar(randomSymbol: String): List<Int> {
        val charIndexes: MutableList<Int> = mutableListOf()
        var index = messageText.indexOf(randomSymbol)
        while (index >= 0) {
            charIndexes.add(index)
            println(index)
            index = messageText.indexOf(randomSymbol, index + 1)
        }
        return charIndexes
    }

    private fun replaceChar(str: String, ch: Char, index: Int): String {
        val myString = StringBuilder(str)
        myString.setCharAt(index, ch)
        return myString.toString()
    }

    private fun getVariants(text: String): String {
        var mText = text
        val rand = Random()
        val pattern = Pattern.compile("\\{(.|\\n)*?\\}")
        val matcher = pattern.matcher(mText)
        while (matcher.find()) {
            val findText = matcher.group(0)
            val variantsText = findText?.replace("{", "")?.replace("}", "")
            val variants =
                variantsText?.split("\\|".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
            val selectedVariant = variants?.get(rand.nextInt(variants.size))
            if (findText != null && selectedVariant != null) {
                mText = mText.replace(findText, selectedVariant)
            }
        }
        return mText
    }

    fun reverse(text: String): String {
        var result = text
        reverseDictionary["A"] = "А"
        reverseDictionary["B"] = "В"
        reverseDictionary["E"] = "Е"
        reverseDictionary["K"] = "К"
        reverseDictionary["M"] = "М"
        reverseDictionary["H"] = "Н"
        reverseDictionary["O"] = "О"
        reverseDictionary["P"] = "Р"
        reverseDictionary["C"] = "С"
        reverseDictionary["T"] = "Т"
        reverseDictionary["X"] = "Х"
        reverseDictionary["a"] = "а"
        reverseDictionary["e"] = "е"
        reverseDictionary["o"] = "о"
        reverseDictionary["p"] = "р"
        reverseDictionary["c"] = "с"
        reverseDictionary["y"] = "у"
        reverseDictionary["x"] = "х"
        for (i in reverseElements.indices) {
            result = text.replace(reverseElements[i], reverseDictionary[reverseElements[i]]!!)
        }
        return result
    }
}
