package com.training.tinhla.training.splashscreen.header_images


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.training.tinhla.training.R
import kotlinx.android.synthetic.main.fragment_background_image.view.*

/**
 * Fragment show background image
 */
class BackgroundImageFragment : Fragment() {
    companion object {

        fun newInstance(url: String) : BackgroundImageFragment {
            val fragment = BackgroundImageFragment()
            val args = Bundle()
            args.putString("url", url)
            fragment.arguments = args

            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_background_image, container, false)
        val url = arguments?.getString("url")

        if (url.equals("")) {
            Glide.with(this).load(R.drawable.bg_default).into(v.img_view_background)
        }else{
            Glide.with(this).load(url).into(v.img_view_background)
        }

        return v
    }
}
