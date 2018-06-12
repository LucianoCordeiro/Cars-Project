package br.com.livroandroid.carros.activity.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.support.v4.app.DialogFragment
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.TextView
import br.com.livroandroid.carros.R

class AboutDialog : DialogFragment() {
    @Suppress("DEPRECATION")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Creates HTML with the text about the application
        val aboutBody = SpannableStringBuilder()

        //Version of the application
        val versionName = getAppVersionName()

        //Converts the text from strings.xml to html
        val html = Html.fromHtml(getString(R.string.about_dialog_text, versionName))
        aboutBody.append(html)

        //Inflates the layout
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_about, null) as TextView
        view.text = aboutBody
        view.movementMethod = LinkMovementMethod()

        //Creates the customized dialog

        return AlertDialog.Builder(activity)
                .setTitle(R.string.about_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                .create()
    }

    //Return the app version registered in build.gradle

    fun getAppVersionName(): String? {
        val pm = activity?.packageManager
        val packageName = activity?.packageName
        var versionName: String?
        try {
            val info = pm?.getPackageInfo(packageName, 0)
            versionName = info?.versionName
        }
        catch (ex: PackageManager.NameNotFoundException) {
            versionName = "N/A"
        }
        return versionName
        }

    companion object {
        //Utilitarian method that shows the dialog
        fun showAbout(fm: android.support.v4.app.FragmentManager) {
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag("about_dialog")
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            AboutDialog().show(ft, "about_dialog")
        }
    }
}

