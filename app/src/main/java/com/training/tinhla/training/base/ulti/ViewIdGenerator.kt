package com.training.tinhla.training.base.ulti

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import java.util.concurrent.atomic.AtomicInteger

class ViewIdGenerator {
    companion object {
        private val idGenerator : AtomicInteger = AtomicInteger(1)

        @SuppressLint("NewApi")
        fun generate() : Int{
            if (Build.VERSION.SDK_INT < 17) {
                while (true) {
                    var res = idGenerator.get()
                    var newValue = res + 1
                    if (newValue > 0x00FFFFFF) {
                        newValue = 1
                    }
                    if (idGenerator.compareAndSet(res, newValue)) {
                        return res
                    }
                }
            }
            return View.generateViewId()
        }
    }
}