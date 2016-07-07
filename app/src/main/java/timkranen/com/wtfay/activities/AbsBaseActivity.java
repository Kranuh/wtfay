package timkranen.com.wtfay.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tim on 6/29/16.
 */
public abstract class AbsBaseActivity extends AppCompatActivity {

    private @LayoutRes int layout;

    protected Unbinder unbinder;

    public AbsBaseActivity(){}

    public AbsBaseActivity(@LayoutRes int layout) {
        this.layout = layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layout);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
