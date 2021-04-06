package com.tentactil.idenxilo.dialogFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.tentactil.idenxilo.R
import com.tentactil.idenxilo.dataModels.Item
import com.tentactil.idenxilo.databinding.FragmentAddItemBinding
import com.tentactil.idenxilo.databinding.FragmentMovimientoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MovimientoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovimientoFragment(var item: Item) : DialogFragment() {

    private var _binding: FragmentMovimientoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentMovimientoBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        isCancelable = true

        binding.txvSelectedItem.setText(item._id)

        binding.btnGuardar.setOnClickListener({
            var N = binding.etCantidad.text
            if(N.isNotBlank()){ // solo si el campo de texto de cantidad no esta vacio
                // no se verifica si es numero ya que el campo de texto es numerico, no acepta letras.

                // se ingresa el movimiento a realm
            }
        })

        binding.btnCancelar.setOnClickListener({dismiss()})

        // Inflate the layout for this fragment
        return view
    }
}