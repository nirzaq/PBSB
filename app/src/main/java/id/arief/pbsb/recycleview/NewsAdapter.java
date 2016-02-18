package id.arief.pbsb.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.arief.pbsb.R;
import id.arief.pbsb.model.News;



public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder>  {
    private List<News> newsItemList;
    private final NewsClickListener mListener;

    public NewsAdapter(NewsClickListener listener) {
        newsItemList = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_news, null);

        Holder viewHolder = new Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Holder customViewHolder, int i) {
        News newsItem = newsItemList.get(i);
        customViewHolder.title.setText(newsItem.getTitle());
        customViewHolder.timeStamp.setText(newsItem.getDateId());
        Picasso.with(customViewHolder.itemView.getContext())
                .load(newsItem.getImageThumbnail())
                .noFade()
                .centerCrop()
                .fit()
                .into(customViewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }



    public void addNews(News news) {
        newsItemList.add(news);
        notifyDataSetChanged();
    }

    public void reset() {
        newsItemList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<News> list) {
        newsItemList.addAll(list);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView category;
        TextView timeStamp;
        ImageView thumbnail;

        public Holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            category = (TextView) itemView.findViewById(R.id.category);
            timeStamp = (TextView) itemView.findViewById(R.id.timestamp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getLayoutPosition());
        }
    }

    public interface NewsClickListener {

        void onClick(int position);
    }

    public News getSelectedNews(int position) {
        return newsItemList.get(position);
    }
}
