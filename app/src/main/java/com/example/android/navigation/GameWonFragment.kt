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

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProviders

import androidx.navigation.findNavController
import com.example.android.navigation.database.PlayerDataModel
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    private  lateinit var binding : FragmentGameWonBinding
    private lateinit var model: HighScoreViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        model = ViewModelProviders.of(this).get(HighScoreViewModel::class.java)

        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context, "Correct: ${args.numQuestions}, Questions: ${args.numCorrect}", Toast.LENGTH_LONG).show()
        var score = args.numQuestions
        binding.scoreText.text = "Your Score : ${args.numQuestions}"
        binding.submitButton.setOnClickListener { view: View ->
            //view.findNavController().navigate(GameWonFragmentDirections. )
            if(!binding.editText.text.isEmpty()){
                model.insert(
                        PlayerDataModel(
                                0,
                                binding.editText.text.toString(),
                                score.toString()

                        )
                )
                Toast.makeText(getActivity(),"Insert Succeed",Toast.LENGTH_LONG).show()

                view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameOverFragment())
            }else{
                Toast.makeText(getActivity(),"PLease Insert Your name",Toast.LENGTH_LONG).show()
            }

        }
        setHasOptionsMenu(true)



        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu,menu)
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)

    }
    private fun getShareIntent() : Intent {
        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numQuestions, args.numCorrect))
        return shareIntent
    }



    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
