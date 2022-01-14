package mostafa.projects.grand

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log

class GrandApp : Application(), Application.ActivityLifecycleCallbacks {
    companion object {
        private lateinit var currentActivity: Activity

        fun currentActivity(): Activity {
            return currentActivity
        }

    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        Log.i("ActivityLifeCycle", "onActivityCreated")
        currentActivity = activity

    }

    override fun onActivityStarted(activity: Activity) {
        Log.i("ActivityLifeCycle", "onActivityStarted")
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityPaused(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityStopped(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(activity: Activity) {
        TODO("Not yet implemented")
    }
}