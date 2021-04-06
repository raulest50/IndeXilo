package com.tentactil.idenxilo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.tentactil.idenxilo.dataModels.Item
import com.tentactil.idenxilo.databinding.ItemBinding
import com.tentactil.idenxilo.dialogFragments.MovimientoFragment
import java.lang.IndexOutOfBoundsException
import java.util.*

class ItemAdapter(var supportFragmentManager: FragmentManager):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var listaItems = LinkedList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.render(listaItems[position])
    }

    override fun getItemCount(): Int {
        return listaItems.size
    }

    fun removeItem(i:Int){
        try{ // cuando se oprime el boton de remover varias veces puede ocurrir indexOutofBounds
            listaItems.removeAt(i)
            notifyItemRemoved(i)
            //ActualizarSumaVenta()
            //ntFragment() // se actualiza el label, suma_ListaCompra y se notifica orondo por websocket
        } catch (e: IndexOutOfBoundsException){ } //do nothing just to avoid crash
    }

    fun AgregarItemVenta(item:Item){
        listaItems.addFirst(item)
        AplicarCambios()
        //ntFragment() // EL fragmento reacciona a los cambios de listaCompra
    }

    fun setLista(lt:LinkedList<Item>){
        this.listaItems = lt
        notifyDataSetChanged()
    }

    fun AplicarCambios(){
        //ActualizarSumaVenta() // actualiza suma venta textView
        notifyDataSetChanged() // se dibujan los cambios
        //ntFragment()
    }

    //inner class ItemViewHolder(var view: View):RecyclerView.ViewHolder(view){}

    inner class ItemViewHolder(var view: View):RecyclerView.ViewHolder(view){

        // es empleado por la api para dibujara cada itemVenta en el recyclerView
        fun render(item:Item){
            val binding:ItemBinding = ItemBinding.bind(view)
            binding.tvCodigo.text = item._id
            binding.tvDescripcion.text = item.nombre
            binding.tvN.text = Integer.toString(item.N)
            binding.btnMovimiento.setOnClickListener({
                var movimientoDialogFragment = MovimientoFragment(item)
                movimientoDialogFragment.show(supportFragmentManager, "Ingresar movimiento del item")
            })
        }
    }
}