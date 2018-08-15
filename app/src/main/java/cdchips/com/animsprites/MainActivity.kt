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

    private val FRAME_W = 88
    // frame height
    private val FRAME_H = 140
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


        val birdBmp = getBitmapFromAssets(this, "sprite_run.png")


        sprites =  ArrayList<Bitmap>()

        if (birdBmp != null) {
            // cut bitmaps from bird bmp to array of bitmaps
            Log.w("Bitmap","h -> ${birdBmp!!.height} , w -> ${birdBmp!!.width}")
            var currentFrame = 0

            for (i in 0..COUNT_Y-1) {
                for (j in 0..COUNT_X-1) {

                    Log.w("Bitmap","y -> ${FRAME_H * i} , w -> ${FRAME_H}")
                    sprites!!.add(Bitmap.createBitmap(birdBmp, FRAME_W
                        * j, FRAME_H * i, FRAME_W, FRAME_H))

                    /*
                    sprites!![currentFrame] = Bitmap.createScaledBitmap(
                            sprites!![currentFrame], FRAME_W / SCALE_FACTOR, FRAME_H / SCALE_FACTOR, true);

*/
                    if (++currentFrame >= NB_FRAMES) break

                }
            }
        }else{
            Log.w("Main","Bitmap null")
        }


        val animation = AnimationDrawable()
        animation.isOneShot = false // repeat animation

        for (i in 0..NB_FRAMES-1) {
            animation.addFrame(BitmapDrawable(resources, sprites!![i]),
                    FRAME_DURATION)
        }

        val img = findViewById<ImageView>(R.id.iv_sprite)
        img.scaleType = ImageView.ScaleType.FIT_CENTER
        // load animation on image
        /*
        if (Build.VERSION.SDK_INT < 16) {
            img.setBackgroundDrawable(animation)
        } else {
            //img.setBackground(animation)
        }
        */
        img.setImageDrawable(animation)
        img.scaleType = ImageView.ScaleType.FIT_CENTER

        // start animation on image
        img.post(Runnable { animation.start() })


    }



    fun getBitmapFromAssets(context:Context,
                            filepath: String): Bitmap? {
        var bitmap: Bitmap? = null

        try {
            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.sprite_run);
        } catch (ioe: IOException) {
            // manage exception
        } finally {
        }

        return bitmap
    }

}
