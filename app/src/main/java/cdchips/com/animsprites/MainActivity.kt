package cdchips.com.animsprites

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.io.IOException
import java.io.InputStream
import java.util.*
import android.os.Build
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.AnimationDrawable
import android.util.Log
import android.widget.ImageView
import cdchips.com.animsprites.R.id.iv_sprite


class MainActivity : AppCompatActivity() {

    var sprites:ArrayList<Bitmap>? = null

    private var FRAME_W = 108
    // frame height
    private var FRAME_H = 140
    // number of frames
    private val NB_FRAMES = 16
    // nb of frames in x
    private val COUNT_X = 8
    // nb of frames in y
    private val COUNT_Y = 2


    // we can slow animation by changing frame duration
    private val FRAME_DURATION = 100 // in ms !
    // scale factor for each frame
    private val SCALE_FACTOR = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spa = SpriteAnimator(this,R.drawable.sprite_run,8,2,16)

        val iv = findViewById<ImageView>(R.id.iv_sprite)
        val anim = spa.getAnimation(50,0,7)
        if(anim!=null){
            iv.setImageDrawable(anim)
            iv.scaleType = ImageView.ScaleType.FIT_CENTER
            anim.start()
        }

    }



}
