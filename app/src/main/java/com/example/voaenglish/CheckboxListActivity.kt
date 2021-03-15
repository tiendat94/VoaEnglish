package com.example.voaenglish

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.work.*
import com.example.voaenglish.adapter.CheckBoxAdapter
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.callback.UserDao
import com.example.voaenglish.database.AppDatabase
import com.example.voaenglish.databinding.ActivityCheckboxBinding
import com.example.voaenglish.model.UserKotlin
import com.example.voaenglish.viewmodel.CheckboxViewModel
import com.example.voaenglish.viewmodel.RadioViewModel
import com.example.voaenglish.viewmodel.ScoreViewModel
import com.example.voaenglish.viewmodel.UserViewModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_checkbox.*
import kotlinx.android.synthetic.main.activity_flag.*
import kotlinx.android.synthetic.main.activity_flag.country_rv
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CheckboxListActivity : BaseActivity(), LifecycleOwner {

    private lateinit var viewModel: CheckboxViewModel
    private lateinit var adapter: CheckBoxAdapter
    private lateinit var viewModelRadio: RadioViewModel
    private lateinit var rewardedAd: RewardedAd
    private lateinit var viewModelScore: ScoreViewModel
    private lateinit var viewModelUser: UserViewModel
    private lateinit var binding: ActivityCheckboxBinding

    private lateinit var userDao: UserDao


    private var adLoadProgresss = 0
    private var dirtLevel = 20

    private val waterFilter = { level: Int -> level / 2 }

    fun encodeMsg(msg: String, encode: (String) -> String): String {
        return encode(msg)
    }

    private val enc1: (String) -> String = { input -> input.toUpperCase() }

    inline fun repeat(times: Int, action: (Int) -> Unit) {}

    private val books = listOf("nature", "biology", "birds")

    private val instruments = listOf("viola", "cello", "violin")
    private val filtered = instruments.asSequence().filter { it[0] == 'v' }
    private val newList = filtered.toList()
    private val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5), setOf(1, 2))

    class House {
        val color: String = "white"
        val numberOfWindows: Int = 2
        val isForSale: Boolean = false

        fun updateColor(newColor: String) {

        }
    }

    class Box(val length: Int, val width: Int = 20, val height: Int = 40)

    class Square(val side: Int) {
        init {
            println(side * 2)
        }
    }

    class Person(val firstName: String, val lastName: String) {
        val fullName: String
            get() {
                return "$firstName $lastName"
            }
    }

    interface Shape {
        fun computeArea(): Double
    }

    class Circle(val radius: Double) : Shape {
        override fun computeArea(): Double {
            return Math.PI * radius * radius
        }

    }

    abstract class Food {
        abstract val kcal: Int
        abstract val name: String
        fun consume(): String {
            return "I'm eating ${name}"
        }
    }

    class Pizza() : Food() {
        override val kcal: Int
            get() = 600
        override val name: String
            get() = "Pizza"

    }

    fun Int.isOdd(): Boolean {
        return this % 2 == 1
    }

    enum class Color(val r: Int, val g: Int, val b: Int) {
        RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255)
    }

    object Calculator {
        fun add(n1: Int, n2: Int): Int {
            return n1 + n2
        }
    }

    class PhysicsSystem {
        companion object WorldConstants {
            val gravity = 9.8
            val unit = "metric"
            fun computeForce(mass: Double, accel: Double): Double {
                return mass * accel
            }
        }
    }

    fun openExternalApp() {
        val intent = Intent("com.example.workapp.FILE_OPEN")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    internal class MyLocationListener(private val context: Context, private val lifecycle: Lifecycle, private val callback: (Location) -> Unit) {
        private var enable = false

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun start() {
            //connect to system location service
            if (enable) {
                //connect
                Toast.makeText(context, "on start", Toast.LENGTH_LONG).show()
            }
        }

        fun enable() {
            enable = true

            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                // connect if not connected

            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun stop() {
            // disconnect from system location service
            Toast.makeText(context, "on stop", Toast.LENGTH_LONG).show()

        }
    }

    private lateinit var myLocationListener: MyLocationListener

    class UploadWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
        override suspend fun doWork(): Result {

            return Result.success()
        }
    }


    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_checkbox
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkbox)
        viewModelScore = ViewModelProviders.of(this@CheckboxListActivity).get(ScoreViewModel::class.java)
        binding.viewModelScore = viewModelScore


        val userKotlin = UserKotlin(1, "tien dat", "nguyen")

        val db = Room.inMemoryDatabaseBuilder(applicationContext, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        userDao = db.userDao
        userDao.insertAll(userKotlin)

        val user = userDao.getAll()

        GlobalScope.launch {
            user?.forEach {
                Log.d("CheckboxListActivity", "userName = " + it.firstName.toString())
            }
        }

        //database
//        viewModelUser = ViewModelProviders.of(this@CheckboxListActivity).get(UserViewModel::class.java)
//        viewModelUser.insert(userKotlin)

        myLocationListener = MyLocationListener(this, lifecycle) {
            // update UI

        }
        Log.d("CheckboxListActivity", 3.isOdd().toString())
        Log.d("CheckboxListActivity", Color.RED.r.toString() + Color.RED.g.toString() + Color.RED.b.toString())
        Log.d("CheckboxListActivity", Calculator.add(3, 5).toString())
        Log.d("CheckboxListActivity", PhysicsSystem.WorldConstants.gravity.toString())
        Log.d("CheckboxListActivity", PhysicsSystem.WorldConstants.computeForce(3.5, 6.7).toString())
        Log.d("CheckboxListActivity", Pizza().consume().toString())
        val c = Circle(3.0)
        Log.d("CheckboxListActivity", c.computeArea().toString())
        val person = Person("tien dat", "nguyen")
        Log.d("CheckboxListActivity", person.fullName)
        val box3 = Box(length = 100, width = 20, height = 40)
        Log.d("CheckboxListActivity", box3.length.toString() + "\n" + box3.height + "\n" + box3.width)
        Log.d("CheckboxListActivity", books.filter { it[0] == 'b' }.toString())
        Log.d("CheckboxListActivity", newList.toString())
        Log.d("CheckboxListActivity", numberSets.flatten().toString())
        val s = Square(10)
        Log.d("CheckboxListActivity", s.toString())
        val myHouse = House()
        Log.d("CheckboxListActivity", myHouse.color)

        repeat(3) {
            Log.d("CheckboxListActivity", "Hello Kotlin")
        }
        Log.d("CheckboxListActivity", "message" + encodeMsg("nguyen tien dat", enc1))
        adapter = CheckBoxAdapter()
        brands_list.layoutManager = LinearLayoutManager(this)
        brands_list.setHasFixedSize(true)
        viewModel = ViewModelProviders.of(this@CheckboxListActivity).get(CheckboxViewModel::class.java)
        viewModel?.getListCheckbox()
        viewModel?.checkModelLive?.observe(this@CheckboxListActivity, Observer {
            it?.let {
                Log.d("CheckboxListActivity", it[0]?.name.toString())
                adapter?.updateRepoList(it)
                brands_list?.adapter = adapter
            }
        })

        viewModelRadio = ViewModelProviders.of(this@CheckboxListActivity).get(RadioViewModel::class.java)
        viewModelRadio?.getOffers()
        viewModelRadio?.radioLiveData?.observe(this@CheckboxListActivity, Observer {
            it?.let {
                Log.d("CheckboxListActivity", it[0]?.name.toString())
            }
        })


        viewModelScore?.incrementScore(true)
        binding.viewModelScore?.score?.observe(this@CheckboxListActivity, Observer {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
        })

        Log.d("CheckboxListActivity", waterFilter(dirtLevel).toString())
        //registerForContextMenu(brands_list)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()

            }
        }

