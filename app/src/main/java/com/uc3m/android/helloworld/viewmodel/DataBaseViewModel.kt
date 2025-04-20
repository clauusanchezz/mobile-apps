package com.uc3m.android.helloworld.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uc3m.android.helloworld.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.delay

class DataBaseViewModel : ViewModel() {
    private val repo = FirestoreRepository()
    private val mathQuestions = MathQuestions()
    private val scienceQuestions = ScienceQuestions()
    private val geoQuestions = GeographyQuestions()
    private val historyQuestions = HistoryQuestions()
    private val bioQuestions = BioQuestions()
    private val spanishQuestions = SpanishQuestions()
    private val englishQuestions = EnglishQuestions()

    private val _databaseInitialized = MutableLiveData<Boolean>()
    val isInitialized: LiveData<Boolean> get() = _databaseInitialized

    private val _subjects = MutableLiveData<List<Subject>>()
    val subjects: LiveData<List<Subject>> get() = _subjects

    private val _units = MutableLiveData<List<SubjectUnit>>()
    val units: LiveData<List<SubjectUnit>> get() = _units

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    private var subjectsListenerRegistration: ListenerRegistration? = null
    private var unitsListenerRegistration: ListenerRegistration? = null
    private var questionsListenerRegistration: ListenerRegistration? = null

    init {
        _databaseInitialized.value = false

        // Observa los cambios en el repositorio y actualiza los LiveData del ViewModel
        repo.subjectsLiveData.observeForever { _subjects.postValue(it) }
        repo.unitsLiveData.observeForever { _units.postValue(it) }
        repo.questionsLiveData.observeForever { _questions.postValue(it) }
    }

    fun initializeDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(2000)
                if (repo.isDatabaseInitializedGlobal()) {
                    Log.d("DB_INIT", "Database already initialized. Skipping.")
                    _databaseInitialized.postValue(true)
                    return@launch
                }

                // Subjects -----------------------------------------------------------
                val mathSubject = Subject(name = "Maths")
                val mathId = repo.addSubject(mathSubject, "maths")

                val scienceSubject = Subject(name = "Science")
                val scienceId = repo.addSubject(scienceSubject, "science")

                val geoSubject = Subject(name = "Geography")
                val geoId = repo.addSubject(geoSubject, "geo")

                val historySubject = Subject(name = "History")
                val historyId = repo.addSubject(historySubject, "history")

                val bioSubject = Subject(name = "Biology")
                val bioId = repo.addSubject(bioSubject, "bio")

                val spanishSubject = Subject(name = "Spanish")
                val spanishId = repo.addSubject(spanishSubject, "spanish")

                val englishSubject = Subject(name = "English")
                val englishId = repo.addSubject(englishSubject, "english")

                // Units & Questions ----------------------------------------------------------------
                // Maths ----------------------------------------------------------------------------
                // 1. Algebra
                val algebraUnit = SubjectUnit(name = "Algebra", subjectId = mathId, id = "u1maths")
                val algebraId = repo.addUnitToSubject(algebraUnit, mathId)

                val algebraQuestion = mathQuestions.generateAlgebraQuestions(algebraId)
                repo.addQuestionsToUnit(algebraQuestion, mathId, algebraId)

                // 2. Geometry
                val geometryUnit = SubjectUnit(name = "Geometry", subjectId = mathId, id = "u2maths")
                val geometryId = repo.addUnitToSubject(geometryUnit, mathId)

                val geometryQuestions = mathQuestions.generateGeometryQuestions(geometryId)
                repo.addQuestionsToUnit(geometryQuestions, mathId, geometryId)

                // 3. Functions
                val functionsUnit = SubjectUnit(name = "Functions", subjectId = mathId, id = "u3maths")
                val functionsId = repo.addUnitToSubject(functionsUnit, mathId)

                val functionsQuestions = mathQuestions.generateFunctionsQuestions(functionsId)
                repo.addQuestionsToUnit(functionsQuestions, mathId, functionsId)

                // 4. Statistic and Probability
                val statsUnit = SubjectUnit(name = "Statistics and Probability", subjectId = mathId, id = "u4maths")
                val statsId = repo.addUnitToSubject(statsUnit, mathId)

                val statsQuestions = mathQuestions.generateStatsQuestions(statsId)
                repo.addQuestionsToUnit(statsQuestions, mathId, statsId)

