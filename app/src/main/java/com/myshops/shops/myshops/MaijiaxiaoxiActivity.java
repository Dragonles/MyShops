package com.myshops.shops.myshops;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myshops.shops.bean.XiaoXi;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

@ContentView(R.layout.activity_maijaixioxi)
public class MaijiaxiaoxiActivity extends AppCompatActivity{

    private ViewPager mViewPage;
    private Fragment mConvesationFragment = null;
    private Fragment mConversationlist;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment> mFrament = new ArrayList<>();
    List<XiaoXi> list = new ArrayList<XiaoXi>();
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mViewPage = (ViewPager)findViewById(R.id.frament_message);
        mConversationlist = cha();
        mFrament.add(mConversationlist);
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFrament.get(position);
            }
            @Override
            public int getCount() {
                return mFrament.size();
            }
        };
        mViewPage.setAdapter(mFragmentPagerAdapter);
    }
    @Event(value = R.id.message_goback)
    private void messageClick(View v) {
        finish();
    }
    public Fragment cha(){
        if (mConvesationFragment == null) {
            //RongIM.getInstance().startPrivateChat(MaijiaxiaoxiActivity.this, "26594", "title");
            ConversationListFragment listFragment = ConversationListFragment.getInstance();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                    .build();
            listFragment.setUri(uri);
            return listFragment;
        }else {
            return mConvesationFragment;
        }
    }
}
