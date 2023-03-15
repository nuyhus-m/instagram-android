package com.softsquared.template.kotlin.src.add

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityAddBinding
import com.softsquared.template.kotlin.src.add.gallery.GalleryFragment
import com.softsquared.template.kotlin.src.add.upload.UploadFragment
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {

    lateinit var currentFragment: Fragment
    private val REQUEST_READ_EXTERMAL_STORAGE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 권한 부여 확인
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한 허용 안됨
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // 이전에 이미 권한이 거부되었을 때 설명
                alert(
                    "사진 정보를 얻으려면 외부 저장소 권한이 필수로 필요합니다",
                    "권한이 필요한 이유"
                ) {
                    yesButton {
                        // 권한 요청
                        ActivityCompat.requestPermissions(
                            this@AddActivity,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_READ_EXTERMAL_STORAGE
                        )
                    }
                    noButton { }
                }.show()
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERMAL_STORAGE
                )
                fragmentController(resources.getString(R.string.gallery_fragment), f, f)
            }
        } else {
            // 권한이 이미 허용됨
            fragmentController(resources.getString(R.string.gallery_fragment), f, f)
        }
    }

    fun fragmentController(name: String, add: Boolean, animate: Boolean) {

        when (name) {
            resources.getString(R.string.gallery_fragment) -> {
                currentFragment = GalleryFragment()
            }
            resources.getString(R.string.upload_fragment) -> {
                currentFragment = UploadFragment()
            }
        }

        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.add_frm, currentFragment)

        if (add) {
            trans.addToBackStack(name)
        }

        if (animate) {
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        trans.commit()
    }

    fun fragmentRemoveBackStack(name: String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}