package com.example.voaenglish.ui.login.slider

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.voaenglish.FlagsActivity
import com.example.voaenglish.R
import com.example.voaenglish.adapter.SliderImageAdapter
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.databinding.ActivitySliderImageBinding
import com.example.voaenglish.model.ImageUploadResponse
import com.example.voaenglish.model.SliderItem
import com.example.voaenglish.model.api.ApiClient
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_slider_image.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.jar.Manifest

class SliderImageActivity : BaseActivity() {

    private lateinit var activitySliderImageBinding: ActivitySliderImageBinding

    private lateinit var adapter: SliderImageAdapter

    private lateinit var viewModel: SliderImageViewModel

    private val callbackManager = CallbackManager.Factory.create()

    private var imageList: ArrayList<SliderItem>? = null
    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_slider_image
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySliderImageBinding = DataBindingUtil.setContentView(this, R.layout.activity_slider_image)
        viewModel = ViewModelProviders.of(this@SliderImageActivity).get(SliderImageViewModel::class.java)

        doAsync {
            uiThread {
                setupAdapter()
                update()
                login_button?.setReadPermissions("email")
                login_button?.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        Toast.makeText(this@SliderImageActivity, result.toString(), Toast.LENGTH_LONG).show()
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {

                    }

                })

                val accessToken = AccessToken.getCurrentAccessToken()
                val isLoggedIn = accessToken != null && !accessToken.isExpired

                var content = ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build()


                buttonShare?.setOnClickListener {
//                    val shareDialog = ShareDialog(this@SliderImageActivity)
//                    shareDialog?.show(content, ShareDialog.Mode.AUTOMATIC)
                    val newDocumentIntent = newDocumentIntent()
                    newDocumentIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                    startActivity(newDocumentIntent)
                }

            }
        }

        doAsync {
            uiThread {
                var gpath: String = Environment.getExternalStorageDirectory().isDirectory.toString()
                var fullpath = File(gpath + File.separator)
                Log.w("fullpath", "" + fullpath)
                imageReaderNew(fullpath)

                checkPermissionReadFile()
            }
        }
        viewModel?.getImageList()?.observe(this, Observer<List<SliderItem>> { images ->
            adapter?.updateListImageSlider(images)
        })
        activitySliderImageBinding?.executePendingBindings()
    }

    private fun newDocumentIntent(): Intent = Intent(this, FlagsActivity::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or android.content.Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            imageGallery?.setImageURI(data?.data)
        }
    }

    private fun uploadImage(file: File?) {
        var parts: ArrayList<MultipartBody.Part>? = null
        var requestImageFile = RequestBody.create(MediaType.parse("image/"), file)
        parts?.add(MultipartBody.Part.createFormData("file", file?.name, requestImageFile))
        ApiClient.instance.uploadImage("", parts!!).enqueue(object : Callback<ImageUploadResponse> {
            override fun onResponse(call: Call<ImageUploadResponse>, response: Response<ImageUploadResponse>) {

            }

            override fun onFailure(call: Call<ImageUploadResponse>, t: Throwable) {

            }

        })

    }

    private fun checkPermissionReadFile() {
        Dexter.withActivity(this@SliderImageActivity)
                .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        pickImageFromGallery()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {

                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {

                    }

                }).check()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    fun imageReaderNew(root: File) {
        val fileList: ArrayList<File> = ArrayList()
        val listAllFiles = root.listFiles()

        if (listAllFiles != null && listAllFiles.size > 0) {
            for (currentFile in listAllFiles) {
                if (currentFile.name.endsWith(".jpeg")) {
                    // File absolute path
                    Log.e("downloadFilePath", currentFile.getAbsolutePath())
                    // File Name
                    Log.e("downloadFileName", currentFile.getName())
                    fileList.add(currentFile.absoluteFile)
                }
            }
            Log.w("fileList", "" + fileList.size)
        }
    }

    fun setupAdapter() {
        adapter = SliderImageAdapter(this@SliderImageActivity)
        imageSlider?.setSliderAdapter(adapter)
        imageSlider?.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider?.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider?.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        imageSlider?.setIndicatorSelectedColor(Color.WHITE)
        imageSlider?.setIndicatorUnselectedColor(Color.GRAY)
        imageSlider?.scrollTimeInSec = 3
        imageSlider?.isAutoCycle = true
        imageSlider?.startAutoCycle()

        imageSlider?.setOnIndicatorClickListener {

        }
    }


    fun update() {
        val sliderItemList: MutableList<SliderItem> = java.util.ArrayList<SliderItem>()
        //dummy data
        for (i in 0..4) {
            val sliderItem = SliderItem("", "")
            if (i % 2 == 0) {
                sliderItem.imageUrl = "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            } else {
                sliderItem.imageUrl = "https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"
            }
            sliderItem?.let { sliderItemList?.add(it) }
        }
        viewModel?.setImageList(sliderItemList)
        adapter.updateListImageSlider(sliderItemList)
    }


}