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
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.android.navigation.database.PlayerDataModel
import com.example.android.navigation.databinding.FragmentGameWonBinding
import com.example.android.navigation.databinding.FragmentHighScoreBinding

@Suppress("DEPRECATION")
class HighScoreFragment : Fragment() {
    private  lateinit var binding : FragmentHighScoreBinding
    private lateinit var model: HighScoreViewModel
    //lateinit var array : ArrayList<ListView>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding =   DataBindingUtil.inflate(
                inflater, R.layout.fragment_high_score, container, false)
        model = ViewModelProviders.of(this).get(HighScoreViewModel::class.java)

        binding.playAgainButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_highScoreFragment_to_titleFragment)
        }


        val adapter = ListAdapter()
        binding.sleepList.adapter = adapter

        model.all.observe(viewLifecycleOwner, Observer {
            it?.let() {
                //Log.i("asd","${it} and ${it.PlayerName}")

                //array.add(ListView(it.PlayerName,it.PlayerScore))

                adapter.data = it
                //adapter.data = listOf(it)
                //Log.i("data","No.${count} : ${adapter.data}")

            }
//            if(count== t.size) {
//                val adapter = ListAdapter()
//                binding.sleepList.adapter = adapter
//                adapter.data = array
//                // Inflate the layout for this fragment
//            }
        })
        binding.button2.setOnClickListener {
            model.clear()
        }

        return binding.root
    }
}
