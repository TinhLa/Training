package com.training.tinhla.training.nkhoi_srcollview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.training.tinhla.training.R
import kotlinx.android.synthetic.main.activity_main_sliding.*

class SlidingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)
//        var listDS : ArrayList<String> = ArrayList()
//        var i: Int =0
//        for(i in 0 until 20 step 1){
//            listDS.add("Phần tử thứ : "+ i)
//        }
//        LvDetail.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listDS)
        TvDetail.setText("aaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbcccccccccccccccccccccdddddddddddddddeeeeeeeeeeeee" +
                "fffffffffffffffffffffffffgggggggggggggggggggggggyhhhhhhhhhhhhhhhhhhhhhhhhhhhjjjjjjjjjjjjj" +
                "qqqqqqqqqqqqqqqqqqqqq" +
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwweeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "rrrrrrrrrrrrrrrrrrrrrrttttttttttttttttttttttttttttttttttttttttttttttt" +
                "sssssssssssssssssssssssssvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" +
                "11111111111111111111111111111111111111111111111111111111111111111" +
                "2222222222222222222222222222222222222222222222222222222222222222" +
                "333333333333333333333333333333333333333333333333333333333333333333333" +
                "4444444444444444444444444444444444444444444444444444444444444444444")
    }
}
