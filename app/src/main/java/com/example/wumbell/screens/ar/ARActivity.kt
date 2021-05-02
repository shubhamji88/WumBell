package com.example.wumbell.screens.ar

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.wumbell.R
import com.google.ar.core.*
import com.google.ar.core.ArCoreApk.Availability
import com.google.ar.core.exceptions.UnavailableException
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.SkeletonNode
import com.google.ar.sceneform.animation.ModelAnimator
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_ar.*
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.timer_card_view_layout.view.*
import kotlinx.coroutines.withContext

class ARActivity : AppCompatActivity() {
    lateinit var arFragment:ArFragment
    private var timerDashbord: ViewRenderable? = null
    private var renderable:ModelRenderable?=null
    private var animator:ModelAnimator?=null
    private var animationNum=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.wumbell.R.layout.activity_ar)
        isARCoreSupportedAndUpToDate()
        arFragment= ux_fragment as ArFragment
        arFragment.setOnTapArPlaneListener{ hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
            if(plane.type!=Plane.Type.HORIZONTAL_UPWARD_FACING){
                return@setOnTapArPlaneListener
            }
            val textAnchorNode = AnchorNode(
                    hitResult.trackable.createAnchor(
                            hitResult.hitPose.compose(
                                    Pose.makeTranslation(
                                            1.0f,
                                            0.5f,
                                            0.0f
                                    )
                            )
                    )
            )


            var anchor=hitResult.createAnchor()
            placeObject(arFragment, anchor, textAnchorNode)
        }
    }
    private fun animateModel(){
        animator?.let {
            if(it.isRunning){
                it.end()
            }
        }
        renderable?.let{
            val data=it.getAnimationData(animationNum)
            animator= ModelAnimator(data, it)
            animator?.start()
        }
    }

    private fun placeObject(
        arFragment: ArFragment,
        anchor: Anchor?,
        textAnchorNode: AnchorNode
    ) {
        lifecycleScope.launch {
            timerDashbord = ViewRenderable.builder().setView(applicationContext, com.example.wumbell.R.layout.timer_card_view_layout)
                    .build().await()
            val personModel=ModelRenderable.builder()
                .setSource(applicationContext, R.raw.modell).build()
                .await()
            addToScene(arFragment, anchor, personModel, textAnchorNode)
        }
    }

    private fun addToScene(
        arFragment: ArFragment,
        anchor: Anchor?,
        it: ModelRenderable?,
        textAnchorNode: AnchorNode
    ) {
        renderable=it
        val anchorNode= AnchorNode(anchor)

        val textNode = Node()
        textAnchorNode.setParent(arFragment.arSceneView.scene)
        textAnchorNode.renderable=timerDashbord
        textNode.setParent(textAnchorNode)


        val modelNode=SkeletonNode()
        modelNode.localRotation = Quaternion.axisAngle(Vector3(0f, 1f, 0f), 180f)
        modelNode.renderable=renderable

        val node=TransformableNode(arFragment.transformationSystem)
        node.addChild(modelNode)
        node.setParent(anchorNode)
        arFragment.arSceneView.scene.addChild(anchorNode)
        updateTextNodeData()
        animateIt()
    }

    private fun animateIt() {
        object : CountDownTimer(31000, 4800) {
            override fun onTick(millisUntilFinished: Long) {
                animateModel()
            }
            override fun onFinish() {
            }
        }.start()
    }


    private fun updateTextNodeData() {
        val timerView=timerDashbord?.view
        object : CountDownTimer(31000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsInMilli: Long = 1000
                val elapsedSeconds = millisUntilFinished / secondsInMilli
                timerView?.aqi_progress?.setProgress(elapsedSeconds.toDouble(), 31.0);

            }
            override fun onFinish() {
            }
        }.start()

    }

    // Verify that ARCore is installed and using the current version.
    fun isARCoreSupportedAndUpToDate(): Boolean {
        return when (ArCoreApk.getInstance().checkAvailability(this)) {
            Availability.SUPPORTED_INSTALLED -> true
            Availability.SUPPORTED_APK_TOO_OLD, Availability.SUPPORTED_NOT_INSTALLED -> {
                try {
                    // Request ARCore installation or update if needed.
                    when (ArCoreApk.getInstance().requestInstall(this, true)) {
                        ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                            Log.i("Main", "ARCore installation requested.")
                            false
                        }
                        ArCoreApk.InstallStatus.INSTALLED -> true
                    }
                } catch (e: UnavailableException) {
                    Log.e("Main", "ARCore not installed", e)
                    false
                }
            }

            Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE ->
                // This device is not supported for AR.
                false

            Availability.UNKNOWN_CHECKING -> {
                false
                // ARCore is checking the availability with a remote query.
                // This function should be called again after waiting 200 ms to determine the query result.
            }
            Availability.UNKNOWN_ERROR, Availability.UNKNOWN_TIMED_OUT -> {
                false
                // There was an error checking for AR availability. This may be due to the device being offline.
                // Handle the error appropriately.
            }
        }
    }

}