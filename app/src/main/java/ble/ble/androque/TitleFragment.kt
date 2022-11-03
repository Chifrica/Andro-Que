package ble.ble.androque

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ble.ble.androque.R.id.action_titleFragment_to_homeFragment
import ble.ble.androque.databinding.FragmentTitleBinding

class TitleFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         val binding: FragmentTitleBinding = DataBindingUtil.inflate(
             inflater, R.layout.fragment_title, container, false
         )

        binding.getStarted.setOnClickListener (
            Navigation.createNavigateOnClickListener(action_titleFragment_to_homeFragment)
        )
            setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
        requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}