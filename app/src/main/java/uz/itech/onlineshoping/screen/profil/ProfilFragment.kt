package uz.itech.onlineshoping.screen.profil

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import uz.itech.onlineshoping.firebase.OTPValidation
import uz.itech.onlineshoping.databinding.FragmentProfilBinding
import uz.itech.onlineshoping.utils.CountryData

class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentProfilBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            CountryData.countryNames
        )

        binding.button.setOnClickListener {
            val code =
                CountryData.countryAreaCodes[binding.spinner.selectedItemPosition]
            if (binding.mobileNumber.text.count() == 9) {
                val intent = Intent(requireContext(), OTPValidation::class.java)
                    intent.putExtra("mobileNumber", code+binding.mobileNumber.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Enter 10 digits mobile number",Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
 @JvmStatic
        fun newInstance() =  ProfilFragment()
    }
}