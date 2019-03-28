package peter.anos.ask.app.view.view_hierarchy;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.concurrent.ConcurrentHashMap;

public class MainViewHierarchyActivity extends Activity {

    private static final String TAG = "ViewHierarchyActivity";

    Button mButton = null;

    private void printViewHierarchy(View parent, int level, int childIndex) {
        /**
         *
         *  * DecorView child -1     (x, y), (w, y)
         *  ** FrameLayout child 0   (x, y), (w, h)
         *  *** TextView child 0     (x, y), (w, h)
         *  ** FrameLayout child 1   (x, y), (w, h)
         *  *** Button child 0       (x, y), (w, h)
         *  *** TextView child 1     (x, y), (w, h)
         *  *** FrameLayout child 2  (x, y), (w, h)
         *
         * */

        int i;
        String levelStr = "*";
        for (i = 0; i < level; i ++) {
            levelStr += "*";
        }

        int [] postions = new int[2];
        parent.getLocationOnScreen(postions);
        Log.i(TAG, levelStr + " " + parent.getClass().getName() + " child " + childIndex +
                " (" + postions[0] + ", " + postions[1] + "), (" + parent.getWidth() + ", " + parent.getHeight() + ")");

        if (parent instanceof ViewGroup) {
            View child;
            ViewGroup root = (ViewGroup) parent;
            i = 0;

            while((child = root.getChildAt(i)) != null) {
                printViewHierarchy(child, level + 1, i);
                i ++;
            }
        }
    }

    class MyButtionViewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.i(TAG, " MyButtionViewListener onClick");
            View decorView = getWindow().getDecorView();
            printViewHierarchy(decorView, 0, -1);
        }
    }

    private void showDecorViewChild(int index) {
        ViewGroup decor = (ViewGroup)getWindow().getDecorView();
        View v = decor.getChildAt(index);
        if (v != null) {
            v.setVisibility(View.VISIBLE);
        }
    }

    private void hideDecorViewChild(int index) {
        ViewGroup decor = (ViewGroup) getWindow().getDecorView();
        View v = decor.getChildAt(index);
        if (v != null) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_hierarchy);

        mButton = findViewById(R.id.button);

        mButton.setOnClickListener(new MyButtionViewListener());
    }

    // private function is error!!
    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.led_1:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "led_1 on", Toast.LENGTH_LONG).show();
                    hideDecorViewChild(1);
                } else {
                    Toast.makeText(getApplicationContext(), "led_1 off", Toast.LENGTH_LONG).show();
                    showDecorViewChild(1);
                }
            case R.id.led_2:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "led_2 on", Toast.LENGTH_LONG).show();
                    hideDecorViewChild(2);
                } else {
                    Toast.makeText(getApplicationContext(), "led_2 off", Toast.LENGTH_LONG).show();
                    showDecorViewChild(2);
                }

            case R.id.led_3:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "led_3 on", Toast.LENGTH_LONG).show();
                    hideDecorViewChild(3);
                } else {
                    Toast.makeText(getApplicationContext(), "led_3 off", Toast.LENGTH_LONG).show();
                    showDecorViewChild(3);
                }

            case R.id.led_4:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "led_4 on", Toast.LENGTH_LONG).show();
                    hideDecorViewChild(4);
                } else {
                    Toast.makeText(getApplicationContext(), "led_4 off", Toast.LENGTH_LONG).show();
                    showDecorViewChild(4);
                }

        }
    }

}
