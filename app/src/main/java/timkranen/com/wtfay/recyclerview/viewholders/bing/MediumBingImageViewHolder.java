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
import timkranen.com.wtfay.recyclerview.viewholders.ServiceResultViewHolder;

/**
 * Created by tim on 7/10/16.
 */
public class MediumBingImageViewHolder extends ServiceResultViewHolder {
    private static final int COMPATABILITY = 2;

    protected
    @BindView(R.id.bing_image_medium_first)
    ImageView firstImage;

    protected
    @BindView(R.id.bing_image_medium_second)
    ImageView secondImage;

    public MediumBingImageViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public int getCompatability() {
        return COMPATABILITY;
    }

    @Override
    public void update(Context context, ServiceResult... result) {
        Image firstImageResult = (Image) result[0];
        Image secondImageResult = (Image) result[1];
        Glide.with(context).load(firstImageResult.getImageUrl()).into(firstImage);
        Glide.with(context).load(secondImageResult.getImageUrl()).into(secondImage);
    }
}
