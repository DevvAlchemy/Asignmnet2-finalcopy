/**
 * Challenge A: Creating  a function that encrypts a string by shifting each letter by the specified key value.
 *  making sure that non-letter charaters remain unchanged. This will help me avoid errors or wrong outputs
 *  - make  string to encrypt
 *  -key how many position to shift each letter
 *  - return encrypted string
 */

fun main () {
    testFunctions()
}

fun encryptString(text: String, key: Int): String {
    // Using a StringBuilder for efficient string building
    val result = StringBuilder()

    for (char in text) {
        //determining the base value Upper case or lower
        if (char.isLetter()) {
            //converting to 0-25 range, applying shift and converting back to char
            val base = if (char.isUpperCase()) 'A'.code else 'a'.code

            // Convert to 0-25 range, apply shift, wrap around with modulo, convert back to char
            val shifted = ((char.code - base + key) % 26 + base).toChar()
            result.append(shifted)
        } else {
            // Keep non-letter characters unchanged
            result.append(char)
        }
    }

    return result.toString()
}

/**
 * Challenge B: checking if two numbers are anagrams of each other
 * compare word1 to word2
 * return True if the words are anagrams, false if otherwise
 */

fun areAnagrams(word1: String, word2: String): Boolean {
    //checking lengths. different lengths are false anagrams
    if (word1.length != word2.length) return false

    // counting frequency of each character and compare
    return word1.lowercase().groupingBy { it }.eachCount() ==
            word2.lowercase().groupingBy { it }.eachCount()
}

/**
 * Challenge C: checking if the second string is a substring of first without contain method
 *  * will make the mainString search  in while  the subString for
 *  * return  True if  substring is founs within MainString or else return false
 */
fun isSubstring(mainString: String, subString: String): Boolean {
    // Edge cases
    if (subString.isEmpty()) return true
    if (mainString.isEmpty() || subString.length > mainString.length) return false

    // Sliding window approach
    for (i in 0..mainString.length - subString.length) {
        var match = true

        // Checking  if characters match at current position
        for (j in subString.indices) {
            if (mainString[i + j] != subString[j]) {
                match = false
                break
            }
        }

        if (match) return true
    }

    return false
}

/**
 * Challenge D: finding the longest word in a String
 *  * I'll set up a string input to analyze
 *  * return the longest word found
 */
fun findLongestWord(text: String): String {
    // Handling empty string
    if (text.isEmpty()) return ""

    // Splitting the string into words and find the longest one
    return text.split(" ")
        .filter { it.isNotEmpty() }  // Remove empty entries
        .maxByOrNull { it.length }   // Find the longest word
        ?: ""                        // Return empty string if no words found
}

// Testing
fun testFunctions() {
    // A
    val message = "Hello Doug, how are you doing today"
    val key = 3
    val encrypted = encryptString(message, key)
    println("Original: $message")
    println("Encrypted (shift $key): $encrypted")

    // B
    val word1 = "Dusty"
    val word2 = "Study"
    val areTheyAnagrams = areAnagrams(word1, word2)
    println("\nAre '$word1' and '$word2' anagrams? $areTheyAnagrams")

    // C
    val mainStr = "Programming is fun but requires logical thinking"
    val subStr = "gram"
    val isSubstr = isSubstring(mainStr, subStr)
    println("\nIs '$subStr' a substring of '$mainStr'? $isSubstr")

    // D
    val sentence = "Here is a random sentence i'd like to use for this assignment"
    val longest = findLongestWord(sentence)
    println("\nLongest word in '$sentence' is: '$longest'")
}