package com.training.tinhla.training.splashscreen.background_image


import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.training.tinhla.training.R
import kotlinx.android.synthetic.main.fragment_background_image.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BackgroundImageFragment : Fragment() {
    companion object {

        fun newInstance(url: String) : BackgroundImageFragment {
            var fragment = BackgroundImageFragment()
            var args = Bundle()
            args.putString("url", url)
            fragment.arguments = args

            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_background_image, container, false)
        var url = arguments?.getString("url")
        Log.d("LOG", "url: " + url)
        v.img_view_background.setImageURI(Uri.parse(url))

        return v
    }


}
