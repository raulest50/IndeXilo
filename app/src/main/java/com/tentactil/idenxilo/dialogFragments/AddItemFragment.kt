package com.tentactil.idenxilo.dialogFragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tentactil.idenxilo.HomeFragment
import com.tentactil.idenxilo.dataModels.Item
import com.tentactil.idenxilo.dataModels.Movimiento
import com.tentactil.idenxilo.databinding.FragmentAddItemBinding
import io.realm.Realm
import io.realm.RealmList
import java.util.*


class AddItemFragment(var homeFragment:HomeFragment) : DialogFragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentAddItemBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        isCancelable = true

        binding.btnSave.setOnClickListener({ Check_n_Insert() })

        binding.btnCancel.setOnClickListener({ dismiss() })

        // Inflate the layout for this fragment
        return view
    }

    /**
     * Inserta un obj. en la base de datos realm
     */
    fun Check_n_Insert(){
        var blank = false
        val id = binding.etCodigo.text
        val descri = binding.etDescripcion.text
        if(id.isBlank() || descri.isBlank()) blank = true

        if(blank == false) {
            Realm.getInstanceAsync(homeFragment.act.config, object : Realm.Callback() {
                override fun onSuccess(realm: Realm) {
                    var x = realm.where(Item::class.java).equalTo("_id", id.toString()).findFirst()
                    if (x == null) { // el id no existe
                        // se inserta el item
                        Realm.getInstanceAsync(homeFragment.act.config, object : Realm.Callback(){
                            override fun onSuccess(realm: Realm) {
                                realm.executeTransaction{ r: Realm ->
                                    val item = Item(id.toString(), descri.toString(), 0, RealmList<Movimiento>() )
                                    realm.insertOrUpdate(item)
                                    homeFragment.LoadItemList(homeFragment.act.config)
                                    dismiss()
                                }
                            }
                        })
                    } else {
                      // avisar al usuario que el item ya existe
                    }
                }
            })
        }
    }

}