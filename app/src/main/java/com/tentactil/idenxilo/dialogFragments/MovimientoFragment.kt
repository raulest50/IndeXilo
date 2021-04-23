package com.tentactil.idenxilo.dialogFragments

import android.icu.number.IntegerWidth
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tentactil.idenxilo.HomeFragment
import com.tentactil.idenxilo.ItemAdapter
import com.tentactil.idenxilo.R
import com.tentactil.idenxilo.dataModels.Item
import com.tentactil.idenxilo.dataModels.Movimiento
import com.tentactil.idenxilo.databinding.FragmentAddItemBinding
import com.tentactil.idenxilo.databinding.FragmentMovimientoBinding
import io.realm.Realm
import io.realm.RealmList
import org.bson.BSON
import org.joda.time.LocalDateTime
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [MovimientoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovimientoFragment(var item: Item, var homeFragment: HomeFragment, var supportFragmentManager: FragmentManager) : DialogFragment() {

    private var _binding: FragmentMovimientoBinding? = null
    private val binding get() = _binding!!

    // Lista en la que se pondra los movimientos de el item seleccionado
    lateinit var movimientoAdapter: MovimientoAdapter
    private lateinit var viewManagerMov: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentMovimientoBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        // se inicializa el recyvlerview (lista en la que se ponen los items en bodega)
        viewManagerMov = LinearLayoutManager(this.activity)
        movimientoAdapter = MovimientoAdapter()

        binding.recyclerViewListaMovimientos.layoutManager = viewManagerMov
        binding.recyclerViewListaMovimientos.adapter = movimientoAdapter

        isCancelable = true

        binding.txvSelectedItem.setText(item._id)

        movimientoAdapter.setLista(item.getMovs())

        binding.btnGuardar.setOnClickListener({
            var N = binding.etCantidad.text

            if(InsertarMovValido(N.toString())) {
                // se construye y agrega el movimiento al obj item
                val desc = binding.etDescripcion.text.toString()
                InsertarMovimiento(Movimiento(N.toString().toInt(), desc, LocalDateTime.now().toString()))
            }
        })

        binding.btnCancelar.setOnClickListener({dismiss()})

        // Inflate the layout for this fragment
        return view
    }

    fun InsertarMovValido(N:String):Boolean{
        var r = true
        // no se verifica si es numero ya que el campo de texto es numerico, no acepta letras.
        if(N.isBlank()) r = false
        if(N.toInt() == 0) r = false
        return r
    }

    fun InsertarMovimiento(mov:Movimiento) {

        Realm.getInstanceAsync(
            homeFragment.act.config,
            object : Realm.Callback() { // se actualiza el item en la base de datos
                override fun onSuccess(realm: Realm) {
                    Realm.getInstanceAsync(homeFragment.act.config, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm) {
                            realm.executeTransaction { r: Realm ->
                                item.AddMovimiento(mov)
                                realm.insertOrUpdate(item)
                                homeFragment.LoadItemList(homeFragment.act.config)
                                dismiss()
                            }
                        }
                    })
                }
            })

    }
}