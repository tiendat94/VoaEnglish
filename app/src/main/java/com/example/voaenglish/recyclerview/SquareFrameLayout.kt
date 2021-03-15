package com.example.voaenglish.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class SquareFrameLayout : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context,attributeSet: AttributeSet) : super(context,attributeSet)

    constructor(context: Context,attributeSet: AttributeSet,defStyle : Int) : super(context,attributeSet,defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}