package com.uc3m.android.helloworld.data

data class Question(
    val questionText: String ="", // The text of the question
    val type: QuestionType = QuestionType.TRUE_FALSE, // Type of question (enum: TrueFalse, MultipleChoice, MatchColumns, ShortAnswer)
    val options: List<String>? = null, // Options only applicable for "MultipleChoice"
    var correctAnswer: Any? = null, // Correct answer. It can be a Boolean, String, Map, etc.
    val difficulty: Difficult = Difficult.EASY, // "easy" or "hard"
    val unitId: String = "", // The ID of the unit to which this question belongs
    val id: String? = null, // The ID of the question in the database
    val leftColumnOptions: List<String>? = null, // Left column options, only for "MatchColumns"
    val rightColumnOptions: List<String>? = null // Right column options, only for "MatchColumns"
)

// Defining question types as an enum for better management
enum class QuestionType {
    TRUE_FALSE,
    MULTIPLE_CHOICE,
    MATCHING,
    SHORT_ANSWER
}

enum class Difficult {
    HARD,
    EASY,
    MEDIUM
}
