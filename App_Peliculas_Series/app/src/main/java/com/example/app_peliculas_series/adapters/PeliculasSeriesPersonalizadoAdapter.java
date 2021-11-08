package com.example.app_peliculas_series.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_peliculas_series.R;
import com.example.app_peliculas_series.model.PeliculasBean;

import java.util.ArrayList;

public class PeliculasSeriesPersonalizadoAdapter extends RecyclerView.Adapter<PeliculasSeriesPersonalizadoAdapter.ViewHolder> {

    private String[] localDataSet;
    ArrayList<PeliculasBean> listMovie;
    private Context context;

    public PeliculasSeriesPersonalizadoAdapter(ArrayList<PeliculasBean> listMovie) {
        this.listMovie = listMovie;
    }


    public interface OnItemClickListener {
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        public ImageView ivPelicula;
        TextView tvDescripcion;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            ivPelicula = view.findViewById(R.id.iv_poster);
            tvTitulo = view.findViewById(R.id.tv_title);
            tvDescripcion = view.findViewById(R.id.tv_overview);
        }

        public void asignarMovie(String s) {
            tvTitulo.setText(s);
        }
//        public TextView getTextView() {
//            return textView;
//        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public PeliculasSeriesPersonalizadoAdapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.content_item, viewGroup, false);

        context = viewGroup.getContext();

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitulo.setText(listMovie.get(position).getTitulo());
        viewHolder.tvDescripcion.setText(listMovie.get(position).getDescripcion());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/original" + listMovie.get(position).getPoster())
                .into(viewHolder.ivPelicula);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listMovie.size();
    }


}
