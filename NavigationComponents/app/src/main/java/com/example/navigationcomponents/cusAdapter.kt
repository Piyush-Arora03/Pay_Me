import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponents.databinding.ItemViewBinding
import com.example.navigationcomponents.model

class cusAdapter(val datalist: ArrayList<model>,var context: Context) : RecyclerView.Adapter<cusAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding:ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate your item layout and create a binding instance
        val binding = ItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind your data here
        holder.binding.TextView.text = datalist[position].text.toString()
        // Example: holder.binding.textView.text = item.property
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}
