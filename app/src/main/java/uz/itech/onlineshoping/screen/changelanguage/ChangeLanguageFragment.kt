package uz.itech.onlineshoping.screen.changelanguage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orhanobut.hawk.Hawk
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.FragmentChangeLanguageBinding
import uz.itech.onlineshoping.databinding.FragmentHomeBinding
import uz.itech.onlineshoping.screen.MainActivity

class ChangeLanguageFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentChangeLanguageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding= FragmentChangeLanguageBinding.inflate(inflater,container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChangeLanguageFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnUzbek.setOnClickListener {
            Hawk.put("pref_lang","uz")
            requireActivity().finish()
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
        binding.btnEnglish.setOnClickListener {
            Hawk.put("pref_lang","en")
            requireActivity().finish()
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }
    }
}