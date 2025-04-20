package com.uc3m.android.helloworld.data

class BioQuestions {
    fun generateCellFunctionsQuestions(cellFunctionsId: String): List<Question> {
        return listOf(
            // TRUE_FALSE - EASY
            Question(
                questionText = "The cell membrane controls the entry and exit of substances.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = true,
                difficulty = Difficult.EASY,
                unitId = cellFunctionsId,
                id = "q11bio"
            ),
            Question(
                questionText = "Mitochondria are involved in photosynthesis.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = false,
                difficulty = Difficult.EASY,
                id = "q12bio",
                unitId = cellFunctionsId

            ),

            // MULTIPLE_CHOICE - EASY
            Question(
                questionText = "Which organelle is responsible for producing energy for the cell?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Nucleus", "Mitochondrion", "Ribosome", "Endoplasmic Reticulum"),
                correctAnswer = "Mitochondrion",
                difficulty = Difficult.EASY,
                id = "q13bio",
                unitId = cellFunctionsId
            ),
            Question(
                questionText = "Which of these structures is found in plant cells but not animal cells?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Cell membrane", "Nucleus", "Chloroplast", "Mitochondrion"),
                correctAnswer = "Chloroplast",
                difficulty = Difficult.EASY,
                id = "q14bio",
                unitId = cellFunctionsId
            ),

            // SHORT_ANSWER - EASY
            Question(
                questionText = "What is the powerhouse of the cell?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Mitochondria",
                difficulty = Difficult.EASY,
                id = "q15bio",
                unitId = cellFunctionsId
            ),
            Question(
                questionText = "Which organelle contains the genetic material of the cell?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Nucleus",
                difficulty = Difficult.EASY,
                id = "q16bio",
                unitId = cellFunctionsId
            ),

            // MATCHING - MEDIUM
            Question(
                questionText = "Match the organelle with its function.",
                type = QuestionType.MATCHING,
                leftColumnOptions = listOf("Ribosome", "Lysosome", "Nucleus", "Mitochondrion"),
                rightColumnOptions = listOf("Protein synthesis", "Digestion of waste", "Genetic control center", "Energy production"),
                correctAnswer = mapOf(
                    "Ribosome" to "Protein synthesis",
                    "Lysosome" to "Digestion of waste",
                    "Nucleus" to "Genetic control center",
                    "Mitochondrion" to "Energy production"
                ),
                difficulty = Difficult.MEDIUM,
                id = "q17bio",
                unitId = cellFunctionsId
            ),

