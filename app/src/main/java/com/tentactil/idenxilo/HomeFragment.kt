package com.tentactil.idenxilo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tentactil.idenxilo.dataModels.Item
import com.tentactil.idenxilo.databinding.FragmentHomeBinding
import com.tentactil.idenxilo.dialogFragments.AddItemFragment
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var act:MainActivity // para poder interactuar entre el fragment y la activity

    // this property is only valid between onCreateView and onDestroyView
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Lista en la que se pondra los items en bodega
    lateinit var itemAdapter: ItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        act = this.activity as MainActivity

        // se inicializa el recyvlerview (lista en la que se ponen los items en bodega)
        viewManager = LinearLayoutManager(this.activity)
        itemAdapter = ItemAdapter(act.supportFragmentManager, this)

        binding.recyclerViewListaItems.layoutManager = viewManager
        binding.recyclerViewListaItems.adapter = itemAdapter

        LoadItemList(act.config)

        binding.floatingActionButton.setOnClickListener({
            var addItemDialog:AddItemFragment = AddItemFragment(this)
            addItemDialog.show(act.supportFragmentManager, "Agregar Item a kardex")
        })

        // cada que cambia el text en el campo de busqueda
        binding.etBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                LoadItemList(act.config)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        return view
    }

    /**
     * carga los items en recycler view de items
     */
    fun LoadItemList(config: RealmConfiguration){
        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm) {

                val bsq = binding.etBuscar.text.toString()
                var itl:LinkedList<Item> = LinkedList()
                var x:Iterable<Item>

                if(bsq.isBlank())x = realm.where(Item::class.java).findAll()
                else x = realm.where(Item::class.java).like("_id", bsq).findAll()

                itl.addAll(x)
                itemAdapter.setLista(itl)
            }
        })
    }




}