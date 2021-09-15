package com.ankur.testlauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {
    lateinit var page: ViewPager
    lateinit var appDrawer: LinearLayout
    lateinit var drawerGrid: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initElement()
        initDrawer()
    }

    private fun initElement() {
        page = findViewById(R.id.viewPager)
    }

    fun initDrawer() {
        appDrawer = findViewById(R.id.all_app) as LinearLayout
        drawerGrid = findViewById(R.id.drawer_grid)
        val sheetBehavior = BottomSheetBehavior.from<LinearLayout>(appDrawer)
        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        sheetBehavior.isHideable = false
        sheetBehavior.peekHeight = 100

        val list: ArrayList<AppModel> = getInstallAppList()

        drawerGrid.adapter = ApplIstAdapter(this, list)
        drawerGrid.layoutManager = GridLayoutManager(this, 5)
        drawerGrid.hasFixedSize()
    }

    private fun getInstallAppList(): ArrayList<AppModel> {
        val list: ArrayList<AppModel> = ArrayList()
        val intent: Intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val untretedAppLIst: ArrayList<ResolveInfo> =
            applicationContext.packageManager.queryIntentActivities(
                intent,
                0
            ) as ArrayList<ResolveInfo>

        for (i in untretedAppLIst) {
            list.add(
                AppModel(
                    i.activityInfo.loadLabel(packageManager).toString(),
                    i.activityInfo.packageName.toString(),
                    i.activityInfo.loadIcon(packageManager)
                )
            )
        }

        return list
    }

}

