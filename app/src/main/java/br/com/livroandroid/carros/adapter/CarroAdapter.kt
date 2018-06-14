package br.com.livroandroid.carros.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import kotlinx.android.synthetic.main.adapter_carro.view.*


//Defines the constructor that gets (carros, on Click)
class CarroAdapter (val carros: List<Carro>,
                    val onClick: (Carro) -> Unit):
        RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>() {

    //ViewHolder with the views
    class CarrosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    //Returns the cars amount
    override fun getItemCount() = this.carros.size

    //Inflates the layout of the adapter and returns the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosViewHolder {
        //Inflates the view of the adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_carro, parent, false)

        //Returns the ViewHolder that contains all views
        val holder = CarrosViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {


        //Retrieves the car object
        val carro = carros[position]

        val view = holder.itemView

        with(view) {

            //Update the cars data
            tNome.text = carro.nome

            //Downloads the picture and shows the ProgressBar
            img.loadUrl(carro.urlFoto, progress)

            //Adds the click event into the line
            setOnClickListener { onClick(carro) }
        }
    }


}