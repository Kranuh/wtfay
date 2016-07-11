package timkranen.com.wtfay.recyclerview.viewholders;

import android.app.Service;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import timkranen.com.wtfay.domain.model.base.ServiceResult;

/**
 * Created by tim on 7/10/16.
 */
public abstract class ServiceResultViewHolder extends RecyclerView.ViewHolder {
    public ServiceResultViewHolder(View itemView) {
        super(itemView);
    }

    public abstract int getCompatability();
    public abstract void update(Context context, ServiceResult... result);
}
