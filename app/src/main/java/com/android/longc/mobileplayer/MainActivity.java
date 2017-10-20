package com.android.longc.mobileplayer;

import com.android.longc.mobileplayer.base.BasePager;
import com.android.longc.mobileplayer.pager.AudioPager;
import com.android.longc.mobileplayer.pager.NetAudioPager;
import com.android.longc.mobileplayer.pager.NetVideoPager;
import com.android.longc.mobileplayer.pager.VideoPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_bottom_tag;

    private ArrayList<BasePager> basePagers;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_bottom_tag = (RadioGroup) findViewById(R.id.rg_bottom_tag);

        basePagers = new ArrayList<BasePager>();
        basePagers.add(new VideoPager(this));// 添加本地视频页面-0
        basePagers.add(new AudioPager(this));// 添加本地音乐页面-1
        basePagers.add(new NetVideoPager(this));// 添加网络视频页面-2
        basePagers.add(new NetAudioPager(this));// 添加网络音乐页面-3

        rg_bottom_tag.check(R.id.rb_video);// 设置默认页面为首页
        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_audio:// 音频
                    position = 1;
                    break;
                case R.id.rb_netVideo:// 网络视频
                    position = 2;
                    break;
                case R.id.rb_netaudio:// 网络音频
                    position = 3;
                    break;
                default:
                    position = 0;// 视频
                    break;
            }
            setFragment();

        }

        private void setFragment() {
            // 1.得到FragmentManager
            FragmentManager manager = getSupportFragmentManager();
            // 2.开启事务
            FragmentTransaction ft = manager.beginTransaction();
            // 3.替换
            ft.replace(R.id.fl_main_content, new Fragment() {
                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                    BasePager basePager = getBasePager();
                    if (basePager != null) {
                        return basePager.rootView;

                    }
                    return null;
                }
            });
            // 4.提交事务
            ft.commit();

        }

        /**
         * 根据位置得到对应的页面
         *
         * @return
         */
        private BasePager getBasePager() {
            BasePager basePager = basePagers.get(position);
            if (basePager != null && !basePager.isInitData) {
                basePager.isInitData = true;
                basePager.initData();// 联网请求或者绑定数据
            }
            return basePager;
        }

    }
}
