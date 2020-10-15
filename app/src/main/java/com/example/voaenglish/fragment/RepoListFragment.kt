package com.example.voaenglish.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voaenglish.adapter.RepoListAdapter
import com.example.voaenglish.databinding.FragmentRepoListBinding
import com.example.voaenglish.viewmodel.RepoListViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_repo_list.*

class RepoListFragment : Fragment(){

    private val TAG: String? = RepoListFragment::class.java.simpleName

    companion object {
        fun newInstance(position: Int?): RepoListFragment {
            val instance = RepoListFragment()
            val args = Bundle()
            args.putInt("position", position!!)
            instance.arguments = args
            return instance
        }
    }

    private lateinit var viewDataBinding: FragmentRepoListBinding
    private lateinit var adapter: RepoListAdapter

    //map
   // private lateinit var mMap: GoogleMap

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentRepoListBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@RepoListFragment).get(RepoListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.viewmodel?.fetchRepoList()
        viewDataBinding.viewmodel?.fetListInbox()
        openCameraButton?.setOnClickListener {
            checkPermission()
        }

        setupAdapter()
        setupObservers()

        Log.d(TAG, "onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
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

    private fun checkPermission() {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        // permission is granted, open camera
                        openCamera()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        if (response?.isPermanentlyDenied!!) {

                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                        token?.continuePermissionRequest()
                    }

                }).check()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 100)
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.repoLiveLive?.observe(viewLifecycleOwner, Observer {
            adapter?.updateRepoList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        viewDataBinding.viewmodel?.repoListInboxLive?.observe(viewLifecycleOwner, Observer {
            Log.d("fetListInbox", it[0].message)
        })


    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = RepoListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(activity)
            repo_list_rv.layoutManager = layoutManager
            repo_list_rv.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            repo_list_rv.adapter = adapter
        }
    }
//
//    override fun onMapReady(p0: GoogleMap) {
//        mMap = p0
//
//        val sydney = com.google.android.gms.maps.model.LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Austalia"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//    }

}