                // 5. Trigonometry
                val trigUnit = SubjectUnit(name = "Trigonometry", subjectId = mathId, id = "u5maths")
                val trigId = repo.addUnitToSubject(trigUnit, mathId)

                val trigQuestions = mathQuestions.generateTrigQuestions(trigId)
                repo.addQuestionsToUnit(trigQuestions, mathId, trigId)

                // 6. Equations
                val equationsUnit = SubjectUnit(name = "Equations and Inequalities", subjectId = mathId, id = "u6maths")
                val equationsId = repo.addUnitToSubject(equationsUnit, mathId)

                val equationsQuestions = mathQuestions.generateEquationsQuestions(equationsId)
                repo.addQuestionsToUnit(equationsQuestions, mathId, equationsId)


                // Science ----------------------------------------------------------------------------
                // 1. Kinematics & Dynamics
                val kinematicsDynamicsUnit = SubjectUnit(name = "Kinematics and Dynamics", subjectId = scienceId, id = "u1science")
                val kinematicsDynamicsId = repo.addUnitToSubject(kinematicsDynamicsUnit, scienceId)

                val kinematicsQuestions = scienceQuestions.generateKinematicsQuestions(kinematicsDynamicsId)
                repo.addQuestionsToUnit(kinematicsQuestions, scienceId, kinematicsDynamicsId)

                // 2. Energy
                val workEnergyPowerUnit = SubjectUnit(name = "Work, Energy, and Power", subjectId = scienceId, id = "u2science")
                val workEnergyPowerId = repo.addUnitToSubject(workEnergyPowerUnit, scienceId)

                val workQuestions = scienceQuestions.generateWorkEnergyPowerQuestions(workEnergyPowerId)
                repo.addQuestionsToUnit(workQuestions, scienceId, workEnergyPowerId)

                // 3. Forces
                val forcesNewtonLawsUnit = SubjectUnit(name = "Forces and Newton's Laws", subjectId = scienceId, id = "u3science")
                val forcesNewtonLawsId = repo.addUnitToSubject(forcesNewtonLawsUnit, scienceId)

                val forcesNewtonLawsQuestions = scienceQuestions.generateForcesNewtonsLawsQuestions(forcesNewtonLawsId)
                repo.addQuestionsToUnit(forcesNewtonLawsQuestions, scienceId, forcesNewtonLawsId)

                // 4. Periodic Table
                val atomicStructurePeriodicTableUnit = SubjectUnit(name = "Atomic Structure and the Periodic Table", subjectId = scienceId, id = "u4science")
                val atomicStructurePeriodicTableId = repo.addUnitToSubject(atomicStructurePeriodicTableUnit, scienceId)

                val periodicTableQuestions = scienceQuestions.generatePeriodicTableQuestions(atomicStructurePeriodicTableId)
                repo.addQuestionsToUnit(periodicTableQuestions, scienceId, atomicStructurePeriodicTableId)

                // 5. Chemical Bonding
                val chemicalBondingUnit = SubjectUnit(name = "Chemical Bonding", subjectId = scienceId, id = "u5science")
                val chemicalBondingId = repo.addUnitToSubject(chemicalBondingUnit, scienceId)

                val chemicalBondingQuestions = scienceQuestions.generateChemicalBondingQuestions(chemicalBondingId)
                repo.addQuestionsToUnit(chemicalBondingQuestions, scienceId, chemicalBondingId)


                // Geography-------------------------------------------------------------------------
                // 1. Physical Geography and Landforms
                val physicalGeographyUnit = SubjectUnit(name = "Physical Geography and Landforms", subjectId = geoId, id = "u1geo")
                val physicalGeographyId = repo.addUnitToSubject(physicalGeographyUnit, geoId)

                val physicalGeographyQuestions = geoQuestions.generatePhysicalGeographyQuestions(physicalGeographyId)
                repo.addQuestionsToUnit(physicalGeographyQuestions, geoId, physicalGeographyId)

                // 2. Climate and Weather Patterns
                val climateUnit = SubjectUnit(name = "Climate and Weather Patterns", subjectId = geoId, id = "u2geo")
                val climateId = repo.addUnitToSubject(climateUnit, geoId)

