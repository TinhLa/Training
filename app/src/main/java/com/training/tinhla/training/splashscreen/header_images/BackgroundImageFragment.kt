package com.training.tinhla.training.splashscreen.header_images


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso

import com.training.tinhla.training.R
import kotlinx.android.synthetic.main.fragment_background_image.view.*

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
//        Log.d("LOG", "url: " + url)
        Picasso.get().load(url).into(v.img_view_background)

        return v
    }


}
