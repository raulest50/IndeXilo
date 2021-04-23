package com.tentactil.idenxilo.dialogFragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tentactil.idenxilo.R
import com.tentactil.idenxilo.dataModels.Movimiento
import com.tentactil.idenxilo.databinding.MovimientoBinding
import java.util.*

class MovimientoAdapter():RecyclerView.Adapter<MovimientoAdapter.MovimientoViewHolder>() {

    private var listaMovimientos = LinkedList<Movimiento>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientoAdapter.MovimientoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovimientoViewHolder(layoutInflater.inflate(R.layout.movimiento, parent, false))
    }

    override fun onBindViewHolder(holder: MovimientoAdapter.MovimientoViewHolder, position: Int) {
        holder.render(listaMovimientos[position])
    }

    override fun getItemCount(): Int {
        return listaMovimientos.size
    }

    fun setLista(listaMovimientos:LinkedList<Movimiento>){
        this.listaMovimientos = listaMovimientos
    }

    inner class MovimientoViewHolder(var view: View):RecyclerView.ViewHolder(view){

        // es empleado por la api para dibujara cada movimiento en el recyclerView
        fun render(mov: Movimiento){
            val binding: MovimientoBinding = MovimientoBinding.bind(view)
            binding.txvFecha.setText(mov.fecha.toString())
            binding.txvDelta.setText(mov.D.toString())
            if(mov.D < 0) binding.txvDelta.setTextColor(Color.RED)
            else binding.txvDelta.setTextColor(Color.GREEN)
            binding.txvDescri.setText(mov.descripcion)
        }
    }
}