//        btn_menu.setOnClickListener {
//            openExternalApp()
//        }

        rewardedAd()
    }

    private fun rewardedAd() {
        fun createAndLoadRewardedAd(): RewardedAd {
            rewardedAd = RewardedAd(this, "ca-app-pub-3940256099942544/5224354917")
            val adLoadCallback = object : RewardedAdLoadCallback() {
                override fun onRewardedAdLoaded() {
                    super.onRewardedAdLoaded()
                    adsLoadedProgress()
                }

                override fun onRewardedAdFailedToLoad(p0: LoadAdError?) {
                    super.onRewardedAdFailedToLoad(p0)
                }
            }

            rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
            return rewardedAd
        }

        fun onRewardedAdClosed() {
            this.rewardedAd = createAndLoadRewardedAd()
        }
        createAndLoadRewardedAd()


        btn_menu.setOnClickListener {
            val activityContext: Activity = this@CheckboxListActivity
            val adCallback = object : RewardedAdCallback() {
                override fun onUserEarnedReward(p0: RewardItem) {
                    TODO("Not yet implemented")

                }

            }
            rewardedAd.show(activityContext, adCallback)
        }
    }

    private fun adsLoadedProgress() {
        ++adLoadProgresss
        if (adLoadProgresss == 3) {
            Toast.makeText(this, "All ads are loaded", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Context Menu")
        menu?.add(0, v!!.id, 0, "Share")

    }

    override fun onStart() {
        super.onStart()
        myLocationListener.start()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
        myLocationListener.stop()

    }

    override fun onDestroy() {
        super.onDestroy()

    }


}