                val climateQuestions = geoQuestions.generateClimateAndWeatherQuestions(climateId)
                repo.addQuestionsToUnit(climateQuestions, geoId, climateId)

                // 3. Population and Economic Activities
                val populationEconomicsUnit = SubjectUnit(name = "Population and Economic Activities", subjectId = geoId, id = "u3geo")
                val populationEconomicsId = repo.addUnitToSubject(populationEconomicsUnit, geoId)

                val populationEconomicsQuestions = geoQuestions.generatePopulationAndEconomicsQuestions(populationEconomicsId)
                repo.addQuestionsToUnit(populationEconomicsQuestions, geoId, populationEconomicsId)


                // History-------------------------------------------------------------------------
                // 1. Ancient history
                val ancientHistoryUnit = SubjectUnit(name = "Prehistory, Ancient Greece and Rome", subjectId = historyId, id = "u1history")
                val ancientHistoryId = repo.addUnitToSubject(ancientHistoryUnit, historyId)

                val ancientHistoryQuestions = historyQuestions.generateAncientHistoryQuestions(ancientHistoryId)
                repo.addQuestionsToUnit(ancientHistoryQuestions, historyId, ancientHistoryId)

                // 2. Medieval Ages and Renaissance
                val medievalRenaissanceUnit = SubjectUnit(name = "Middle Ages and the Renaissance", subjectId = historyId, id = "u2history")
                val medievalRenaissanceId = repo.addUnitToSubject(medievalRenaissanceUnit, historyId)

                val medievalRenaissanceQuestions = historyQuestions.generateMedievalRenaissanceQuestions(medievalRenaissanceId)
                repo.addQuestionsToUnit(medievalRenaissanceQuestions, historyId, medievalRenaissanceId)

                // 3. Modern and Contemporary History
                val modernContemporaryUnit = SubjectUnit(name = "Modern and Contemporary History", subjectId = historyId, id = "u3history")
                val modernContemporaryId = repo.addUnitToSubject(modernContemporaryUnit, historyId)

                val modernContemporaryHistoryQuestions = historyQuestions.generateModernContemporaryHistoryQuestions(modernContemporaryId)
                repo.addQuestionsToUnit(modernContemporaryHistoryQuestions, historyId, modernContemporaryId)


                // Biology -------------------------------------------------------------------------
                // 1. The Cell and its Functions
                val cellFunctionsUnit = SubjectUnit(name = "The Cell and its Functions", subjectId = bioId, id = "u1bio")
                val cellFunctionsId = repo.addUnitToSubject(cellFunctionsUnit, bioId)

                val cellFunctionsQuestions = bioQuestions.generateCellFunctionsQuestions(cellFunctionsId)
                repo.addQuestionsToUnit(cellFunctionsQuestions, bioId, cellFunctionsId)

                // 2. Human Body Systems
                val humanBodySystemsUnit = SubjectUnit(name = "Human Body Systems", subjectId = bioId, id = "u2bio")
                val humanBodySystemsId = repo.addUnitToSubject(humanBodySystemsUnit, bioId)

                val humanBodySystemsQuestions = bioQuestions.generateHumanBodySystemsQuestions(humanBodySystemsId)
                repo.addQuestionsToUnit(humanBodySystemsQuestions, bioId, humanBodySystemsId)

                // 3. Genetics and Evolution
                val geneticsEvolutionUnit = SubjectUnit(name = "Genetics and Evolution", subjectId = bioId, id = "u3bio")
                val geneticsEvolutionId = repo.addUnitToSubject(geneticsEvolutionUnit, bioId)

                val geneticsEvolutionQuestions = bioQuestions.generateGeneticsEvolutionQuestions(geneticsEvolutionId)
                repo.addQuestionsToUnit(geneticsEvolutionQuestions, bioId, geneticsEvolutionId)


                // Spanish -------------------------------------------------------------------------
                // 1. Grammar and Sentence Structure
                val grammarUnit = SubjectUnit(name = "Grammar and Sentence Structure", subjectId = spanishId, id = "u1spanish")
                val grammarId = repo.addUnitToSubject(grammarUnit, spanishId)

                val grammarQuestions = spanishQuestions.generateGrammarQuestions(grammarId)
                repo.addQuestionsToUnit(grammarQuestions, spanishId, grammarId)

