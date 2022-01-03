package com.example.dayliplaner_v1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dayliplaner_v1.databinding.FragmentAddCaseBinding

class AddCaseFragment : Fragment() {
    lateinit var binding: FragmentAddCaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCaseBinding.inflate(inflater, container, false)

        return binding.root
    }
}
