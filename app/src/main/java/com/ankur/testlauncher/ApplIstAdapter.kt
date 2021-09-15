package com.ankur.testlauncher

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ApplIstAdapter(

    val context: Context,
    val appList: ArrayList<AppModel>

) : RecyclerView.Adapter<ApplIstAdapter.MyViewHolder>() {

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image: AppCompatImageView = v.findViewById(R.id.app_logo)
        val name: AppCompatTextView = v.findViewById(R.id.app_name)
        val app: CardView = v.findViewById(R.id.logo)

        fun bind(model: AppModel) {
            image.setImageDrawable(model.image)
            name.text = model.name

            app.setOnClickListener(View.OnClickListener {
                val intent: Intent? =
                    context.packageManager.getLaunchIntentForPackage(model.packageName)
                if (intent != null) {
                    context.startActivity(intent)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(appList[position])
    }

    override fun getItemCount(): Int {
        return appList.size
    }
}