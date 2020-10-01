package com.example.voaenglish.ui.login.message

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voaenglish.R
import com.example.voaenglish.adapter.MessageAdapter
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.databinding.ActivityMessageBinding
import com.example.voaenglish.utils.DirInfo
import com.example.voaenglish.utils.SongInfo
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.fragment_repo_list.*
import java.io.File

class MessageActivity : BaseActivity() {

    private lateinit var activityMessageBinding: ActivityMessageBinding
    private lateinit var adapter: MessageAdapter

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMessageBinding = DataBindingUtil.setContentView(this, R.layout.activity_message)
        activityMessageBinding.viewmodel = ViewModelProviders.of(this@MessageActivity).get(MessageViewModel::class.java)
        activityMessageBinding.viewmodel?.fetListInbox()
        activityMessageBinding.viewmodel?.fetRssFeed()
        setupObservers()
        setupAdapter()
        listenerSwipe()

        checkAllMessage.setOnClickListener {
            if (checkAllMessage.isChecked) {
                adapter?.selectAll()

            } else {
                adapter?.unSelectAll()
            }
        }

        activityMessageBinding.executePendingBindings()
    }

    private fun getAudioDirectories(): ArrayList<DirInfo> {
        var result = ArrayList<DirInfo>()
        val directories = LinkedHashMap<String, ArrayList<SongInfo>>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val order = MediaStore.Audio.Media.DATE_MODIFIED + "DESC"
        val cursor = contentResolver?.query(uri, null, selection, null, order)
        cursor.let {
            it?.moveToFirst()
            val pathIndex = it?.getColumnIndex(MediaStore.Images.Media.DATA)
            do {
                val path = pathIndex?.let { it1 -> it?.getString(it1) }
                val file = File(path)
                if (!file.exists()) {
                    continue
                }
                val fileDir = file.parent
                var songURL = it?.getString(it?.getColumnIndex(MediaStore.Audio.Media.DATA))
                var songAuth = it?.getString(it?.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                var songName = it?.getString(it?.getColumnIndex(MediaStore.Audio.Media.TITLE))

                if (directories.containsKey(fileDir)) {
                    var songs = directories.getValue(fileDir)
                    var song = SongInfo(songURL, songAuth, songName)
                    songs.add(song)
                    directories.put(fileDir, songs)
                } else {
                    var song = SongInfo(songURL, songAuth, songName)
                    var songs = ArrayList<SongInfo>()
                    songs.add(song)
                    directories.put(fileDir, songs)
                }

            } while (it?.moveToNext()!!)

            for (dir in directories) {
                var dirInfo: DirInfo = DirInfo(dir.key, dir.value)
                result.add(dirInfo)
            }

        }
        return result
    }

    private fun listenerSwipe() {
        swipe_refresh_layout.setOnRefreshListener {
            swipe_refresh_layout.isRefreshing = false
        }
    }

    private fun setupObservers() {
        activityMessageBinding.viewmodel?.repoListInboxLive?.observe(this, Observer {
            adapter?.updateListMessage(it)
            Log.d("MessageActivity", it[0]?.message)
        })

        activityMessageBinding.viewmodel?.rssFeedLive?.observe(this, Observer {
            Log.d("MessageActivity", it?.items!!.get(0)!!.image.toString())
        })
    }

    private fun setupAdapter() {
        val viewModel = activityMessageBinding?.viewmodel
        if (viewModel != null) {
            adapter = MessageAdapter(viewModel)
            val layoutManager = LinearLayoutManager(this@MessageActivity)
            message_list_rv.layoutManager = layoutManager
            message_list_rv.addItemDecoration(DividerItemDecoration(this@MessageActivity, layoutManager.orientation))
            message_list_rv.adapter = adapter
        }
    }

}