                // 2. Literary Genres and Figures of Speech
                val literaryGenresUnit = SubjectUnit(name = "Literary Genres and Figures of Speech", subjectId = spanishId, id = "u2spanish")
                val literaryGenresId = repo.addUnitToSubject(literaryGenresUnit, spanishId)

                val literaryGenresQuestions = spanishQuestions.generateLiteraryGenresQuestions(literaryGenresId)
                repo.addQuestionsToUnit(literaryGenresQuestions, spanishId, literaryGenresId)


                // English -------------------------------------------------------------------------
                // 1. Grammar and Verb Tenses
                val englishGrammarUnit = SubjectUnit(name = "Grammar and Verb Tenses", subjectId = englishId, id = "u1english")
                val englishGrammarId = repo.addUnitToSubject(englishGrammarUnit, englishId)

                val englishGrammarQuestions = englishQuestions.generateEnglishGrammarQuestions(englishGrammarId)
                repo.addQuestionsToUnit(englishGrammarQuestions, englishId, englishGrammarId)

                // 2. Vocabulary and Everyday Expressions
                val englishVocabUnit = SubjectUnit(name = "Vocabulary and Everyday Expressions", subjectId = englishId, id = "u2english")
                val englishVocabId = repo.addUnitToSubject(englishVocabUnit, englishId)

                val englishVocabQuestions = englishQuestions.generateEnglishVocabQuestions(englishVocabId)
                repo.addQuestionsToUnit(englishVocabQuestions, englishId, englishVocabId)




                // DATABASE INITIALIZED ------------------------------------------------------------
                repo.markDatabaseAsInitialized()
                Log.d("DB_INIT", "Database initialized successfully.")
                _databaseInitialized.postValue(true)

            } catch (e: Exception) {
                Log.e("DB_INIT", "Error initializing database: ${e.message}", e)
                _databaseInitialized.postValue(false)
            }
        }
    }
    // TODO usar bien estas funciones para que se pueda actualizar la base de datos

    fun startListeningToSubjects() {
        subjectsListenerRegistration = repo.listenToSubjects()
    }

    fun startListeningToUnitsForSubject(subjectId: String) {
        unitsListenerRegistration = repo.listenToUnitsForSubject(subjectId)
    }

    fun startListeningToQuestionsForUnit(subjectId: String, unitId: String) {
        questionsListenerRegistration = repo.listenToQuestionsForUnit(subjectId, unitId)
    }

    fun stopListening() {
        subjectsListenerRegistration?.remove()
        unitsListenerRegistration?.remove()
        questionsListenerRegistration?.remove()
    }


    // Load all subjects
    fun loadSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repo.getAllSubjects()
                _subjects.postValue(result)
            } catch (e: Exception) {
                Log.e("LOAD_SUBJECTS", "Error loading subjects", e)
            }
        }
    }

    // Función para añadir una nueva asignatura
    fun addNewSubject(subject: Subject, subjectId : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val subjectId = repo.addSubject(subject, subjectId)
                Log.d("DB_UPDATE", "Subject added with ID: $subjectId")
                loadSubjects() // Recargar asignaturas después de añadir una nueva
            } catch (e: Exception) {
                Log.e("DB_UPDATE", "Error adding subject: ${e.message}")
            }
        }
    }


    // Load all units from a subject
    fun loadUnitsForSubject(subjectId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repo.getAllUnitsFromSubject(subjectId)
                _units.postValue(result)
            } catch (e: Exception) {
                Log.e("LOAD_UNITS", "Error loading units", e)
            }
        }
    }

    // Obtain 10 questions of a unit
    fun loadQuestionsForUnit(subjectId: String, unitId: String) {
        viewModelScope.launch {
            val allQuestions = repo.getQuestionsForUnit(subjectId, unitId)

            // Choose 10 random questions from the corresponding unit
            val randomQuestions = if (allQuestions.size >= 5) {
            allQuestions.shuffled().take(5)
            } else {
            allQuestions.shuffled()
            }

            // Update the state with the corresponding questions
            _questions.value = randomQuestions
        }
    }


//TODO NO FUNCIONA
    fun resetInitializationFlag() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.resetInitializationFlag()
                Log.d("DB_INIT", "Initialization flag has been reset.")
            } catch (e: Exception) {
                Log.e("DB_INIT", "Error resetting initialization flag: ${e.message}")
            }
        }
    }





}
