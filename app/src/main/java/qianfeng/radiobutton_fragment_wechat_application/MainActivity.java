package qianfeng.radiobutton_fragment_wechat_application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.chat:
                        fragment = new ChatFragment();
                        break;
                    case R.id.friend:
                        fragment = new FriendFragment();
                        break;
                    case R.id.moment:
                        fragment = new MomentFragment();
                        break;
                    case R.id.mine:
                        fragment = new MineFragment();
                        break;

                }

//                getSupportFragmentManager().beginTransaction().replace(R.id.ll,fragment).commit();

                //add   //切换Fragment方式一：replace方法每次会将当前的Fragment销毁掉，然后创建新的Fragment实例添加进来
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.ll, fragment);
//                transaction.commit();
                //切换Fragment方式二：add、show、hide
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if(fragment.isAdded()) {
                    transaction.hide(currentFragment).show(fragment).commit(); // 记得commit啊，否则就不生效了啊！
                }else{
                    transaction.hide(currentFragment).add(R.id.ll,fragment).commit();
                }
                currentFragment = fragment;


            }
        });

        currentFragment = new ChatFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ll, currentFragment).commit();

    }
}
