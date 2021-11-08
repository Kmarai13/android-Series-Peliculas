package com.example.app_peliculas_series.views.slideshow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_peliculas_series.databinding.FragmentGalleryBinding;
import com.example.app_peliculas_series.databinding.FragmentSlideshowBinding;
import com.example.app_peliculas_series.views.gallery.GalleryViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    protected ImageView imageView;
    protected Button btnCargarImg;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int File = 1;
    DatabaseReference myRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imageView = binding.imagemId;
        btnCargarImg = binding.btnCargarImg;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user1");

        btnCargarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto();
            }
        });

//        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    private void foto() {
        cargarImagen();
    }

    private void cargarImagen() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("imagen/");
//        startActivityForResult(intent, File);
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==RESULT_OK){
//            Uri path=data.getData();
//            imageView.setImageURI(path);
//        }else
        if (resultCode == RESULT_OK) {
            Uri fileUri=data.getData();
            imageView.setImageURI(fileUri);
            StorageReference folder = FirebaseStorage.getInstance().getReference().child("user");
            final StorageReference file_name = folder.child("file" + fileUri.getLastPathSegment());

            file_name.putFile(fileUri).addOnSuccessListener(taskSnapshot -> file_name.getDownloadUrl().addOnSuccessListener(uri -> {
                HashMap<String, String> haahMap = new HashMap<>();
                haahMap.put("link", String.valueOf(uri));
                myRef.setValue(haahMap);
                Toast.makeText(getContext(),"La imagen se subio a FIREBASE correctamente", Toast.LENGTH_LONG).show();
            }));
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}