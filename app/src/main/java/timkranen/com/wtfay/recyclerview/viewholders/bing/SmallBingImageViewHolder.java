package timkranen.com.wtfay.recyclerview.viewholders.bing;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import timkranen.com.wtfay.R;
import timkranen.com.wtfay.domain.model.base.ServiceResult;
import timkranen.com.wtfay.domain.model.services.bing.Image;
import timkranen.com.wtfay.domain.model.services.bing.ImageResult;
import timkranen.com.wtfay.recyclerview.viewholders.ServiceResultViewHolder;

/**
 * Created by tim on 7/10/16.
 */
public class SmallBingImageViewHolder extends ServiceResultViewHolder {
    private static final int COMPATABILITY = 1;

    protected @BindView(R.id.bing_image_small) ImageView smallImage;

    public SmallBingImageViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public int getCompatability() {
        return COMPATABILITY;
    }

    @Override
    public void update(Context context, ServiceResult... result) {
        Image imageResult = (Image) result[0];
        Glide.with(context).load(imageResult.getThumbnailUrl()).into(smallImage);
    }
}
