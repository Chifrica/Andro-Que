/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ble.ble.androque
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ble.ble.androque.databinding.FragmentGameBinding
import kotlin.math.min

class GameFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>)

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
            Question(text = "What is Android Jetpack?",
                    answers = listOf("all of these", "tools", "documentation", "libraries")),
            Question(text = "Which of the following is true about Room?",
                    answers = listOf("Validates the SQL queries in compile time", "Part of Android Foundation Components", "Validates the SQL queries in run time only", "None")),
            Question(text = "Which of the following will you use to save complex data like dates in your database?",
                    answers = listOf("TypeConverters", "Migrations", "Repositories", "Transactions")),
            Question(text = "Which of the following is the most appropriate choice for handling the data related operation from multiple data sources?",
                    answers = listOf("Repository", "ViewModel", "LiveDate", "Activity")),
            Question(text = "LiveData can be observed in which of the following lifecycle states?",
                    answers = listOf("STARTED, RESUMED", "STARTED, RESUMED, PAUSED, STOPPED, DESTROYED", "PAUSED, STOPPED, DESTROYED", "RESUMED, PAUSED")),
            Question(text = "Which lifecycle method is the most appropriate to begin observing LiveData? ",
                    answers = listOf("onCreate()", "onPause()", "onResumed()", "onStart()")),
            Question(text = "Base class for Layout?",
                    answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
            Question(text = "Layout for complex Screens?",
                    answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
            Question(text = "Pushing structured data into a Layout?",
                    answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),

//        Retrofit Questions

            Question(text="What is REST",
                    answers = listOf("A set of architectural principles of communication between networked systems", "An XML based protocol that sits on top of HTTP and enforces structure through WSDL files",
                                        "An application level protocol that allows for a client and server communication",
                                        "A networking level communication protocol that allows routers and servers to communicate")),
            Question(text="What does a 200 Response status code communicate?",
                answers = listOf("The request was successful", "A client error occurred", "The request was redirected", "A server error occurred")),
            Question(text="What is the purpose of a reusable service builder class in Retrofit?",
                answers = listOf("It creates implementations of the service interfaces we define.", "it automatically builds out the structure of our HTTP requests.",
                                    "It provides dependency injection for our service classes.", "It automatically builds out interface classes by scanning our Web Service.")),
            Question(text="What are the two response handler methods Retrofit provides for managing an HTTP Response?",
                answers = listOf("onResponse and Failure", "onSuccess and Error", "onSuccess and Failure", "onComplete and Error")),
            Question(text="What are the two types of request parameters in Retrofit?",
                answers = listOf("Path and Query Parameters", "Request and Response Parameters", "Header and Body Parameters", "Query and Service Parameters")),
            Question(text="What is the difference between Path and Query parameters?",
                answers = listOf("Path parameters modify URL segments, while query parameters modify the query string", "Path parameters change the base URL domain, while query change the URL segments",
                                    "Path parameters are static, while query parameters are dynamic.", "Path parameters modify the query string, while Query parameters modify the base URL")),
            Question(text="How can we dynamically add an HTTP Header value to a request?",
                answers = listOf("Through the use of the @Header parameter annotation", "By using the @Http configuration annotation", "Through the use of the @Headers method annotation", "By using a setter on our Request object")),
            Question(text="Which Retrofit method is used to make a synchronous HTTP Request?",
                answers = listOf("Execute", "Enqueue", "Sync", "Queue")),
            Question(text="What is the relationship between Retrofit and OkHTTP?",
                answers = listOf("OkHTTP manages low level networking details for Retrofit", " OkHTTP is built on top of Retrofit", "OkHTTP is a competing client library separate from Retrofit", "OkHTTP is a legacy HTTP client that is no longer widely used")),
            Question(text="Why is Retrofit a particularly good match for working with RESTful Web Services?",
                answers = listOf("Retrofit allows us to map Web Service operations to Java interface methods", "Retrofit is designed around the SOAP protocol, which is the modern standard for REST.", "Retrofit uses web sockets to improve request performance",
                                    "Retrofit can receive Response data in formats other libraries do not support")),

            Question(text = "Inflate layout in fragments?",
                    answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")),
            Question(text = "Build system for Android?",
                    answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
            Question(text = "What is the annotation used for Dao method to modify the existing records in Room DataBase?",
                    answers = listOf("@Update", "@Amend", "@Change", "@Modify")),
            Question(text = "Given the books table below, which of the following Dao methods will give me the list of all books written by Dan Brown?",
                    answers = listOf("SELECT*FROM books WHERE author = 'Dan Brown'", "SELECT*FROM books WHERE author IS 'Dan Brown'", "SELECT*FROM books WHERE author 'Dan Brown'", "SELECT*FROM books WHERE author book = 'Dan Brown'")),
            Question(text = "Which of the following belong to Android Architecture Components?",
                    answers = listOf("Room, Paging, ViewModel, LiveData, Navigation", " Animation, Layout, Palette, Fragment", "Download Manager, Permission, Notification, Sharing", "AppCompact, MultiDex, Test, Android KTX")),
            Question(text = "Which of the followings can be a parameter of the Insert method in Dao",
                    answers = listOf("Classes annotated with Entity", " LiveData", "Custom SQL statement", "Variables of basic datatype")),
            Question(text = "Which of the following annotations is used for reading data from room database?",
                    answers = listOf("@Query", "@Read", "@Extract", "@Select")),
            Question(text = "Which of the following are the major components required for implementing Room Database?",
                    answers = listOf("DAO, Entity, Database", "Repository, Entity, SQLite Helper", "DAO, Model, ViewModel, Database", "Repository, Entity, ViewModel, Database")),
            Question(text = "Which of the following will delete a record from the database table?",
                    answers = listOf("@Dao \n\ninterface MyDao { \n\n \t\t @Delete \n\n \t\t fun deleteUsers(users: User) \n\n }", "@Dao \n\ninterface MyDao { \n\n \t\t @Remove \n\n \t\t fun deleteUsers(vararg users: User) \n\n }", "@Dao \n\ninterface MyDao { \n\n \t\t @Delete \n\n \t\t fun deleteUsers() \n\n }", "None")),
            Question(text = "Android vector format?",
                    answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
            Question(text = "Android Navigation Component?",
                    answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
            Question(text = "Registers app with launcher?",
                    answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
            Question(text = "These are examples of CRUD operations except",
                    answers = listOf("GIT", "CREATE", "DELETE", "UPDATE")),
            Question(text = "Mark a layout for Data Binding?",
                    answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")),
            Question(text = "Span in grid layout manager can be describe as ....?",
                        answers = listOf("columns for vertical orientation", "rows for vertical orientation", "columns for horizontal orientation", "rows for horizontal orientaion")),

        //WorkManager
            Question(text = "Which os the following is true about WorkManager",
                    answers = listOf("WorkManger is part of Android Jetpack and requires a Gradle dependency to use.",
                    "WorkManger does not necessarily guarantee that a task will be executed.", "WorkRequest is the name of the class responsible for scheduling and renning tasks.",
                    "Tasks are typically chained, but not run in parallel.")),
            Question(text = "Which of the following would not require WorkManager?",
                answers = listOf("Performing a GET request to a web service", "Long running task such as downloading large aounts of data", "Scheduling a task to repeat after a set interval",
                    "Doing something while the app is in the background.")),
            Question(text = "A Worker represents a task to be schedule by a WorkManager while a WorkRequest contains the actual code to be excuted.",
                answers = listOf("True", "False")),
            Question(text = "Making execution dependent on device state such as storage space and battery life are examples of ?",
                answers = listOf("constraints", "chaining", "best practices", "canceling tasks")),
            Question(text = "In a chain, the output os the last WorkRequest becomes the input for the next WorkRequest.",
                answers = listOf("True", "False")),

    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = min((questions.size + 1) / 2, 3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                }
            }
        }
        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}
