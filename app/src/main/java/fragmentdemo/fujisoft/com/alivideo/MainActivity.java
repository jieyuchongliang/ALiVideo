package fragmentdemo.fujisoft.com.alivideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.alivc.player.AliVcMediaPlayer;

public class MainActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private AliVcMediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private static final String TAG = "MainActivity";
    private void init() {
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        //初始化播放器（只需调用一次即可，建议在application中初始化）
        AliVcMediaPlayer.init(getApplicationContext());
        //创建播放器的实例
        mPlayer = new AliVcMediaPlayer(this, mSurfaceView);

        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
//                holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
                holder.setKeepScreenOn(true);
                Log.e("lfj0930", "surfaceCreated ");
                // Important: surfaceView changed from background to front, we need reset surface to mediaplayer.
                // 对于从后台切换到前台,需要重设surface;部分手机锁屏也会做前后台切换的处理
                if (mPlayer != null) {
                    mPlayer.setVideoSurface(holder.getSurface());
                }

            }

            public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

                Log.e("lfj0930", "surfaceChanged ");
                if (mPlayer != null) {
                    mPlayer.setSurfaceChanged();
                }
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.e("lfj0930", "surfaceDestroyed ");
//                if (mPlayer != null) {
//                    mPlayer.releaseVideoSurface();
//                }
            }
        });

        if (mPlayer != null) {
            mPlayer.prepareAndPlay("http://player.alicdn.com/video/aliyunmedia.mp4");
        }

        //开始播放
//        mPlayer.play();
        mPlayer.setPlaySpeed(2.0f);

    }

}
