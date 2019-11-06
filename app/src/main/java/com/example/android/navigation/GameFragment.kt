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

package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>)


    private val questions: MutableList<Question> = mutableListOf(
            Question(text = "Cat แปลว่าอะไร?",
                    answers = listOf("แมว", "สุนัข", "แมวน้ำ", "กระต่าย")),
            Question(text = "Banana แปลว่าอะไร?",
                    answers = listOf("กล้วย", "ส้ม", "มะม่วง", "แอปเปิ้ล")),
            Question(text = "century แปลว่าอะไร?",
                    answers = listOf("ศตวรรษ", "ทศวรรษ", "สหัสวรรษ", "รอบ 3 ปี")),
            Question(text = "mountain แปลว่าอะไร?",
                    answers = listOf("ภูเขา", "ทะเล", "น้ำตก", "ที่ราบ")),
            Question(text = "product แปลว่าอะไร?",
                    answers = listOf("ผลิตภัณฑ์", "ผลิต", "กระบวนการ", "ปัญหา")),
            Question(text = "quiet แปลว่าอะไร?",
                    answers = listOf("เงียบ", "เร็ว", "ค่อนข้าง", "คำถาม")),
            Question(text = "require แปลว่าอะไร?",
                    answers = listOf("ต้องการ", "เป็นตัวแทน", "ทำซ้ำ", "ผลลัพธ์")),
            Question(text = "several แปลว่าอะไร?",
                    answers = listOf("หลากหลาย", "คล้ายกัน", "แยกกัน", "แบ่งปัน")),
            Question(text = "support แปลว่าอะไร?",
                    answers = listOf("สนับสนุน", "แนะนำ", "ความแปลกใจ", "แจกจ่าย")),
            Question(text = "especially แปลว่าอะไร?",
                    answers = listOf("โดยเฉพาะ", "แม้ว่า", "ที่แท้จริง", "ประสบการณ์"))
    )



    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private var score = 0
    private var countwrong = 0

    private val numQuestions = questions.size

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


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
                    score++
                    questionIndex++
                    binding.textView.text = "Score : " + score.toString()
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController()
                                .navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(score,numQuestions))
                    }
                } else {
                    questionIndex++
                    countwrong++
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController()
                                .navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(score,numQuestions))
                    }
                    // Game over! A wrong answer sends us to the gameOverFragment.
//                    view.findNavController()
//                            .navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
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
