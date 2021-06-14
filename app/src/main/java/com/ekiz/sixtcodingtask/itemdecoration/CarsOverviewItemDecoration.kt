package com.ekiz.sixtcodingtask.itemdecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ekiz.sixtcodingtask.R

class CarsOverviewItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect,
                                view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val resources = context.resources
        val padding = resources.getDimensionPixelSize(R.dimen.recycler_view_padding)
        val horizontalMargin = resources.getDimensionPixelSize(R.dimen.recycler_view_element_horizontal_margin)
        val position = parent.getChildAdapterPosition(view)

        when (position) {
            0 -> outRect.set(padding, padding, horizontalMargin, padding)
            state.itemCount - 1 -> outRect.set(0, padding, padding, padding)
            else -> outRect.set(0, padding, horizontalMargin, padding)
        }
    }
}