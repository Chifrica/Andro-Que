package ble.ble.androque

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ble.ble.androque.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        binding.startGame.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_gameFragment)
        )

        return binding.root

    }
}