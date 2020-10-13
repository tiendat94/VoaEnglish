package com.example.voaenglish.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.voaenglish.CollapsingActivity
import com.example.voaenglish.MainActivityKotlin
import com.example.voaenglish.R
import com.example.voaenglish.adapter.PetsHomeAdapter
import com.example.voaenglish.databinding.FragmentViewpagerHomeBinding
import com.example.voaenglish.model.PetsModel
import com.example.voaenglish.service.SongService
import kotlinx.android.synthetic.main.fragment_viewpager_home.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startService

class HomeFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentViewpagerHomeBinding
    private lateinit var adapter: PetsHomeAdapter
    private lateinit var models: ArrayList<PetsModel>
    private var dotscount = 0
    private val TAG: String? = HomeFragment::class.java.simpleName

    companion object {
        fun newInstance(position: Int?): HomeFragment {
            val instance = HomeFragment()
            var args = Bundle()
            args.putInt("position", position!!)
            instance.arguments = args
            return instance
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentViewpagerHomeBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(viewLifecycleOwner)
        }
        Log.d(TAG, "onCreateView")
        return viewDataBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
        imageGoToActivity?.setOnClickListener {
            val intent = Intent(activity!!, SongService::class.java)
            activity!!.startService(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        adapter = PetsHomeAdapter(models, activity!!)
        view_pager_home.adapter = adapter
        view_pager_home?.setPadding(30, 0, 30, 0)
        dotscount = adapter?.count

        val dots = arrayOfNulls<ImageView>(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(activity!!)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.non_active_dot))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            slider_dots!!.addView(dots[i], params)
        }

        dots[0]?.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.active_dot))
        view_pager_home?.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.non_active_dot))
                }
                dots[position]?.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.active_dot))
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        imageGoToActivity?.setOnClickListener {
            val intent = Intent(activity, CollapsingActivity::class.java)
            startActivityForResult(intent, 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                val returnResult = data?.getStringExtra("HELLO")
                Toast.makeText(activity, returnResult, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setData() {
        models = ArrayList()
        models.add(PetsModel(R.drawable.dummy_image, "Dobby", "Kutta"))
        models.add(PetsModel(R.drawable.dummy_image, "Kitto", "billi"))
        models.add(PetsModel(R.drawable.dummy_image, "Cozmo", "Lambardor"))
        models.add(PetsModel(R.drawable.dummy_image, "Tiger", "German Shepherd"))
        models.add(PetsModel(R.drawable.dummy_image, "Husky", "Husky"))
        models.add(PetsModel(R.drawable.dummy_image, "Cat", "Unkwon"))
    }


}