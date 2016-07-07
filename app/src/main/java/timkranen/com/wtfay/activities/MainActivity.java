package timkranen.com.wtfay.activities;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timkranen.com.wtfay.R;
import timkranen.com.wtfay.controllers.MainController;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_container) ViewGroup container;

    private Router _router;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        _router = Conductor.attachRouter(this, container, savedInstanceState);
        if(!_router.hasRootController()) {
            _router.setRoot(new MainController());
        }

    }
}
