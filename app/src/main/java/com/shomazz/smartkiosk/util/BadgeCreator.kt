package com.shomazz.smartkiosk.util

import android.content.res.Resources
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.shomazz.smartkiosk.MainActivity
import com.shomazz.smartkiosk.R
import com.shomazz.smartkiosk.domain.model.User
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class BadgeCreator @Inject constructor(
    private val mainActivity: MainActivity,
    private val resources: Resources
) {

    private val badgeHeight = resources.getDimensionPixelSize(R.dimen.badge_height)
    private val badgeWidth = resources.getDimensionPixelSize(R.dimen.badge_width)

    fun getBadgeFilePath(): String {
        return Environment.getExternalStorageDirectory().toString() + PDF_FOLDER + PDF_FILE_TITLE
    }

    fun generateBadgePdf(user: User) {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(badgeWidth, badgeHeight, 1).create()
        val page = document.startPage(pageInfo)
        val badgeView = getPreparedBadgeView(user)
        badgeView.draw(page.canvas)
        document.finishPage(page)
        document.writeTo(getOutputStream())
    }

    private fun getPreparedBadgeView(user: User): View {
        return LayoutInflater.from(mainActivity).inflate(R.layout.badge_template, null).apply {
            findViewById<TextView>(R.id.badgeNameTextView).text = user.name
            findViewById<TextView>(R.id.badgeCompanyTextView).text = user.organization
            findViewById<TextView>(R.id.badgeProfessionTextView).text = user.payload
            measure(badgeWidth, badgeHeight)
            layout(0, 0, badgeWidth, badgeHeight)
        }
    }

    private fun getOutputStream(): FileOutputStream {
        val folder = File(Environment.getExternalStorageDirectory().toString() + PDF_FOLDER)
        folder.mkdir()
        return FileOutputStream(File(folder, PDF_FILE_TITLE))
    }

    companion object {
        private const val PDF_FOLDER = "/SmartKiosk"
        private const val PDF_FILE_TITLE = "badge.pdf"
    }

}