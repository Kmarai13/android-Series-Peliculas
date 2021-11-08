package com.example.app_peliculas_series.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_peliculas_series.R;

import java.util.ArrayList;

public class PeliculasSeriesAdapter extends RecyclerView.Adapter<PeliculasSeriesAdapter.ViewHolder> {

    private String[] localDataSet;
    ArrayList<String> listMovie;

    public PeliculasSeriesAdapter(ArrayList<String> listMovie) {
        this.listMovie = listMovie;
    }


    public interface OnItemClickListener {
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitulo;
        private final ImageView ivPelicula;
        private final TextView tvDescripcion;

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
    public PeliculasSeriesAdapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.content_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.asignarMovie(listMovie.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listMovie.size();
    }


}