            // MULTIPLE_CHOICE - MEDIUM
            Question(
                questionText = "Which of the following is NOT a function of the cell membrane?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Protection", "Regulation of substances", "Protein synthesis", "Communication with other cells"),
                correctAnswer = "Protein synthesis",
                difficulty = Difficult.MEDIUM,
                id = "q18bio",
                unitId = cellFunctionsId
            ),

            // TRUE_FALSE - MEDIUM
            Question(
                questionText = "Ribosomes are responsible for synthesizing proteins.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = true,
                difficulty = Difficult.MEDIUM,
                id = "q19bio",
                unitId = cellFunctionsId
            ),

            // SHORT_ANSWER - MEDIUM
            Question(
                questionText = "What is the fluid-filled space inside the cell where many metabolic reactions occur?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Cytoplasm",
                difficulty = Difficult.MEDIUM,
                id = "q110bio",
                unitId = cellFunctionsId
            ),

            // MATCHING - HARD
            Question(
                questionText = "Match the structure with its location in the cell.",
                type = QuestionType.MATCHING,
                leftColumnOptions = listOf("Rough Endoplasmic Reticulum", "Smooth Endoplasmic Reticulum", "Golgi Apparatus", "Vesicles"),
                rightColumnOptions = listOf("Near the nucleus", "Near the plasma membrane", "Cytoplasm", "Near the nucleus"),
                correctAnswer = mapOf(
                    "Rough Endoplasmic Reticulum" to "Near the nucleus",
                    "Smooth Endoplasmic Reticulum" to "Cytoplasm",
                    "Golgi Apparatus" to "Near the plasma membrane",
                    "Vesicles" to "Near the nucleus"
                ),
                difficulty = Difficult.HARD,
                id = "q111bio",
                unitId = cellFunctionsId
            ),

            // MULTIPLE_CHOICE - HARD
            Question(
                questionText = "What type of molecule makes up the majority of the cell membrane?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Proteins", "Lipids", "Carbohydrates", "Nucleic acids"),
                correctAnswer = "Lipids",
                difficulty = Difficult.HARD,
                id = "q112bio",
                unitId = cellFunctionsId
            ),

            // TRUE_FALSE - HARD
            Question(
                questionText = "The nucleus is involved in cellular respiration.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = false,
                difficulty = Difficult.HARD,
                id = "q113bio",
                unitId = cellFunctionsId
            ),

            // SHORT_ANSWER - HARD
            Question(
                questionText = "Which organelle is responsible for breaking down fatty acids in the cell?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Peroxisome",
                difficulty = Difficult.HARD,
                id = "q114bio",
                unitId = cellFunctionsId
            ),

            // SHORT_ANSWER - HARD
            Question(
                questionText = "What is the name of the protein synthesis process that occurs in the ribosomes?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Translation",
                difficulty = Difficult.HARD,
                id = "q115bio",
                unitId = cellFunctionsId
            )
        )
    }
    fun generateHumanBodySystemsQuestions(humanBodySystemsId: String): List<Question> {
        return listOf(
            // TRUE_FALSE - EASY
            Question(
                questionText = "The heart pumps blood through the circulatory system.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = true,
                difficulty = Difficult.EASY,
                unitId = humanBodySystemsId,
                id = "q1bio2"
            ),
            Question(
                questionText = "The liver is responsible for producing insulin.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = false,
                difficulty = Difficult.EASY,
                unitId = humanBodySystemsId,
                id = "q2bio2"
            ),

            // MULTIPLE_CHOICE - EASY
            Question(
                questionText = "Which organ is responsible for filtering blood in the human body?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Heart", "Kidney", "Lungs", "Stomach"),
                correctAnswer = "Kidney",
                difficulty = Difficult.EASY,
                unitId = humanBodySystemsId,
                id = "q3bio2"
            ),
            Question(
                questionText = "Which system is responsible for fighting infections in the body?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Nervous system", "Endocrine system", "Immune system", "Digestive system"),
                correctAnswer = "Immune system",
                difficulty = Difficult.EASY,
                unitId = humanBodySystemsId,
                id = "q4bio2"
            ),

            // SHORT_ANSWER - EASY
            Question(
                questionText = "What is the main function of the respiratory system?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Breathing",
                difficulty = Difficult.EASY,
                unitId = humanBodySystemsId,
                id = "q5bio2"
            ),
            Question(
                questionText = "What organ is involved in the digestion of food?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Stomach",
                difficulty = Difficult.EASY,
                unitId = humanBodySystemsId,
                id = "q6bio2"
            ),

            // MATCHING - MEDIUM
            Question(
                questionText = "Match the system with its function.",
                type = QuestionType.MATCHING,
                leftColumnOptions = listOf("Circulatory system", "Digestive system", "Nervous system", "Endocrine system"),
                rightColumnOptions = listOf("Transports nutrients and oxygen", "Processes food and absorbs nutrients", "Coordinates body functions", "Secretes hormones to regulate body functions"),
                correctAnswer = mapOf(
                    "Circulatory system" to "Transports nutrients and oxygen",
                    "Digestive system" to "Processes food and absorbs nutrients",
                    "Nervous system" to "Coordinates body functions",
                    "Endocrine system" to "Secretes hormones to regulate body functions"
                ),
                difficulty = Difficult.MEDIUM,
                unitId = humanBodySystemsId,
                id = "q7bio2"
            ),

            // MULTIPLE_CHOICE - MEDIUM
            Question(
                questionText = "Which part of the brain controls breathing?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Cerebellum", "Medulla oblongata", "Cerebrum", "Hypothalamus"),
                correctAnswer = "Medulla oblongata",
                difficulty = Difficult.MEDIUM,
                unitId = humanBodySystemsId,
                id = "q8bio2"
            ),

            // TRUE_FALSE - MEDIUM
            Question(
                questionText = "The digestive system absorbs oxygen from the air.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = false,
                difficulty = Difficult.MEDIUM,
                unitId = humanBodySystemsId,
                id = "q9bio2"
            ),

            // SHORT_ANSWER - MEDIUM
            Question(
                questionText = "What is the name of the tube that connects the throat to the stomach?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Esophagus",
                difficulty = Difficult.MEDIUM,
                unitId = humanBodySystemsId,
                id = "q10bio2"
            ),

            // MATCHING - HARD
            Question(
                questionText = "Match the organ with its system.",
                type = QuestionType.MATCHING,
                leftColumnOptions = listOf("Heart", "Lungs", "Kidneys", "Pancreas"),
                rightColumnOptions = listOf("Circulatory system", "Respiratory system", "Excretory system", "Endocrine system"),
                correctAnswer = mapOf(
                    "Heart" to "Circulatory system",
                    "Lungs" to "Respiratory system",
                    "Kidneys" to "Excretory system",
                    "Pancreas" to "Endocrine system"
                ),
                difficulty = Difficult.HARD,
                unitId = humanBodySystemsId,
                id = "q11bio2"
            ),

            // MULTIPLE_CHOICE - HARD
            Question(
                questionText = "Which of these is NOT a part of the human nervous system?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Spinal cord", "Brain", "Heart", "Nerves"),
                correctAnswer = "Heart",
                difficulty = Difficult.HARD,
                unitId = humanBodySystemsId,
                id = "q12bio2"
            ),

            // TRUE_FALSE - HARD
            Question(
                questionText = "The lymphatic system is a part of the circulatory system.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = true,
                difficulty = Difficult.HARD,
                unitId = humanBodySystemsId,
                id = "q13bio2"
            ),

            // SHORT_ANSWER - HARD
            Question(
                questionText = "What is the name of the process where the body breaks down food into smaller components?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Digestion",
                difficulty = Difficult.HARD,
                unitId = humanBodySystemsId,
                id = "q14bio2"
            ),

            // SHORT_ANSWER - HARD
            Question(
                questionText = "What part of the brain is responsible for regulating emotions?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Limbic system",
                difficulty = Difficult.HARD,
                unitId = humanBodySystemsId,
                id = "q15bio2"
            )
        )
    }

    fun generateGeneticsEvolutionQuestions(geneticsEvolutionId: String): List<Question> {
        return listOf(
            // TRUE_FALSE - EASY
            Question(
                questionText = "Genetics is the study of heredity and variation in living organisms.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = true,
                difficulty = Difficult.EASY,
                unitId = geneticsEvolutionId,
                id = "q1bio3"
            ),
            Question(
                questionText = "Mendel's laws only apply to humans.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = false,
                difficulty = Difficult.EASY,
                unitId = geneticsEvolutionId,
                id = "q2bio3"
            ),

            // MULTIPLE_CHOICE - EASY
            Question(
                questionText = "What is the basic unit of heredity?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Chromosome", "Gene", "DNA", "Protein"),
                correctAnswer = "Gene",
                difficulty = Difficult.EASY,
                unitId = geneticsEvolutionId,
                id = "q3bio3"
            ),
            Question(
                questionText = "Which of the following is an example of a dominant trait?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Blue eyes", "Brown eyes", "Short hair", "Straight hair"),
                correctAnswer = "Brown eyes",
                difficulty = Difficult.EASY,
                unitId = geneticsEvolutionId,
                id = "q4bio3"
            ),

            // SHORT_ANSWER - EASY
            Question(
                questionText = "What does the term 'genotype' refer to?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Genetic makeup",
                difficulty = Difficult.EASY,
                unitId = geneticsEvolutionId,
                id = "q5bio3"
            ),
            Question(
                questionText = "What does the term 'phenotype' refer to?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Physical traits",
                difficulty = Difficult.EASY,
                unitId = geneticsEvolutionId,
                id = "q6bio3"
            ),

            // MATCHING - MEDIUM
            Question(
                questionText = "Match the terms with their correct description.",
                type = QuestionType.MATCHING,
                leftColumnOptions = listOf("Dominant allele", "Recessive allele", "Homozygous", "Heterozygous"),
                rightColumnOptions = listOf("Two identical alleles", "One dominant and one recessive allele", "Only expressed when two copies are present", "Expressed even when only one copy is present"),
                correctAnswer = mapOf(
                    "Dominant allele" to "Expressed even when only one copy is present",
                    "Recessive allele" to "Only expressed when two copies are present",
                    "Homozygous" to "Two identical alleles",
                    "Heterozygous" to "One dominant and one recessive allele"
                ),
                difficulty = Difficult.MEDIUM,
                unitId = geneticsEvolutionId,
                id = "q7bio3"
            ),

            // MULTIPLE_CHOICE - MEDIUM
            Question(
                questionText = "Who is known as the father of modern genetics?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Gregor Mendel", "Charles Darwin", "James Watson", "Francis Crick"),
                correctAnswer = "Gregor Mendel",
                difficulty = Difficult.MEDIUM,
                unitId = geneticsEvolutionId,
                id = "q8bio3"
            ),

            // TRUE_FALSE - MEDIUM
            Question(
                questionText = "In genetic inheritance, recessive traits are always expressed in the offspring.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = false,
                difficulty = Difficult.MEDIUM,
                unitId = geneticsEvolutionId,
                id = "q9bio3"
            ),

            // SHORT_ANSWER - MEDIUM
            Question(
                questionText = "What is the process by which natural selection leads to evolution?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Survival of the fittest",
                difficulty = Difficult.MEDIUM,
                unitId = geneticsEvolutionId,
                id = "q10bio3"
            ),

            // MATCHING - HARD
            Question(
                questionText = "Match the evolutionary mechanism with its definition.",
                type = QuestionType.MATCHING,
                leftColumnOptions = listOf("Natural selection", "Gene flow", "Genetic drift", "Mutation"),
                rightColumnOptions = listOf("Changes in allele frequency due to chance", "Movement of genes between populations", "Heritable changes that increase an organism's fitness", "Random changes in genetic material"),
                correctAnswer = mapOf(
                    "Natural selection" to "Heritable changes that increase an organism's fitness",
                    "Gene flow" to "Movement of genes between populations",
                    "Genetic drift" to "Changes in allele frequency due to chance",
                    "Mutation" to "Random changes in genetic material"
                ),
                difficulty = Difficult.HARD,
                unitId = geneticsEvolutionId,
                id = "q11bio3"
            ),

            // MULTIPLE_CHOICE - HARD
            Question(
                questionText = "Which of the following is NOT a component of the theory of evolution?",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("Variation", "Inheritance", "Adaptation", "Lamarckian inheritance"),
                correctAnswer = "Lamarckian inheritance",
                difficulty = Difficult.HARD,
                unitId = geneticsEvolutionId,
                id = "q12bio3"
            ),

            // TRUE_FALSE - HARD
            Question(
                questionText = "Humans evolved directly from chimpanzees.",
                type = QuestionType.TRUE_FALSE,
                correctAnswer = false,
                difficulty = Difficult.HARD,
                unitId = geneticsEvolutionId,
                id = "q13bio3"
            ),

            // SHORT_ANSWER - HARD
            Question(
                questionText = "What is the primary cause of genetic variation in a population?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Mutation",
                difficulty = Difficult.HARD,
                unitId = geneticsEvolutionId,
                id = "q14bio3"
            ),

            // SHORT_ANSWER - HARD
            Question(
                questionText = "What is the process by which new species arise due to reproductive isolation?",
                type = QuestionType.SHORT_ANSWER,
                correctAnswer = "Speciation",
                difficulty = Difficult.HARD,
                unitId = geneticsEvolutionId,
                id = "q15bio3"
            )
        )
    }


}